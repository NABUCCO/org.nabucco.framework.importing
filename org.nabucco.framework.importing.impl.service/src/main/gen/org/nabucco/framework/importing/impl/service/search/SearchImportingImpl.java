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
package org.nabucco.framework.importing.impl.service.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.search.ImportConfigurationSearchRq;
import org.nabucco.framework.importing.facade.message.search.ImportJobSearchRq;
import org.nabucco.framework.importing.facade.service.search.SearchImporting;

/**
 * SearchImportingImpl<p/>Search Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class SearchImportingImpl extends ServiceSupport implements SearchImporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchImporting";

    private static Map<String, String[]> ASPECTS;

    private SearchImportConfigurationServiceHandler searchImportConfigurationServiceHandler;

    private SearchImportJobServiceHandler searchImportJobServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new SearchImportingImpl instance. */
    public SearchImportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.searchImportConfigurationServiceHandler = injector.inject(SearchImportConfigurationServiceHandler.getId());
        if ((this.searchImportConfigurationServiceHandler != null)) {
            this.searchImportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.searchImportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.searchImportJobServiceHandler = injector.inject(SearchImportJobServiceHandler.getId());
        if ((this.searchImportJobServiceHandler != null)) {
            this.searchImportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.searchImportJobServiceHandler.setLogger(super.getLogger());
        }
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
    }

    @Override
    public String[] getAspects(String operationName) {
        if ((ASPECTS == null)) {
            ASPECTS = new HashMap<String, String[]>();
            ASPECTS.put("searchImportConfiguration", NO_ASPECTS);
            ASPECTS.put("searchImportJob", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ImportConfigurationListMsg> searchImportConfiguration(
            ServiceRequest<ImportConfigurationSearchRq> rq) throws SearchException {
        if ((this.searchImportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchImportConfiguration().");
            throw new InjectionException("No service implementation configured for searchImportConfiguration().");
        }
        ServiceResponse<ImportConfigurationListMsg> rs;
        this.searchImportConfigurationServiceHandler.init();
        rs = this.searchImportConfigurationServiceHandler.invoke(rq);
        this.searchImportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportJobListMsg> searchImportJob(ServiceRequest<ImportJobSearchRq> rq)
            throws SearchException {
        if ((this.searchImportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchImportJob().");
            throw new InjectionException("No service implementation configured for searchImportJob().");
        }
        ServiceResponse<ImportJobListMsg> rs;
        this.searchImportJobServiceHandler.init();
        rs = this.searchImportJobServiceHandler.invoke(rq);
        this.searchImportJobServiceHandler.finish();
        return rs;
    }
}
