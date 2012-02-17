/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.importing.impl.service.execute;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.importing.ImportContainer;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.importing.facade.component.ImportingComponentLocator;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.support.scripting.facade.component.ScriptingComponentLocator;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
import org.nabucco.framework.support.scripting.facade.exception.ScriptExecutionException;
import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;
import org.nabucco.framework.support.scripting.facade.message.search.ScriptSearchRq;

/**
 * ExecuteImportUtil
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ExecuteImportUtil {

    private static final String IMPORT_CONTEXT = "context";

    private static final String IMPORT_INPUT_DATA = "data";

    private static final NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            ExecuteImportUtil.class);

    private static DocumentBuilder DOCUMENT_BUILDER = null;

    static DocumentBuilder getDocumentBuilder() throws ImportException {
        if (ExecuteImportUtil.DOCUMENT_BUILDER == null) {
            try {
                ExecuteImportUtil.DOCUMENT_BUILDER = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                String msg = "Unable to create instance of DocumentBuilder";
                logger.error(msg);
                throw new ImportException(msg, e);
            }
        }
        return ExecuteImportUtil.DOCUMENT_BUILDER;
    }

    static Script resolveScript(ServiceMessageContext context, ImportConfiguration cfg)
            throws ImportException {
        try {
            ServiceRequest<ScriptSearchRq> rq = new ServiceRequest<ScriptSearchRq>(context);
            ScriptSearchRq requestMessage = new ScriptSearchRq();
            requestMessage.setName(cfg.getScriptName());
            rq.setRequestMessage(requestMessage);
            ScriptListMsg responseMessage = ScriptingComponentLocator.getInstance().getComponent()
                    .getScriptSearchService().searchScripts(rq).getResponseMessage();
            List<Script> scriptList = responseMessage.getScriptList();
            if (scriptList.isEmpty()) {
                String msg = "Cannot find Script by name: " + cfg.getScriptName();
                logger.error(msg);
                throw new ImportException(msg);
            }
            if (scriptList.size() > 1) {
                logger.warning("found multiple script's named : \""
                        + cfg.getScriptName() + "\" picking last");
            }
            return scriptList.get(scriptList.size() - 1);
        } catch (ServiceException e) {
            String msg = "unable to run script search for ImportConfiguration with id: "
                    + cfg.getId();
            logger.error(msg);
            throw new ImportException(msg);
        } catch (ConnectionException e) {
            String msg = "unable to connect to scripting component while executing import with ImportConfiguration id: "
                    + cfg.getId();
            logger.error(msg);
            throw new ImportException(msg);
        }
    }

    static Script prepareScriptData(Script script, ImportContainer container, ImportContext context) {
        ScriptData inputData = new ScriptData();
        inputData.setDatatypeState(DatatypeState.INITIALIZED);
        inputData.setName(IMPORT_INPUT_DATA);
        inputData.setDescription("DataContainer of data to Import");
        inputData
                .setTypeName("org.nabucco.framework.base.facade.datatype.importing.ImportContainer");
        inputData.setValue(container);

        ScriptData paramContext = new ScriptData();
        paramContext.setDatatypeState(DatatypeState.INITIALIZED);
        paramContext.setName(IMPORT_CONTEXT);
        paramContext
                .setDescription("Importing context, should be altered on import of elements, provide new database id's");
        paramContext
                .setTypeName("org.nabucco.framework.base.facade.datatype.importing.ImportContext");
        paramContext.setValue(context);

        // only in
        script.getInputData().add(inputData);
        // inout
        script.getOutputData().add(paramContext);

        return script;
    }

    static ImportContext invokeScript(ServiceMessageContext callContext, Script s)
            throws ImportException {
        ImportContext result = null;
        ServiceRequest<ScriptExecutionMsg> rq = new ServiceRequest<ScriptExecutionMsg>(callContext);
        ScriptExecutionMsg requestMessage = new ScriptExecutionMsg();
        requestMessage.setScript(s);
        rq.setRequestMessage(requestMessage);
        try {
            ScriptExecutionResultMsg responseMessage = ScriptingComponentLocator.getInstance()
                    .getComponent().getScriptService().executeScript(rq).getResponseMessage();
            List<ScriptData> resultData = responseMessage.getScriptDataList();
            for (ScriptData current : resultData) {
                if (current.getName().getValue().compareTo(IMPORT_CONTEXT) == 0) {
                    result = (ImportContext) current.getValue();
                    logger.info("found result context in returned ScriptExecutionResultMsg");
                    break;
                }
            }
        } catch (ScriptExecutionException e) {
            String msg = "Execution of the script \"" + s.getName() + "\" failed.";
            logger.error(msg);
            throw new ImportException(msg, e);
        } catch (ServiceException e) {
            String msg = "Invocation of ScriptService failed.";
            logger.error(msg);
            throw new ImportException(msg, e);
        } catch (ConnectionException e) {
            String msg = "Unable to connect to support scripting component.";
            logger.error(msg);
            throw new ImportException(msg, e);
        }
        return result;
    }

    static ImportJob produceImportJob(ServiceMessageContext context, ImportConfiguration cfg)
            throws ImportException {
        try {
            ServiceRequest<EmptyServiceMessage> rq = new ServiceRequest<EmptyServiceMessage>(
                    context);
            rq.setRequestMessage(new EmptyServiceMessage());
            ImportJobMsg responseMessage = ImportingComponentLocator.getInstance().getComponent()
                    .getProduceImporting().produceImportJob(rq).getResponseMessage();
            ImportJob resultingJob = responseMessage.getImportJob();
            resultingJob.setConfiguration(cfg);
            return resultingJob;
        } catch (ProduceException e) {
            String msg = "Unable to produce ImportJob for ImportConfiguration with id: "
                    + cfg.getId();
            logger.error(msg);
            throw new ImportException(msg);
        } catch (ServiceException e) {
            String msg = "Unable to call service to produce ImportJob for importConfiguration with id: "
                    + cfg.getId();
            logger.error(msg);
            throw new ImportException(msg);
        } catch (ConnectionException e) {
            String msg = "Unable to connect to Importing Component while attempting to create ImportJob for ImportConfiguration with id: "
                    + cfg.getId();
            logger.error(msg);
            throw new ImportException(msg);
        }
    }
}
