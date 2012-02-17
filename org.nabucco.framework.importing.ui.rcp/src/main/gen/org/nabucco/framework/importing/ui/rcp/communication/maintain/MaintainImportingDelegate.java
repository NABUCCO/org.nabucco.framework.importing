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
package org.nabucco.framework.importing.ui.rcp.communication.maintain;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.facade.message.maintain.ImportConfigurationLinkRq;
import org.nabucco.framework.importing.facade.service.maintain.MaintainImporting;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * MaintainImportingDelegate<p/>Maintain Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class MaintainImportingDelegate extends ServiceDelegateSupport {

    private MaintainImporting service;

    /**
     * Constructs a new MaintainImportingDelegate instance.
     *
     * @param service the MaintainImporting.
     */
    public MaintainImportingDelegate(MaintainImporting service) {
        super();
        this.service = service;
    }

    /**
     * MaintainImportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportConfigurationMsg.
     * @return the ImportConfigurationMsg.
     * @throws ClientException
     */
    public ImportConfigurationMsg maintainImportConfiguration(ImportConfigurationMsg message,
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
                response = service.maintainImportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainImporting.class, "maintainImportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainImporting.maintainImportConfiguration");
    }

    /**
     * MaintainImportConfigurationList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportConfigurationListMsg.
     * @return the ImportConfigurationListMsg.
     * @throws ClientException
     */
    public ImportConfigurationListMsg maintainImportConfigurationList(ImportConfigurationListMsg message,
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
                response = service.maintainImportConfigurationList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainImporting.class, "maintainImportConfigurationList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainImporting.maintainImportConfigurationList");
    }

    /**
     * LinkImportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportConfigurationLinkRq.
     * @return the ImportConfigurationMsg.
     * @throws ClientException
     */
    public ImportConfigurationMsg linkImportConfiguration(ImportConfigurationLinkRq message,
            ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<ImportConfigurationLinkRq> request = new ServiceRequest<ImportConfigurationLinkRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.linkImportConfiguration(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainImporting.class, "linkImportConfiguration", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainImporting.linkImportConfiguration");
    }

    /**
     * MaintainImportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportJobMsg.
     * @return the ImportJobMsg.
     * @throws ClientException
     */
    public ImportJobMsg maintainImportJob(ImportJobMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ImportJobMsg> request = new ServiceRequest<ImportJobMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportJobMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainImportJob(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainImporting.class, "maintainImportJob", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainImporting.maintainImportJob");
    }

    /**
     * MaintainImportJobList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ImportJobListMsg.
     * @return the ImportJobListMsg.
     * @throws ClientException
     */
    public ImportJobListMsg maintainImportJobList(ImportJobListMsg message, ServiceSubContext... subContexts)
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
                response = service.maintainImportJobList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainImporting.class, "maintainImportJobList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainImporting.maintainImportJobList");
    }
}
