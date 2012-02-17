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
package org.nabucco.framework.importing.ui.rcp.communication.search;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.search.ImportConfigurationSearchRq;
import org.nabucco.framework.importing.facade.message.search.ImportJobSearchRq;
import org.nabucco.framework.importing.facade.service.search.SearchImporting;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * SearchImportingDelegate<p/>Search Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class SearchImportingDelegate extends ServiceDelegateSupport {

    private SearchImporting service;

    /**
     * Constructs a new SearchImportingDelegate instance.
     *
     * @param service the SearchImporting.
     */
    public SearchImportingDelegate(SearchImporting service) {
        super();
        this.service = service;
    }

    /**
     * SearchImportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportConfigurationSearchRq.
     * @return the ImportConfigurationListMsg.
     * @throws ClientException
     */
    public ImportConfigurationListMsg searchImportConfiguration(ImportConfigurationSearchRq message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ImportConfigurationSearchRq> request = new ServiceRequest<ImportConfigurationSearchRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchImportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchImporting.class, "searchImportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchImporting.searchImportConfiguration");
    }

    /**
     * SearchImportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportJobSearchRq.
     * @return the ImportJobListMsg.
     * @throws ClientException
     */
    public ImportJobListMsg searchImportJob(ImportJobSearchRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ImportJobSearchRq> request = new ServiceRequest<ImportJobSearchRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportJobListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchImportJob(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchImporting.class, "searchImportJob", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchImporting.searchImportJob");
    }
}
