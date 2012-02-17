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
package org.nabucco.framework.importing.facade.service.search;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.search.ImportConfigurationSearchRq;
import org.nabucco.framework.importing.facade.message.search.ImportJobSearchRq;

/**
 * SearchImporting<p/>Search Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public interface SearchImporting extends Service {

    /**
     * Missing description at method searchImportConfiguration.
     *
     * @param rq the ServiceRequest<ImportConfigurationSearchRq>.
     * @return the ServiceResponse<ImportConfigurationListMsg>.
     * @throws SearchException
     */
    ServiceResponse<ImportConfigurationListMsg> searchImportConfiguration(ServiceRequest<ImportConfigurationSearchRq> rq)
            throws SearchException;

    /**
     * Missing description at method searchImportJob.
     *
     * @param rq the ServiceRequest<ImportJobSearchRq>.
     * @return the ServiceResponse<ImportJobListMsg>.
     * @throws SearchException
     */
    ServiceResponse<ImportJobListMsg> searchImportJob(ServiceRequest<ImportJobSearchRq> rq) throws SearchException;
}
