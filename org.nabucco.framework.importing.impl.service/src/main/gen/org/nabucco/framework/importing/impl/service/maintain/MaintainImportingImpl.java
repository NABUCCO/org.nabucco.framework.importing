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
package org.nabucco.framework.importing.impl.service.maintain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
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
import org.nabucco.framework.importing.facade.message.maintain.ImportConfigurationLinkRq;
import org.nabucco.framework.importing.facade.service.maintain.MaintainImporting;

/**
 * MaintainImportingImpl<p/>Maintain Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class MaintainImportingImpl extends ServiceSupport implements MaintainImporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainImporting";

    private static Map<String, String[]> ASPECTS;

    private MaintainImportConfigurationServiceHandler maintainImportConfigurationServiceHandler;

    private MaintainImportConfigurationListServiceHandler maintainImportConfigurationListServiceHandler;

    private LinkImportConfigurationServiceHandler linkImportConfigurationServiceHandler;

    private MaintainImportJobServiceHandler maintainImportJobServiceHandler;

    private MaintainImportJobListServiceHandler maintainImportJobListServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new MaintainImportingImpl instance. */
    public MaintainImportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.maintainImportConfigurationServiceHandler = injector.inject(MaintainImportConfigurationServiceHandler
                .getId());
        if ((this.maintainImportConfigurationServiceHandler != null)) {
            this.maintainImportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainImportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.maintainImportConfigurationListServiceHandler = injector
                .inject(MaintainImportConfigurationListServiceHandler.getId());
        if ((this.maintainImportConfigurationListServiceHandler != null)) {
            this.maintainImportConfigurationListServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainImportConfigurationListServiceHandler.setLogger(super.getLogger());
        }
        this.linkImportConfigurationServiceHandler = injector.inject(LinkImportConfigurationServiceHandler.getId());
        if ((this.linkImportConfigurationServiceHandler != null)) {
            this.linkImportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.linkImportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.maintainImportJobServiceHandler = injector.inject(MaintainImportJobServiceHandler.getId());
        if ((this.maintainImportJobServiceHandler != null)) {
            this.maintainImportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainImportJobServiceHandler.setLogger(super.getLogger());
        }
        this.maintainImportJobListServiceHandler = injector.inject(MaintainImportJobListServiceHandler.getId());
        if ((this.maintainImportJobListServiceHandler != null)) {
            this.maintainImportJobListServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainImportJobListServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("maintainImportConfiguration", NO_ASPECTS);
            ASPECTS.put("maintainImportConfigurationList", NO_ASPECTS);
            ASPECTS.put("linkImportConfiguration", NO_ASPECTS);
            ASPECTS.put("maintainImportJob", NO_ASPECTS);
            ASPECTS.put("maintainImportJobList", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ImportConfigurationMsg> maintainImportConfiguration(ServiceRequest<ImportConfigurationMsg> rq)
            throws MaintainException {
        if ((this.maintainImportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainImportConfiguration().");
            throw new InjectionException("No service implementation configured for maintainImportConfiguration().");
        }
        ServiceResponse<ImportConfigurationMsg> rs;
        this.maintainImportConfigurationServiceHandler.init();
        rs = this.maintainImportConfigurationServiceHandler.invoke(rq);
        this.maintainImportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportConfigurationListMsg> maintainImportConfigurationList(
            ServiceRequest<ImportConfigurationListMsg> rq) throws MaintainException {
        if ((this.maintainImportConfigurationListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainImportConfigurationList().");
            throw new InjectionException("No service implementation configured for maintainImportConfigurationList().");
        }
        ServiceResponse<ImportConfigurationListMsg> rs;
        this.maintainImportConfigurationListServiceHandler.init();
        rs = this.maintainImportConfigurationListServiceHandler.invoke(rq);
        this.maintainImportConfigurationListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportConfigurationMsg> linkImportConfiguration(ServiceRequest<ImportConfigurationLinkRq> rq)
            throws MaintainException {
        if ((this.linkImportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for linkImportConfiguration().");
            throw new InjectionException("No service implementation configured for linkImportConfiguration().");
        }
        ServiceResponse<ImportConfigurationMsg> rs;
        this.linkImportConfigurationServiceHandler.init();
        rs = this.linkImportConfigurationServiceHandler.invoke(rq);
        this.linkImportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportJobMsg> maintainImportJob(ServiceRequest<ImportJobMsg> rq) throws MaintainException {
        if ((this.maintainImportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainImportJob().");
            throw new InjectionException("No service implementation configured for maintainImportJob().");
        }
        ServiceResponse<ImportJobMsg> rs;
        this.maintainImportJobServiceHandler.init();
        rs = this.maintainImportJobServiceHandler.invoke(rq);
        this.maintainImportJobServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportJobListMsg> maintainImportJobList(ServiceRequest<ImportJobListMsg> rq)
            throws MaintainException {
        if ((this.maintainImportJobListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainImportJobList().");
            throw new InjectionException("No service implementation configured for maintainImportJobList().");
        }
        ServiceResponse<ImportJobListMsg> rs;
        this.maintainImportJobListServiceHandler.init();
        rs = this.maintainImportJobListServiceHandler.invoke(rq);
        this.maintainImportJobListServiceHandler.finish();
        return rs;
    }
}
