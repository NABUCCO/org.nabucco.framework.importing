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
import org.nabucco.framework.importing.facade.component.ImportingComponent;
import org.nabucco.framework.importing.facade.service.execute.ExecuteImporting;
import org.nabucco.framework.importing.facade.service.maintain.MaintainImporting;
import org.nabucco.framework.importing.facade.service.produce.ProduceImporting;
import org.nabucco.framework.importing.facade.service.resolve.ResolveImporting;
import org.nabucco.framework.importing.facade.service.search.SearchImporting;

/**
 * ImportingComponentLocalProxy<p/>Component for XML, CSV, etc. imports.. The component was named 'Importing' as opposed to 'Import', because 'import' is a reserved java keyword.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-22
 */
public class ImportingComponentLocalProxy implements ImportingComponent {

    private static final long serialVersionUID = 1L;

    private final ImportingComponentLocal delegate;

    /**
     * Constructs a new ImportingComponentLocalProxy instance.
     *
     * @param delegate the ImportingComponentLocal.
     */
    public ImportingComponentLocalProxy(ImportingComponentLocal delegate) {
        super();
        if ((delegate == null)) {
            throw new IllegalArgumentException("Cannot create local proxy for component [null].");
        }
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getId();
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public String getJndiName() {
        return this.delegate.getJndiName();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.delegate.getComponentRelationServiceLocal();
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return this.delegate.getQueryFilterServiceLocal();
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }

    @Override
    public ProduceImporting getProduceImporting() throws ServiceException {
        return this.delegate.getProduceImportingLocal();
    }

    @Override
    public ResolveImporting getResolveImporting() throws ServiceException {
        return this.delegate.getResolveImportingLocal();
    }

    @Override
    public ExecuteImporting getExecuteImporting() throws ServiceException {
        return this.delegate.getExecuteImportingLocal();
    }

    @Override
    public MaintainImporting getMaintainImporting() throws ServiceException {
        return this.delegate.getMaintainImportingLocal();
    }

    @Override
    public SearchImporting getSearchImporting() throws ServiceException {
        return this.delegate.getSearchImportingLocal();
    }
}
