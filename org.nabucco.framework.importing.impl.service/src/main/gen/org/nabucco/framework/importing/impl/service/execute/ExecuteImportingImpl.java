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
package org.nabucco.framework.importing.impl.service.execute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRq;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRs;
import org.nabucco.framework.importing.facade.service.execute.ExecuteImporting;

/**
 * ExecuteImportingImpl<p/>Services that are used to start / execute import operations.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class ExecuteImportingImpl extends ServiceSupport implements ExecuteImporting {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ExecuteImporting";

    private static Map<String, String[]> ASPECTS;

    private ExecuteImportServiceHandler executeImportServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ExecuteImportingImpl instance. */
    public ExecuteImportingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.executeImportServiceHandler = injector.inject(ExecuteImportServiceHandler.getId());
        if ((this.executeImportServiceHandler != null)) {
            this.executeImportServiceHandler.setPersistenceManager(persistenceManager);
            this.executeImportServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("executeImport", NO_ASPECTS);
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<ExecuteImportRs> executeImport(ServiceRequest<ExecuteImportRq> rq) throws ImportException {
        if ((this.executeImportServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for executeImport().");
            throw new InjectionException("No service implementation configured for executeImport().");
        }
        ServiceResponse<ExecuteImportRs> rs;
        this.executeImportServiceHandler.init();
        rs = this.executeImportServiceHandler.invoke(rq);
        this.executeImportServiceHandler.finish();
        return rs;
    }
}
