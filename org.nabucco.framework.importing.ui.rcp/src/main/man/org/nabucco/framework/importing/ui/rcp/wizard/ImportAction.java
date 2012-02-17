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
package org.nabucco.framework.importing.ui.rcp.wizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.nabucco.framework.base.facade.datatype.importing.ImportContainer;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.text.TextContent;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.datatype.ImportStateType;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRq;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRs;
import org.nabucco.framework.importing.ui.rcp.communication.ImportingComponentServiceDelegateFactory;
import org.nabucco.framework.importing.ui.rcp.communication.execute.ExecuteImportingDelegate;

/**
 * ImportAction
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ImportAction {

    private static final String CONTENT_XML = "content.xml";

    private ImportWizardModel model;

    private List<ImportConfiguration> configurations;

    /**
     * Creates a new {@link ImportAction} instance.
     * 
     * @param model
     *            the model
     * @param configurations
     *            the configurations
     */
    public ImportAction(ImportWizardModel model, List<ImportConfiguration> configurations) {
        this.model = model;
        this.configurations = configurations;
    }

    /**
     * Execute the file import.
     * 
     * @return <b>true</b> if the import finished successful, <b>false</b> if not
     * 
     * @throws ClientException
     */
    public boolean execute() throws ClientException {
        try {
            byte[] content = this.readFile();

            if (content == null) {
                return false;
            }

            List<ImportJob> jobs = this.executeImport(content);

            for (ImportJob job : jobs) {
                if (job.getState() != ImportStateType.SUCCEEDED) {
                    return false;
                }
            }

            return true;

        } catch (IOException ioe) {
            throw new ClientException("Error reading from file " + this.model.getDestination() + ".");
        }
    }

    /**
     * Executes the importing.
     * 
     * @param content
     *            the file content
     * 
     * @return the import result
     * 
     * @throws ClientException
     *             when the exeution failed
     */
    private List<ImportJob> executeImport(byte[] content) throws ClientException {

        ExecuteImportingDelegate importingService = ImportingComponentServiceDelegateFactory.getInstance()
                .getExecuteImporting();

        ExecuteImportRq request;
        try {
            request = this.createExecutionRequest(content);
        } catch (IOException e) {
            throw new ClientException("Unable to create import execution request.", e);
        }

        ExecuteImportRs response = importingService.executeImport(request);

        return response.getExecutedImportJobs();
    }

    /**
     * Read the imported file.
     * 
     * @return the file content, or null if the file is not valid
     * 
     * @throws IOException
     *             when an IO error occured during file reading
     */
    private byte[] readFile() throws IOException {
        File file = new File(this.model.getDestination());

        if (!file.exists()) {
            return null;
        }

        if (!file.isFile()) {
            return null;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[1024];
        while ((bytesRead = fis.read(buffer)) > -1) {
            baos.write(Arrays.copyOf(buffer, bytesRead));
        }
        fis.close();
        byte[] resultingByteArray = baos.toByteArray();
        baos.close();

        return resultingByteArray;
    }

    /**
     * Create the importing request message.
     * 
     * @param content
     *            the file content
     * 
     * @return the request message
     * @throws IOException
     * @throws ClientException
     */
    private ExecuteImportRq createExecutionRequest(byte[] content) throws IOException, ClientException {
        ExecuteImportRq request = new ExecuteImportRq();

        request.setContainer(prepareContainer(content));
        request.setContext(new ImportContext());

        if (this.model.isImportAll()) {
            request.getConfigurations().addAll(this.configurations);
        } else {
            request.getConfigurations().addAll(this.model.getConfigurations());
        }

        return request;
    }

    private ImportContainer prepareContainer(byte[] content) throws ClientException {
        ImportContainer result = new ImportContainer();

        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        ZipInputStream zis = new ZipInputStream(bais);
        ZipEntry current = null;

        try {
            while ((current = zis.getNextEntry()) != null) {
                if (current.getName().compareTo(CONTENT_XML) == 0) {
                    break;
                }
            }
        } catch (IOException ioe) {
            throw new ClientException("unable to extract content.xml from file", ioe);
        }

        if (current == null) {
            throw new ClientException("unable to find content.xml resource in container.");
        }

        String contentXml = "";

        try {
            ByteArrayOutputStream streamBuilder = new ByteArrayOutputStream();
            byte[] tempBuffer = new byte[1024];
            int readBytes = 0;
            while ((readBytes = zis.read(tempBuffer)) != -1) {
                streamBuilder.write(Arrays.copyOf(tempBuffer, readBytes));
            }
            zis.closeEntry();
            zis.close();
            contentXml = new String(streamBuilder.toByteArray());
            bais.close();
        } catch (IOException ioe) {
            throw new ClientException("unable to unpack content.xml", ioe);
        }

        TextContent tfc = new TextContent(contentXml);
        result.setContent(tfc);
        result.setResourceData(content);
        return result;
    }
}
