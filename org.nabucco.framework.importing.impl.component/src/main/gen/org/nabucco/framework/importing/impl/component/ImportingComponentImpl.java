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
package org.nabucco.framework.importing.impl.component;

import org.nabucco.framework.base.facade.component.handler.PostConstructHandler;
import org.nabucco.framework.base.facade.component.handler.PreDestroyHandler;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.base.impl.component.ComponentSupport;
import org.nabucco.framework.importing.facade.component.ImportingComponentLocal;
import org.nabucco.framework.importing.facade.component.ImportingComponentRemote;
import org.nabucco.framework.importing.facade.service.execute.ExecuteImporting;
import org.nabucco.framework.importing.facade.service.maintain.MaintainImporting;
import org.nabucco.framework.importing.facade.service.produce.ProduceImporting;
import org.nabucco.framework.importing.facade.service.resolve.ResolveImporting;
import org.nabucco.framework.importing.facade.service.search.SearchImporting;

/**
 * ImportingComponentImpl<p/>Component for XML, CSV, etc. imports.. The component was named 'Importing' as opposed to 'Import', because 'import' is a reserved java keyword.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-22
 */
public class ImportingComponentImpl extends ComponentSupport implements ImportingComponentLocal,
        ImportingComponentRemote {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ImportingComponent";

    /** Constructs a new ImportingComponentImpl instance. */
    public ImportingComponentImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PostConstructHandler handler = injector.inject(PostConstructHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No post construct handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PreDestroyHandler handler = injector.inject(PreDestroyHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No pre destroy handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }

    @Override
    public String getJndiName() {
        return JNDI_NAME;
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.COMPONENT_RELATION_SERVICE_REMOTE,
                ComponentRelationService.class);
    }

    @Override
    public ComponentRelationService getComponentRelationServiceLocal() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.COMPONENT_RELATION_SERVICE_LOCAL,
                ComponentRelationService.class);
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.QUERY_FILTER_SERVICE_REMOTE, QueryFilterService.class);
    }

    @Override
    public QueryFilterService getQueryFilterServiceLocal() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.QUERY_FILTER_SERVICE_LOCAL, QueryFilterService.class);
    }

    @Override
    public ProduceImporting getProduceImportingLocal() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.PRODUCE_IMPORTING_LOCAL, ProduceImporting.class);
    }

    @Override
    public ProduceImporting getProduceImporting() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.PRODUCE_IMPORTING_REMOTE, ProduceImporting.class);
    }

    @Override
    public ResolveImporting getResolveImportingLocal() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.RESOLVE_IMPORTING_LOCAL, ResolveImporting.class);
    }

    @Override
    public ResolveImporting getResolveImporting() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.RESOLVE_IMPORTING_REMOTE, ResolveImporting.class);
    }

    @Override
    public ExecuteImporting getExecuteImportingLocal() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.EXECUTE_IMPORTING_LOCAL, ExecuteImporting.class);
    }

    @Override
    public ExecuteImporting getExecuteImporting() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.EXECUTE_IMPORTING_REMOTE, ExecuteImporting.class);
    }

    @Override
    public MaintainImporting getMaintainImportingLocal() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.MAINTAIN_IMPORTING_LOCAL, MaintainImporting.class);
    }

    @Override
    public MaintainImporting getMaintainImporting() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.MAINTAIN_IMPORTING_REMOTE, MaintainImporting.class);
    }

    @Override
    public SearchImporting getSearchImportingLocal() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.SEARCH_IMPORTING_LOCAL, SearchImporting.class);
    }

    @Override
    public SearchImporting getSearchImporting() throws ServiceException {
        return super.lookup(ImportingComponentJndiNames.SEARCH_IMPORTING_REMOTE, SearchImporting.class);
    }
}
