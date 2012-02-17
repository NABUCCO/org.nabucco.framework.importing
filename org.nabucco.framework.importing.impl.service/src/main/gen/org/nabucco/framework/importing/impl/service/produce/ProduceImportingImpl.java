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
package org.nabucco.framework.importing.impl.service.produce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.facade.service.produce.ProduceImporting;

/**
 * ProduceImportingImpl<p/>Produce Service for Importing<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-08-06
 */
public class ProduceImportingImpl extends ServiceSupport implements ProduceImporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceImporting";

    private static Map<String, String[]> ASPECTS;

    private ProduceImportConfigurationServiceHandler produceImportConfigurationServiceHandler;

    private ProduceImportJobServiceHandler produceImportJobServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ProduceImportingImpl instance. */
    public ProduceImportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.produceImportConfigurationServiceHandler = injector.inject(ProduceImportConfigurationServiceHandler
                .getId());
        if ((this.produceImportConfigurationServiceHandler != null)) {
            this.produceImportConfigurationServiceHandler.setPersistenceManager(persistenceManager);
            this.produceImportConfigurationServiceHandler.setLogger(super.getLogger());
        }
        this.produceImportJobServiceHandler = injector.inject(ProduceImportJobServiceHandler.getId());
        if ((this.produceImportJobServiceHandler != null)) {
            this.produceImportJobServiceHandler.setPersistenceManager(persistenceManager);
            this.produceImportJobServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("produceImportConfiguration", NO_ASPECTS);
            ASPECTS.put("produceImportJob", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ImportConfigurationMsg> produceImportConfiguration(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceImportConfigurationServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceImportConfiguration().");
            throw new InjectionException("No service implementation configured for produceImportConfiguration().");
        }
        ServiceResponse<ImportConfigurationMsg> rs;
        this.produceImportConfigurationServiceHandler.init();
        rs = this.produceImportConfigurationServiceHandler.invoke(rq);
        this.produceImportConfigurationServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<ImportJobMsg> produceImportJob(ServiceRequest<EmptyServiceMessage> rq)
            throws ProduceException {
        if ((this.produceImportJobServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceImportJob().");
            throw new InjectionException("No service implementation configured for produceImportJob().");
        }
        ServiceResponse<ImportJobMsg> rs;
        this.produceImportJobServiceHandler.init();
        rs = this.produceImportJobServiceHandler.invoke(rq);
        this.produceImportJobServiceHandler.finish();
        return rs;
    }
}
