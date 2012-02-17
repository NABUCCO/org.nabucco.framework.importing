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

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.nabucco.framework.base.facade.datatype.DateTime;
import org.nabucco.framework.base.facade.datatype.importing.ImportContainer;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.datatype.serialization.SerializationConstants;
import org.nabucco.framework.base.facade.datatype.text.TextContent;
import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.datatype.ImportStateType;
import org.nabucco.framework.importing.facade.datatype.ImportType;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRq;
import org.nabucco.framework.support.scripting.facade.datatype.Script;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * XmlImportHandler
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class XmlImportHandler implements ImportHandler {

    private static final String LINE_SEPARATOR = "\n";

    private static final String TAG_EXPORT = "Export";

    private static final String TAG_EXPORT_JOB = TAG_EXPORT + "Job";

    private static final String ATTRIB_CONFIGNAME = "configurationName";

    private static final String ATTRIB_OWNER = "owner";

    private static final String ATTRIB_VERSION = "version";

    private static final NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(XmlImportHandler.class);

    private ServiceMessageContext context;

    @Override
    public ImportType getType() {
        return ImportType.XML;
    }

    /**
     * @param context
     */
    public XmlImportHandler(ServiceMessageContext context) {
        this.context = context;
    }

    @Override
    public ImportJob executeImport(ImportConfiguration conf, ExecuteImportRq request) throws ImportException {
        TextContent content = request.getContainer().getContent();
        ImportJob importJob = ExecuteImportUtil.produceImportJob(this.context, conf);
        String xmlContent = content.getValue();

        Document xmlDoc = parseFromString(xmlContent);

        NodeList exportTag = xmlDoc.getElementsByTagName(TAG_EXPORT);

        if (exportTag.getLength() != 1) {
            throw new ImportException("Encountered malformed xml during import: more than one Export tag");
        }

        Node exportItem = exportTag.item(0);
        if (exportItem.getNodeType() == Node.ELEMENT_NODE) {
            Element exportNode = (Element) exportItem;
            if (exportNode.hasAttribute(ATTRIB_OWNER)) {
                request.getContext().setOwner(exportNode.getAttribute(ATTRIB_OWNER));
            }
            if (exportNode.hasAttribute(ATTRIB_VERSION)) {
                // TODO: validation ?
            }
        }

        NodeList exportJobEntries = xmlDoc.getElementsByTagName(TAG_EXPORT_JOB);

        for (int i = 0; i < exportJobEntries.getLength(); i++) {
            Node item = exportJobEntries.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element exportJob = (Element) item;
                if (exportJob.hasAttribute(ATTRIB_CONFIGNAME)
                        && (exportJob.getAttribute(ATTRIB_CONFIGNAME).compareTo(conf.getName().getValue()) == 0)) {
                    logger.info("found matching configuration: " + conf.getName().getValue());
                    return handleImport(conf, getFirstChildElement(item), request);
                }
            }
        }

        return importJob;
    }

    private static final Node getFirstChildElement(Node n) {
        NodeList childNodes = n.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                return childNodes.item(i);
            }
        }
        return null;
    }

    private ImportJob handleImport(ImportConfiguration conf, Node item, ExecuteImportRq rq) throws ImportException {
        ImportJob importJob = ExecuteImportUtil.produceImportJob(this.context, conf);
        importJob.setStartTime(new DateTime(System.currentTimeMillis()));
        importJob.setState(ImportStateType.RUNNING);
        try {
            StringWriter sw = new StringWriter();

            OutputFormat xmlFormat = new OutputFormat();
            xmlFormat.setEncoding(SerializationConstants.CHARSET);
            xmlFormat.setLineSeparator(LINE_SEPARATOR);
            xmlFormat.setIndent(2);
            xmlFormat.setIndenting(true);

            XMLSerializer xmlSerializer = new XMLSerializer(sw, xmlFormat);

            xmlSerializer.serialize(item);

            Script importScript = ExecuteImportUtil.resolveScript(this.context, conf);

            ImportContainer container = new ImportContainer();
            container.setContent(sw.toString());
            container.setResourceData(rq.getContainer().getResourceData());

            importScript = ExecuteImportUtil.prepareScriptData(importScript, container, rq.getContext());
            try {
                rq.setContext(ExecuteImportUtil.invokeScript(this.context, importScript));
            } catch (ImportException e) {
                failed(importJob);
                return importJob;
            }

        } catch (IOException e) {
            failed(importJob);
            String msg = "unable to handle xml content";
            logger.error(msg);
        }
        successful(importJob);
        return importJob;
    }

    private Document parseFromString(String input) throws ImportException {
        Document result = null;
        StringReader sr = new StringReader(input);
        InputSource is = new InputSource();
        is.setEncoding(SerializationConstants.CHARSET);
        is.setCharacterStream(sr);
        try {
            result = ExecuteImportUtil.getDocumentBuilder().parse(is);
        } catch (SAXException e) {
            String msg = "unable to parse given input: " + input.substring(0, 20);
            logger.error(msg);
            new ImportException(msg, e);
        } catch (IOException e) {
            String msg = "unable to parse given input: " + input.substring(0, 20);
            logger.error(msg);
        }
        return result;
    }

    private static void failed(ImportJob ij) {
        ij.setState(ImportStateType.FAILED);
        ij.setEndTime(new DateTime(System.currentTimeMillis()));
    }

    private static void successful(ImportJob ij) {
        ij.setState(ImportStateType.SUCCEEDED);
        ij.setEndTime(new DateTime(System.currentTimeMillis()));
    }
}
