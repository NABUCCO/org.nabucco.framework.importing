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
package org.nabucco.framework.importing.facade.service.maintain;

import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.facade.message.maintain.ImportConfigurationLinkRq;

/**
 * MaintainImporting<p/>Maintain Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public interface MaintainImporting extends Service {

    /**
     * Missing description at method maintainImportConfiguration.
     *
     * @param rq the ServiceRequest<ImportConfigurationMsg>.
     * @return the ServiceResponse<ImportConfigurationMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ImportConfigurationMsg> maintainImportConfiguration(ServiceRequest<ImportConfigurationMsg> rq)
            throws MaintainException;

    /**
     * Missing description at method maintainImportConfigurationList.
     *
     * @param rq the ServiceRequest<ImportConfigurationListMsg>.
     * @return the ServiceResponse<ImportConfigurationListMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ImportConfigurationListMsg> maintainImportConfigurationList(
            ServiceRequest<ImportConfigurationListMsg> rq) throws MaintainException;

    /**
     * Missing description at method linkImportConfiguration.
     *
     * @param rq the ServiceRequest<ImportConfigurationLinkRq>.
     * @return the ServiceResponse<ImportConfigurationMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ImportConfigurationMsg> linkImportConfiguration(ServiceRequest<ImportConfigurationLinkRq> rq)
            throws MaintainException;

    /**
     * Missing description at method maintainImportJob.
     *
     * @param rq the ServiceRequest<ImportJobMsg>.
     * @return the ServiceResponse<ImportJobMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ImportJobMsg> maintainImportJob(ServiceRequest<ImportJobMsg> rq) throws MaintainException;

    /**
     * Missing description at method maintainImportJobList.
     *
     * @param rq the ServiceRequest<ImportJobListMsg>.
     * @return the ServiceResponse<ImportJobListMsg>.
     * @throws MaintainException
     */
    ServiceResponse<ImportJobListMsg> maintainImportJobList(ServiceRequest<ImportJobListMsg> rq)
            throws MaintainException;
}
