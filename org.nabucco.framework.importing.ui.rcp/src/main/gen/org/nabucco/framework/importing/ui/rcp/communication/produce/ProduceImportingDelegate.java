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
package org.nabucco.framework.importing.ui.rcp.communication.produce;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.facade.service.produce.ProduceImporting;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ProduceImportingDelegate<p/>Produce Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-08-06
 */
public class ProduceImportingDelegate extends ServiceDelegateSupport {

    private ProduceImporting service;

    /**
     * Constructs a new ProduceImportingDelegate instance.
     *
     * @param service the ProduceImporting.
     */
    public ProduceImportingDelegate(ProduceImporting service) {
        super();
        this.service = service;
    }

    /**
     * ProduceImportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the ImportConfigurationMsg.
     * @throws ClientException
     */
    public ImportConfigurationMsg produceImportConfiguration(EmptyServiceMessage message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceImportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceImporting.class, "produceImportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ProduceImporting.produceImportConfiguration");
    }

    /**
     * ProduceImportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the EmptyServiceMessage.
     * @return the ImportJobMsg.
     * @throws ClientException
     */
    public ImportJobMsg produceImportJob(EmptyServiceMessage message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<EmptyServiceMessage> request = new ServiceRequest<EmptyServiceMessage>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportJobMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceImportJob(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceImporting.class, "produceImportJob", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ProduceImporting.produceImportJob");
    }
}
