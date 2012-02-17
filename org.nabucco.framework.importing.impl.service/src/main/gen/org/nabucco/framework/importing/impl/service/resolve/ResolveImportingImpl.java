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
package org.nabucco.framework.importing.impl.service.resolve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.facade.service.resolve.ResolveImporting;

/**
 * ResolveImportingImpl<p/>Resolve Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-08-03
 */
public class ResolveImportingImpl extends ServiceSupport implements ResolveImporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ResolveImporting";

    private static Map<String, String[]> ASPECTS;

    private ResolveImportConfigurationServiceHandler resolveImportConfigurationServiceHandler;

    private ResolveImportConfigurationListServiceHandler resolveImportConfigurationListServiceHandler;

    private ResolveImportJobServiceHandler resolveImportJobServiceHandler;

    private ResolveImportJobListServiceHandler resolveImportJobListServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ResolveImportingImpl instance. */
    public ResolveImportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.resolveImportConfigurationServiceHandler = injector.inject(ResolveImportConfigurationServiceHandler
                .getId());
        if ((this.resolveImportConfigurationServiceHandler != null)) {
            this.resolveImportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveImportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.resolveImportConfigurationListServiceHandler = injector
                .inject(ResolveImportConfigurationListServiceHandler.getId());
        if ((this.resolveImportConfigurationListServiceHandler != null)) {
            this.resolveImportConfigurationListServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveImportConfigurationListServiceHandler.setLogger(super.getLogger());
        }
        this.resolveImportJobServiceHandler = injector.inject(ResolveImportJobServiceHandler.getId());
        if ((this.resolveImportJobServiceHandler != null)) {
            this.resolveImportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveImportJobServiceHandler.setLogger(super.getLogger());
        }
        this.resolveImportJobListServiceHandler = injector.inject(ResolveImportJobListServiceHandler.getId());
        if ((this.resolveImportJobListServiceHandler != null)) {
            this.resolveImportJobListServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveImportJobListServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("resolveImportConfiguration", NO_ASPECTS);
            ASPECTS.put("resolveImportConfigurationList", NO_ASPECTS);
            ASPECTS.put("resolveImportJob", NO_ASPECTS);
            ASPECTS.put("resolveImportJobList", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ImportConfigurationMsg> resolveImportConfiguration(ServiceRequest<ImportConfigurationMsg> rq)
            throws ResolveException {
        if ((this.resolveImportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveImportConfiguration().");
            throw new InjectionException("No service implementation configured for resolveImportConfiguration().");
        }
        ServiceResponse<ImportConfigurationMsg> rs;
        this.resolveImportConfigurationServiceHandler.init();
        rs = this.resolveImportConfigurationServiceHandler.invoke(rq);
        this.resolveImportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportConfigurationListMsg> resolveImportConfigurationList(
            ServiceRequest<ImportConfigurationListMsg> rq) throws ResolveException {
        if ((this.resolveImportConfigurationListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveImportConfigurationList().");
            throw new InjectionException("No service implementation configured for resolveImportConfigurationList().");
        }
        ServiceResponse<ImportConfigurationListMsg> rs;
        this.resolveImportConfigurationListServiceHandler.init();
        rs = this.resolveImportConfigurationListServiceHandler.invoke(rq);
        this.resolveImportConfigurationListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportJobMsg> resolveImportJob(ServiceRequest<ImportJobMsg> rq) throws ResolveException {
        if ((this.resolveImportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveImportJob().");
            throw new InjectionException("No service implementation configured for resolveImportJob().");
        }
        ServiceResponse<ImportJobMsg> rs;
        this.resolveImportJobServiceHandler.init();
        rs = this.resolveImportJobServiceHandler.invoke(rq);
        this.resolveImportJobServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportJobListMsg> resolveImportJobList(ServiceRequest<ImportJobListMsg> rq)
            throws ResolveException {
        if ((this.resolveImportJobListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveImportJobList().");
            throw new InjectionException("No service implementation configured for resolveImportJobList().");
        }
        ServiceResponse<ImportJobListMsg> rs;
        this.resolveImportJobListServiceHandler.init();
        rs = this.resolveImportJobListServiceHandler.invoke(rq);
        this.resolveImportJobListServiceHandler.finish();
        return rs;
    }
}
