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
package org.nabucco.framework.importing.ui.rcp.communication.execute;

import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRq;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRs;
import org.nabucco.framework.importing.facade.service.execute.ExecuteImporting;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ExecuteImportingDelegate<p/>Services that are used to start / execute import operations.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class ExecuteImportingDelegate extends ServiceDelegateSupport {

    private ExecuteImporting service;

    /**
     * Constructs a new ExecuteImportingDelegate instance.
     *
     * @param service the ExecuteImporting.
     */
    public ExecuteImportingDelegate(ExecuteImporting service) {
        super();
        this.service = service;
    }

    /**
     * ExecuteImport.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the ExecuteImportRq.
     * @return the ExecuteImportRs.
     * @throws ClientException
     */
    public ExecuteImportRs executeImport(ExecuteImportRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<ExecuteImportRq> request = new ServiceRequest<ExecuteImportRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<ExecuteImportRs> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.executeImport(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ExecuteImporting.class, "executeImport", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ExecuteImporting.executeImport");
    }
}
