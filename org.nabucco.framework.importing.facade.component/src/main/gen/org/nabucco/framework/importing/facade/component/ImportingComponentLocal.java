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
package org.nabucco.framework.importing.facade.component;

import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.importing.facade.service.execute.ExecuteImporting;
import org.nabucco.framework.importing.facade.service.maintain.MaintainImporting;
import org.nabucco.framework.importing.facade.service.produce.ProduceImporting;
import org.nabucco.framework.importing.facade.service.resolve.ResolveImporting;
import org.nabucco.framework.importing.facade.service.search.SearchImporting;

/**
 * ImportingComponentLocal<p/>Component for XML, CSV, etc. imports.. The component was named 'Importing' as opposed to 'Import', because 'import' is a reserved java keyword.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-22
 */
public interface ImportingComponentLocal extends ImportingComponent {

    /**
     * Getter for the ComponentRelationServiceLocal.
     *
     * @return the ComponentRelationService.
     * @throws ServiceException
     */
    ComponentRelationService getComponentRelationServiceLocal() throws ServiceException;

    /**
     * Getter for the QueryFilterServiceLocal.
     *
     * @return the QueryFilterService.
     * @throws ServiceException
     */
    QueryFilterService getQueryFilterServiceLocal() throws ServiceException;

    /**
     * Getter for the ProduceImportingLocal.
     *
     * @return the ProduceImporting.
     * @throws ServiceException
     */
    ProduceImporting getProduceImportingLocal() throws ServiceException;

    /**
     * Getter for the ResolveImportingLocal.
     *
     * @return the ResolveImporting.
     * @throws ServiceException
     */
    ResolveImporting getResolveImportingLocal() throws ServiceException;

    /**
     * Getter for the ExecuteImportingLocal.
     *
     * @return the ExecuteImporting.
     * @throws ServiceException
     */
    ExecuteImporting getExecuteImportingLocal() throws ServiceException;

    /**
     * Getter for the MaintainImportingLocal.
     *
     * @return the MaintainImporting.
     * @throws ServiceException
     */
    MaintainImporting getMaintainImportingLocal() throws ServiceException;

    /**
     * Getter for the SearchImportingLocal.
     *
     * @return the SearchImporting.
     * @throws ServiceException
     */
    SearchImporting getSearchImportingLocal() throws ServiceException;
}
