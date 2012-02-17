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
package org.nabucco.framework.importing.ui.web.communication;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateFactorySupport;
import org.nabucco.framework.importing.facade.component.ImportingComponent;
import org.nabucco.framework.importing.facade.component.ImportingComponentLocator;
import org.nabucco.framework.importing.ui.web.communication.execute.ExecuteImportingDelegate;
import org.nabucco.framework.importing.ui.web.communication.maintain.MaintainImportingDelegate;
import org.nabucco.framework.importing.ui.web.communication.produce.ProduceImportingDelegate;
import org.nabucco.framework.importing.ui.web.communication.resolve.ResolveImportingDelegate;
import org.nabucco.framework.importing.ui.web.communication.search.SearchImportingDelegate;

/**
 * ServiceDelegateFactoryTemplate<p/>Component for XML, CSV, etc. imports.. The component was named 'Importing' as opposed to 'Import', because 'import' is a reserved java keyword.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-22
 */
public class ImportingComponentServiceDelegateFactory extends ServiceDelegateFactorySupport<ImportingComponent> {

    private static ImportingComponentServiceDelegateFactory instance = new ImportingComponentServiceDelegateFactory();

    private ProduceImportingDelegate produceImportingDelegate;

    private ResolveImportingDelegate resolveImportingDelegate;

    private ExecuteImportingDelegate executeImportingDelegate;

    private MaintainImportingDelegate maintainImportingDelegate;

    private SearchImportingDelegate searchImportingDelegate;

    /** Constructs a new ImportingComponentServiceDelegateFactory instance. */
    private ImportingComponentServiceDelegateFactory() {
        super(ImportingComponentLocator.getInstance());
    }

    /**
     * Getter for the ProduceImporting.
     *
     * @return the ProduceImportingDelegate.
     * @throws ClientException
     */
    public ProduceImportingDelegate getProduceImporting() throws ClientException {
        try {
            if ((this.produceImportingDelegate == null)) {
                this.produceImportingDelegate = new ProduceImportingDelegate(this.getComponent().getProduceImporting());
            }
            return this.produceImportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ProduceImporting", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ResolveImporting.
     *
     * @return the ResolveImportingDelegate.
     * @throws ClientException
     */
    public ResolveImportingDelegate getResolveImporting() throws ClientException {
        try {
            if ((this.resolveImportingDelegate == null)) {
                this.resolveImportingDelegate = new ResolveImportingDelegate(this.getComponent().getResolveImporting());
            }
            return this.resolveImportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ResolveImporting", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ExecuteImporting.
     *
     * @return the ExecuteImportingDelegate.
     * @throws ClientException
     */
    public ExecuteImportingDelegate getExecuteImporting() throws ClientException {
        try {
            if ((this.executeImportingDelegate == null)) {
                this.executeImportingDelegate = new ExecuteImportingDelegate(this.getComponent().getExecuteImporting());
            }
            return this.executeImportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ExecuteImporting", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the MaintainImporting.
     *
     * @return the MaintainImportingDelegate.
     * @throws ClientException
     */
    public MaintainImportingDelegate getMaintainImporting() throws ClientException {
        try {
            if ((this.maintainImportingDelegate == null)) {
                this.maintainImportingDelegate = new MaintainImportingDelegate(this.getComponent()
                        .getMaintainImporting());
            }
            return this.maintainImportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: MaintainImporting", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the SearchImporting.
     *
     * @return the SearchImportingDelegate.
     * @throws ClientException
     */
    public SearchImportingDelegate getSearchImporting() throws ClientException {
        try {
            if ((this.searchImportingDelegate == null)) {
                this.searchImportingDelegate = new SearchImportingDelegate(this.getComponent().getSearchImporting());
            }
            return this.searchImportingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: SearchImporting", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the ImportingComponentServiceDelegateFactory.
     */
    public static ImportingComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
