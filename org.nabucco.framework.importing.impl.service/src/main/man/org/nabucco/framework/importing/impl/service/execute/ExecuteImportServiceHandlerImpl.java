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
///*
// * Copyright 2010 PRODYNA AG
// *
// * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.opensource.org/licenses/eclipse-1.0.php or
// * http://nabuccosource.org/License.html
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.nabucco.framework.importing.impl.service.execute;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.PrintWriter;
//import java.io.Reader;
//import java.io.StringWriter;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
//import org.nabucco.framework.base.facade.datatype.DatatypeState;
//import org.nabucco.framework.base.facade.datatype.DateTime;
//import org.nabucco.framework.base.facade.datatype.Name;
//import org.nabucco.framework.base.facade.datatype.log.LogTrace;
//import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
//import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
//import org.nabucco.framework.base.facade.datatype.net.Url;
//import org.nabucco.framework.base.facade.exception.service.MaintainException;
//import org.nabucco.framework.base.facade.message.ServiceMessage;
//import org.nabucco.framework.base.facade.message.ServiceRequest;
//import org.nabucco.framework.base.facade.message.ServiceResponse;
//import org.nabucco.framework.base.facade.service.Service;
//import org.nabucco.framework.importing.facade.component.ImportingComponent;
//import org.nabucco.framework.importing.facade.component.ImportingComponentLocator;
//import org.nabucco.framework.importing.facade.datatype.ImportJob;
//import org.nabucco.framework.importing.facade.datatype.ImportStateType;
//import org.nabucco.framework.importing.facade.datatype.MaintainServiceConfiguration;
//import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRqMsg;
//import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRsMsg;
//import org.nabucco.framework.importing.facade.message.maintain.ImportJobMaintainMsg;
//import org.nabucco.framework.support.scripting.facade.component.ScriptComponent;
//import org.nabucco.framework.support.scripting.facade.component.ScriptComponentLocator;
//import org.nabucco.framework.support.scripting.facade.datatype.ImportData;
//import org.nabucco.framework.support.scripting.facade.datatype.LogTraceWrapper;
//import org.nabucco.framework.support.scripting.facade.datatype.NbcDatatypeList;
//import org.nabucco.framework.support.scripting.facade.datatype.Script;
//import org.nabucco.framework.support.scripting.facade.datatype.ScriptData;
//import org.nabucco.framework.support.scripting.facade.datatype.ScriptSourceCode;
//import org.nabucco.framework.support.scripting.facade.datatype.ScriptType;
//import org.nabucco.framework.support.scripting.facade.message.ScriptListMsg;
//import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionMsg;
//import org.nabucco.framework.support.scripting.facade.message.execution.ScriptExecutionResultMsg;
//import org.nabucco.framework.support.scripting.facade.message.search.ScriptSearchMsg;
//
///**
// * ExecuteImportServiceHandlerImpl
// * 
// * @author Lasse Asbach, PRODYNA AG
// */
//public class ExecuteImportServiceHandlerImpl extends ExecuteImportServiceHandler {
//
//    /**
//     * Comment for <code>serialVersionUID</code>
//     */
//    private static final long serialVersionUID = 1L;
//
//    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
//            ExecuteImportServiceHandlerImpl.class);
//
//    /*
//     * (non-Javadoc)
//     * 
//     * @see
//     * org.nabucco.framework.importing.impl.service.execute.ExecuteImportServiceHandler#executeImport
//     * (org.nabucco.framework.importing.facade.message.execute.ExecuteImportRqMsg)
//     */
//    @Override
//    protected ExecuteImportRsMsg executeImport(ExecuteImportRqMsg rqMsg) throws MaintainException {
//
//        ExecuteImportRsMsg rsMsg = new ExecuteImportRsMsg();
//        ImportJob importJob = new ImportJob();
//
//        // measure start time of this import job
//        java.util.Date startTime = new java.util.Date();
//        DateTime nbcStartTime = new DateTime();
//        nbcStartTime.setValue(startTime.getTime());
//
//        // initialize the import job with data already available
//        importJob.setDescription(rqMsg.getDescription());
//        importJob.setOwner(rqMsg.getOwner());
//        importJob.setName(rqMsg.getName());
//        importJob.setConfiguration(rqMsg.getConfiguration());
//        importJob.setStartTime(nbcStartTime);
//        importJob.setSource(rqMsg.getUrl());
//
//        boolean failedFlag = false;
//        String dataToImport = null;
//        StringBuilder logTraces = new StringBuilder();
//        StringBuilder errorTraces = new StringBuilder();
//
//        logger.debug("BEGIN executeImport");
//        logTraces.append(formatLogLine("DEBUG", "BEGIN executeImport"));
//
//        try {
//
//            // load the data to import from the given URL and make it available to the script
//            // under name 'importData' as input data
//            dataToImport = getDataToImport(rqMsg.getUrl());
//            logger.trace("dataToImport = '" + dataToImport + "'");
//
//            // build script execution message
//            ScriptExecutionMsg executeScriptRqMsg = createExecuteScriptRqMsg(rqMsg, dataToImport);
//
//            // call the executeScript service operation in support.scripting
//            ScriptExecutionResultMsg executeScriptRsMsg = executeScriptInScriptingComponent(executeScriptRqMsg);
//            logger.trace(executeScriptRsMsg.toString());
//
//            // get returned data
//            List<?> maintainRqMessages = null;
//
//            List<ScriptData> rsScriptData = executeScriptRsMsg.getScriptDataList();
//            for (ScriptData scriptData : rsScriptData) {
//
//                if (scriptData.getName().getValue().equals("resultingMaintainMessages")) {
//
//                    // result
//                    NbcDatatypeList nbcDatatypeList = (NbcDatatypeList) scriptData.getValue();
//                    maintainRqMessages = nbcDatatypeList.getNbcDatatypeList();
//
//                } else if (scriptData.getName().getValue().equals("logTraces")) {
//
//                    // logTraces
//                    LogTraceWrapper logTraceWrapper = (LogTraceWrapper) scriptData.getValue();
//                    LogTrace logTraceObject = logTraceWrapper.getLogTrace();
//
//                    if (logTraceObject == null) {
//                        logger.warning("No log traces returned from the script!");
//                        logTraces.append(formatLogLine("WARN",
//                                "No log traces returned from the script!"));
//                    } else {
//                        logTraces.append(logTraceObject.getValue());
//                    }
//
//                } else if (scriptData.getName().getValue().equals("errorTraces")) {
//
//                    // errorTraces
//                    LogTraceWrapper logTraceWrapper = (LogTraceWrapper) scriptData.getValue();
//                    LogTrace logTraceObject = logTraceWrapper.getLogTrace();
//
//                    if (logTraceObject == null) {
//                        logger.warning("No error traces returned from the script!");
//                        logTraces.append(formatLogLine("WARN",
//                                "No error traces returned from the script!"));
//                    } else {
//                        errorTraces.append(logTraceObject.getValue());
//                    }
//
//                } else {
//
//                    String logLine = "Unknown response data name found: '"
//                            + scriptData.getName()
//                            + "'.";
//                    logger.error(logLine);
//                    logTraces.append(formatLogLine("ERROR", logLine));
//
//                    failedFlag = true;
//                }
//            }
//
//            // maintain imported objects
//            if (failedFlag == false && maintainRqMessages != null && maintainRqMessages.size() > 0) {
//
//                // get access to the maintain service operation
//                Service service = getMaintainServiceViaReflection(rqMsg);
//                Method serviceOperationMethod = getMaintainServiceOperationMethodViaReflection(
//                        rqMsg, service);
//
//                // call the maintain-service for each maintain request message
//                // Class<?> maintainRqMsgClass = maintainRqMessages.get(0).getClass();
//                for (Object curMaintainRqMsg : maintainRqMessages) {
//
//                    // pack the request message into a service request message
//                    ServiceRequest curServiceRq = new ServiceRequest(super.getContext());
//                    ServiceMessage serviceMessage = (ServiceMessage) curMaintainRqMsg;
//                    curServiceRq.setRequestMessage(serviceMessage);
//
//                    String logLine = "Maintaining NABUCCO datatype with service '"
//                            + service.toString()
//                            + "'"
//                            + " and service operation '"
//                            + serviceOperationMethod.toGenericString()
//                            + "'...";
//                    logger.info(logLine);
//                    logTraces.append(formatLogLine("INFO", logLine));
//
//                    Object serviceRsMsg = serviceOperationMethod.invoke(service, curServiceRq);
//                    logger.trace("serviceRsMsg = " + serviceRsMsg);
//                }
//            }
//
//        } catch (Exception e) {
//
//            failedFlag = true;
//
//            e.printStackTrace();
//            logger.error("Error during executing the import script or maintaining the imported objects:");
//            logTraces
//                    .append(formatLogLine("ERROR",
//                            "Error during executing the import script or maintaining the imported objects:"));
//            errorTraces
//                    .append(formatLogLine("ERROR",
//                            "Error during executing the import script or maintaining the imported objects:"));
//
//            logTraces.append(formatLogLine("ERROR", writeStackTraceIntoString(e)));
//            errorTraces.append(formatLogLine("ERROR", writeStackTraceIntoString(e)));
//        }
//
//        // create importJob containing the results of the import job executed here
//        try {
//
//            // measure end time of this import job
//            java.util.Date endTime = new java.util.Date();
//            DateTime nbcEndTime = new DateTime();
//            nbcEndTime.setValue(endTime.getTime());
//
//            importJob.setEndTime(nbcEndTime);
//            importJob.setErrorTraces(errorTraces.toString());
//            importJob.setLogTraces(logTraces.toString());
//
//            if (dataToImport != null) {
//                byte[] bytes = dataToImport.getBytes();
//                importJob.setSourceData(bytes);
//            }
//
//            if (failedFlag == false) {
//                importJob.setState(ImportStateType.SUCCEEDED);
//            } else {
//                importJob.setState(ImportStateType.FAILED);
//            }
//
//            importJob.setDatatypeState(DatatypeState.INITIALIZED);
//
//            // maintain the import job
//            ImportJobMaintainMsg maintainRsMsg = maintainImportJob(importJob);
//            ImportJob maintainedImportJob = maintainRsMsg.getImportJob();
//
//            rsMsg.setExecutedImportJob(maintainedImportJob);
//
//        } catch (Exception e) {
//
//            // case: error during maintaining the import job
//            logger.error("Error during maintaining the ImportJob:");
//            e.printStackTrace();
//            rsMsg.setExecutedImportJob(importJob);
//        }
//
//        logger.debug("END executeImport");
//
//        return rsMsg;
//    }
//
//    private String formatLogLine(String logLevel, String logLine) {
//
//        StringBuilder sb = new StringBuilder();
//
//        // write current time
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss,SSS");
//        sb.append(simpleDateFormat.format(date));
//
//        // write loglevel
//        sb.append(" " + logLevel + " [ExecuteImportServiceHandlerImp] ");
//
//        // write current log line
//        sb.append(logLine + "\n");
//
//        return sb.toString();
//    }
//
//    private Service getMaintainServiceViaReflection(ExecuteImportRqMsg rqMsg) throws Exception {
//
//        // get the appropriate component locator class (e.g.:
//        // org.nabucco.framework.skm.employee.facade.component.EmployeeComponentLocator)
//        MaintainServiceConfiguration maintainServiceConfiguration = rqMsg.getConfiguration()
//                .getMaintainServiceConfiguration();
//        String componentLocatorClassName = deriveComponentLocatorClassNameFromComponentName(maintainServiceConfiguration
//                .getComponentName().getValue());
//        Class<?> componentLocatorClass = Class.forName(componentLocatorClassName);
//
//        // call the static getInstance method on the component locator class
//        Class<?>[] methodArgs = new Class<?>[0];
//        Method getInstanceMethod = componentLocatorClass.getMethod("getInstance", methodArgs);
//        ComponentLocator<?> componentLocator = (ComponentLocator<?>) getInstanceMethod.invoke(null);
//
//        // get the component
//        Object component = componentLocator.getComponent();
//
//        // get the service object of the component (e.g. MaintainEmployee)
//        Class<?> componentClass = component.getClass();
//        String serviceGetterMethodName = deriveServiceGetterMethodNameFromServiceName(maintainServiceConfiguration
//                .getServiceName().getValue());
//        Method getServiceMethod = componentClass.getMethod(serviceGetterMethodName, methodArgs);
//        Service service = (Service) getServiceMethod.invoke(component);
//
//        return service;
//    }
//
//    private Method getMaintainServiceOperationMethodViaReflection(ExecuteImportRqMsg rqMsg,
//            Service service) throws Exception {
//
//        // get the appropriate maintain service operation and call it
//        Class<?> serviceClass = service.getClass();
//        String serviceOperationName = rqMsg.getConfiguration().getMaintainServiceConfiguration()
//                .getServiceOperationName().getValue();
//
//        Class<?>[] maintainMethodArgs = new Class<?>[1];
//        maintainMethodArgs[0] = ServiceRequest.class;
//        Method serviceOperationMethod = serviceClass.getMethod(serviceOperationName,
//                maintainMethodArgs);
//
//        return serviceOperationMethod;
//    }
//
//    /**
//     * @param rqMsg
//     * @param importData
//     * @param scriptInputData
//     * @return
//     * @throws Exception
//     */
//    private ScriptExecutionMsg createExecuteScriptRqMsg(ExecuteImportRqMsg rqMsg,
//            String dataToImport) throws Exception {
//
//        // build message for executing scripts in the scripting component
//        ScriptExecutionMsg executeScriptRqMsg = new ScriptExecutionMsg();
//        Script script = new Script();
//        executeScriptRqMsg.setScript(script);
//
//        // define basic stuff in script
//        script.setName(rqMsg.getName());
//        script.setOwner(rqMsg.getOwner());
//        script.setDescription(rqMsg.getDescription());
//        script.setType(ScriptType.JAVASCRIPT);
//        script.setDatatypeState(DatatypeState.INITIALIZED);
//
//        // load script sourcecode via search service of support.scripting
//        String scriptName = rqMsg.getConfiguration().getScriptName().getValue();
//        String scriptSourceCode = retrieveScriptFromScriptingComponent(scriptName);
//        script.setSourcecode(scriptSourceCode);
//        logger.trace("script.SourceCode = " + script.getSourcecode().getValue());
//
//        // set the input data (date to import)
//        ImportData importData = new ImportData();
//        importData.setText(dataToImport);
//        ScriptData scriptInputData = new ScriptData();
//        scriptInputData.setName("importData");
//        scriptInputData.setDescription("The data to import: e.g. hr.xml");
//        scriptInputData.setValue(importData);
//        script.getScriptInputDataList().add(scriptInputData);
//
//        // define and declare the output data of script
//        // output data: result
//        ScriptData outputDataResult = new ScriptData();
//        outputDataResult.setName("resultingMaintainMessages");
//        outputDataResult
//                .setDescription("The output / result of the script must be contained in this variable after execution of the script)");
//        outputDataResult.setValue(new NbcDatatypeList());
//        script.getScriptOutputDataList().add(outputDataResult);
//
//        // output data: logTrace
//        ScriptData outputDataLogTrace = new ScriptData();
//        outputDataLogTrace.setName("logTraces");
//        outputDataLogTrace
//                .setDescription("The log trace of the script must be contained in this variable after execution of the script)");
//        outputDataLogTrace.setValue(new LogTraceWrapper());
//        script.getScriptOutputDataList().add(outputDataLogTrace);
//
//        // output data: errorTrace
//        ScriptData outputDataErrorTrace = new ScriptData();
//        outputDataErrorTrace.setName("errorTraces");
//        outputDataErrorTrace
//                .setDescription("The error trace of the script must be contained in this variable after execution of the script)");
//        outputDataErrorTrace.setValue(new LogTraceWrapper());
//        script.getScriptOutputDataList().add(outputDataErrorTrace);
//
//        return executeScriptRqMsg;
//    }
//
//    private String writeStackTraceIntoString(Exception e) {
//
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw, true);
//        e.printStackTrace(pw);
//        pw.flush();
//        sw.flush();
//        return sw.toString();
//    }
//
//    private ImportJobMaintainMsg maintainImportJob(ImportJob importJob) throws Exception {
//
//        ImportJobMaintainMsg rqMsg = new ImportJobMaintainMsg();
//        rqMsg.setImportJob(importJob);
//
//        ServiceRequest<ImportJobMaintainMsg> rq = new ServiceRequest<ImportJobMaintainMsg>(
//                super.getContext());
//        rq.setRequestMessage(rqMsg);
//
//        ImportingComponentLocator importingComponentLocator = ImportingComponentLocator
//                .getInstance();
//        ImportingComponent importingComponent = importingComponentLocator.getComponent();
//
//        ServiceResponse<ImportJobMaintainMsg> rs = importingComponent.getMaintainImporting()
//                .maintainImportJob(rq);
//
//        return rs.getResponseMessage();
//    }
//
//    private String deriveComponentLocatorClassNameFromComponentName(String componentName) {
//
//        String componentLocatorClassName = componentName + "Locator";
//        return componentLocatorClassName;
//    }
//
//    private String deriveServiceGetterMethodNameFromServiceName(String serviceName) {
//
//        StringBuilder serviceGetterMethodName = new StringBuilder();
//
//        serviceGetterMethodName.append("get");
//        serviceGetterMethodName.append(serviceName.substring(0, 1).toUpperCase());
//        serviceGetterMethodName.append(serviceName.substring(1));
//
//        return serviceGetterMethodName.toString();
//    }
//
//    /**
//     * For the sake of first tests simplified URL resolution method.
//     * 
//     * @param url
//     * @return
//     */
//    private String getDataToImport(Url url) throws Exception {
//
//        final String LINE_SEPERATOR = System.getProperty("line.separator");
//
//        String filepath = url.getValue();
//
//        if (filepath.startsWith("file:")) {
//            filepath = filepath.substring(5);
//        }
//
//        File file = new File(filepath);
//        Reader reader = new FileReader(file);
//        BufferedReader bufferedReader = new BufferedReader(reader);
//
//        StringBuilder totalInputData = new StringBuilder();
//        String curLine;
//        while ((curLine = bufferedReader.readLine()) != null) {
//            totalInputData.append(curLine);
//            totalInputData.append(LINE_SEPERATOR);
//        }
//
//        return totalInputData.toString();
//    }
//
//    private ScriptExecutionResultMsg executeScriptInScriptingComponent(
//            ScriptExecutionMsg executeScriptRqMsg) throws Exception {
//
//        ServiceRequest<ScriptExecutionMsg> rq = new ServiceRequest<ScriptExecutionMsg>(
//                super.getContext());
//        rq.setRequestMessage(executeScriptRqMsg);
//
//        ScriptComponentLocator scriptComponentLocator = ScriptComponentLocator.getInstance();
//        ScriptComponent scriptComponent = scriptComponentLocator.getComponent();
//
//        ServiceResponse<ScriptExecutionResultMsg> rs = scriptComponent.getScriptService()
//                .executeScript(rq);
//
//        return rs.getResponseMessage();
//    }
//
//    private String retrieveScriptFromScriptingComponent(String scriptName) throws Exception {
//
//        ScriptSearchMsg rqMsg = new ScriptSearchMsg();
//        rqMsg.setName(new Name(scriptName));
//
//        ServiceRequest<ScriptSearchMsg> rq = new ServiceRequest<ScriptSearchMsg>(super.getContext());
//        rq.setRequestMessage(rqMsg);
//
//        ScriptComponentLocator scriptComponentLocator = ScriptComponentLocator.getInstance();
//        ScriptComponent scriptComponent = scriptComponentLocator.getComponent();
//
//        ServiceResponse<ScriptListMsg> rs = scriptComponent.getScriptSearchService().searchScripts(
//                rq);
//
//        List<Script> scripts = rs.getResponseMessage().getScriptList();
//        if (scripts.size() == 0) {
//            throw new Exception("Script with name '" + scriptName + "' not found!");
//        }
//        if (scripts.size() > 1) {
//            logger.info("More than one script with name '" + scriptName + "' found.");
//        }
//
//        ScriptSourceCode scriptSourceCode = scripts.get(0).getSourcecode();
//        return scriptSourceCode.getValue();
//    }
// }
