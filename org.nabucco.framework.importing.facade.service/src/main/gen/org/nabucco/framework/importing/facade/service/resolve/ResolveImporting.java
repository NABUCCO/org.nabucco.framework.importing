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
package org.nabucco.framework.importing.facade.service.resolve;

import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;

/**
 * ResolveImporting<p/>Resolve Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-08-03
 */
public interface ResolveImporting extends Service {

    /**
     * Missing description at method resolveImportConfiguration.
     *
     * @param rq the ServiceRequest<ImportConfigurationMsg>.
     * @return the ServiceResponse<ImportConfigurationMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ImportConfigurationMsg> resolveImportConfiguration(ServiceRequest<ImportConfigurationMsg> rq)
            throws ResolveException;

    /**
     * Missing description at method resolveImportConfigurationList.
     *
     * @param rq the ServiceRequest<ImportConfigurationListMsg>.
     * @return the ServiceResponse<ImportConfigurationListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ImportConfigurationListMsg> resolveImportConfigurationList(
            ServiceRequest<ImportConfigurationListMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveImportJob.
     *
     * @param rq the ServiceRequest<ImportJobMsg>.
     * @return the ServiceResponse<ImportJobMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ImportJobMsg> resolveImportJob(ServiceRequest<ImportJobMsg> rq) throws ResolveException;

    /**
     * Missing description at method resolveImportJobList.
     *
     * @param rq the ServiceRequest<ImportJobListMsg>.
     * @return the ServiceResponse<ImportJobListMsg>.
     * @throws ResolveException
     */
    ServiceResponse<ImportJobListMsg> resolveImportJobList(ServiceRequest<ImportJobListMsg> rq) throws ResolveException;
}
