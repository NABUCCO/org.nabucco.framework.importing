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

import java.util.List;

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportConfigurationLink;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.maintain.ImportConfigurationLinkRq;

/**
 * LinkImportConfigurationServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class LinkImportConfigurationServiceHandlerImpl extends
        LinkImportConfigurationServiceHandler implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final NabuccoLogger LOGGER = NabuccoLoggingFactory.getInstance().getLogger(
            LinkImportConfigurationServiceHandlerImpl.class);

    private Number priority;

    private ImportConfiguration source;

    private ImportConfiguration target;

    @Override
    protected ImportConfigurationMsg linkImportConfiguration(ImportConfigurationLinkRq msg)
            throws MaintainException {
        ImportConfigurationMsg result = new ImportConfigurationMsg();

        initData(msg);

        switch (msg.getOperation()) {
        case ADD: {
            addOrUpdate();
            break;
        }
        case REMOVE: {
            remove();
            break;
        }
        default: {
            break;
        }
        }

        result.setImportConfiguration(this.source);
        return result;
    }

    private void addOrUpdate() throws MaintainException {
        List<ImportConfigurationLink> dependencyList = source.getDependencies();

        // already contain a link to the given Export Configuration? update if so
        for (int i = 0; i < dependencyList.size(); i++) {

            if (dependencyList.get(i).getConfig().getId().compareTo(target.getId()) == 0) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Import Configuration with id ",
                            String.valueOf(dependencyList.get(i).getId()),
                            " already contains a Link to ImportConfiguration with id ",
                            String.valueOf(this.target.getId()), " atempting to update priority");
                }

                if (dependencyList.get(i).getPriority().compareTo(this.priority) == 0) {
                    LOGGER.debug("Nothing to do, priority same.");
                } else {

                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("update priority for Link in ImportConfiguration id:",
                                String.valueOf(dependencyList.get(i).getConfig().getId()),
                                ", to ImportConfiguration id:", String.valueOf(target.getId()),
                                ", from ",
                                String.valueOf(dependencyList.get(i).getPriority().toString()),
                                " to ", String.valueOf(this.priority));
                    }

                    dependencyList.get(i).setPriority(this.priority);
                    try {
                        dependencyList.set(i,
                                super.getPersistenceManager().persist(dependencyList.get(i)));
                        this.source = super.getPersistenceManager().persist(this.source);
                    } catch (PersistenceException e) {
                        throw new MaintainException(
                                "Unable to update priority ImportConfigurationLink with id: "
                                        + dependencyList.get(i).getId(), e);
                    }
                }
                // done here
                return;
            }
        }

        ImportConfigurationLink newLink = new ImportConfigurationLink();
        newLink.setDatatypeState(DatatypeState.INITIALIZED);
        newLink.setConfig(target);
        newLink.setPriority(this.priority);

        try {
            ImportConfigurationLink persist = super.getPersistenceManager().persist(newLink);
            dependencyList.add(persist);
            this.source = super.getPersistenceManager().persist(this.source);
        } catch (PersistenceException e) {
            throw new MaintainException(
                    "Unable to add ImportConfigurationLink to ImportConfiguration with id: "
                            + this.source.getId());
        }
    }

    private void remove() throws MaintainException {
        List<ImportConfigurationLink> listOfLinks = this.source.getDependencies();
        for (int i = 0; i < listOfLinks.size(); i++) {
            if (listOfLinks.get(i).getConfig().getId().compareTo(this.target.getId()) == 0) {
                ImportConfigurationLink remove = listOfLinks.remove(i);
                remove.setDatatypeState(DatatypeState.DELETED);
                try {
                    this.source = super.getPersistenceManager().persist(this.source);
                    super.getPersistenceManager().persist(remove);
                    return;
                } catch (PersistenceException e) {
                    throw new MaintainException("unable to delete ImportComponentLink with id: "
                            + remove.getId());
                }
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ImportConfiguration with id:"
                    + source.getId()
                    + " does not contain a dependency to ImportConfiguration with id: "
                    + this.target.getId());
        }

    }

    private void initData(ImportConfigurationLinkRq msg) throws MaintainException {

        this.priority = msg.getPriority();

        try {
            this.source = super.getPersistenceManager().find(msg.getSource());
            this.target = super.getPersistenceManager().find(msg.getTarget());
        } catch (PersistenceException e) {
            throw new MaintainException("Error initializing import data.", e);
        }

    }
}
