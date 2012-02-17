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
package org.nabucco.framework.importing.ui.rcp.communication.resolve;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.facade.service.resolve.ResolveImporting;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ResolveImportingDelegate<p/>Resolve Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-08-03
 */
public class ResolveImportingDelegate extends ServiceDelegateSupport {

    private ResolveImporting service;

    /**
     * Constructs a new ResolveImportingDelegate instance.
     *
     * @param service the ResolveImporting.
     */
    public ResolveImportingDelegate(ResolveImporting service) {
        super();
        this.service = service;
    }

    /**
     * ResolveImportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportConfigurationMsg.
     * @return the ImportConfigurationMsg.
     * @throws ClientException
     */
    public ImportConfigurationMsg resolveImportConfiguration(ImportConfigurationMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ImportConfigurationMsg> request = new ServiceRequest<ImportConfigurationMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveImportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveImporting.class, "resolveImportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveImporting.resolveImportConfiguration");
    }

    /**
     * ResolveImportConfigurationList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportConfigurationListMsg.
     * @return the ImportConfigurationListMsg.
     * @throws ClientException
     */
    public ImportConfigurationListMsg resolveImportConfigurationList(ImportConfigurationListMsg message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ImportConfigurationListMsg> request = new ServiceRequest<ImportConfigurationListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveImportConfigurationList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveImporting.class, "resolveImportConfigurationList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveImporting.resolveImportConfigurationList");
    }

    /**
     * ResolveImportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportJobMsg.
     * @return the ImportJobMsg.
     * @throws ClientException
     */
    public ImportJobMsg resolveImportJob(ImportJobMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ImportJobMsg> request = new ServiceRequest<ImportJobMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportJobMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveImportJob(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveImporting.class, "resolveImportJob", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveImporting.resolveImportJob");
    }

    /**
     * ResolveImportJobList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportJobListMsg.
     * @return the ImportJobListMsg.
     * @throws ClientException
     */
    public ImportJobListMsg resolveImportJobList(ImportJobListMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ImportJobListMsg> request = new ServiceRequest<ImportJobListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportJobListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveImportJobList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveImporting.class, "resolveImportJobList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveImporting.resolveImportJobList");
    }
}
