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
package org.nabucco.framework.importing.ui.web.communication.maintain;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.datatype.session.NabuccoSession;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateSupport;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.facade.message.maintain.ImportConfigurationLinkRq;
import org.nabucco.framework.importing.facade.service.maintain.MaintainImporting;

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
     * @param session the NabuccoSession.
     * @param message the ImportConfigurationMsg.
     * @return the ImportConfigurationMsg.
     * @throws MaintainException
     */
    public ImportConfigurationMsg maintainImportConfiguration(ImportConfigurationMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ImportConfigurationMsg> request = new ServiceRequest<ImportConfigurationMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainImporting.maintainImportConfiguration");
    }

    /**
     * MaintainImportConfigurationList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ImportConfigurationListMsg.
     * @return the ImportConfigurationListMsg.
     * @throws MaintainException
     */
    public ImportConfigurationListMsg maintainImportConfigurationList(ImportConfigurationListMsg message,
            NabuccoSession session, ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ImportConfigurationListMsg> request = new ServiceRequest<ImportConfigurationListMsg>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException(
                "Cannot execute service operation: MaintainImporting.maintainImportConfigurationList");
    }

    /**
     * LinkImportConfiguration.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ImportConfigurationLinkRq.
     * @return the ImportConfigurationMsg.
     * @throws MaintainException
     */
    public ImportConfigurationMsg linkImportConfiguration(ImportConfigurationLinkRq message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ImportConfigurationLinkRq> request = new ServiceRequest<ImportConfigurationLinkRq>(
                super.createServiceContext(session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportConfigurationMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainImporting.linkImportConfiguration");
    }

    /**
     * MaintainImportJob.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ImportJobMsg.
     * @return the ImportJobMsg.
     * @throws MaintainException
     */
    public ImportJobMsg maintainImportJob(ImportJobMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ImportJobMsg> request = new ServiceRequest<ImportJobMsg>(super.createServiceContext(session,
                subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportJobMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainImporting.maintainImportJob");
    }

    /**
     * MaintainImportJobList.
     *
     * @param subContexts the ServiceSubContext....
     * @param session the NabuccoSession.
     * @param message the ImportJobListMsg.
     * @return the ImportJobListMsg.
     * @throws MaintainException
     */
    public ImportJobListMsg maintainImportJobList(ImportJobListMsg message, NabuccoSession session,
            ServiceSubContext... subContexts) throws MaintainException {
        ServiceRequest<ImportJobListMsg> request = new ServiceRequest<ImportJobListMsg>(super.createServiceContext(
                session, subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ImportJobListMsg> response = null;
        Exception exception = null;
        if ((this.service != null)) {
            super.handleRequest(request, session);
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
                super.handleResponse(response, session);
                return response.getResponseMessage();
            }
        }
        throw new MaintainException("Cannot execute service operation: MaintainImporting.maintainImportJobList");
    }
}
