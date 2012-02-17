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
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportConfigurationLink;
import org.nabucco.framework.importing.facade.datatype.ImportJob;

/**
 * ImportElementMaintainer
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ImportElementMaintainer {

    private static final NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            ImportElementMaintainer.class);

    static ImportConfiguration[] maintain(PersistenceManager pm,
            ImportConfiguration... configurations) throws MaintainException {

        for (int i = 0; i < configurations.length; i++) {
            if (configurations[i].getDatatypeState() == DatatypeState.DELETED) {
                try {
                    configurations[i] = pm.find(configurations[i]);
                    List<ImportConfigurationLink> dependencies = configurations[i]
                            .getDependencies();
                    for (int j = 0; j < dependencies.size(); j++) {
                        dependencies.get(j).setDatatypeState(DatatypeState.DELETED);
                        ImportConfigurationLink removed = dependencies.remove(j);
                        removed = maintain(pm, removed)[0];
                    }
                } catch (PersistenceException e) {
                    String msg = "unable to resolve ImportConfiguration with id: "
                            + configurations[i].getId() + " while attempting to delete it";
                    logger.error(msg);
                    throw new MaintainException(msg, e);
                }
            }
            try {
                configurations[i] = pm.persist(configurations[i]);
            } catch (PersistenceException e) {
                String msg = "unable to maintain ImportConfiguration with id: "
                        + configurations[i].getId();
                logger.error(msg);
                throw new MaintainException(msg, e);
            }
        }
        return configurations;
    }

    static ImportJob[] maintain(PersistenceManager pm, ImportJob... importJobs)
            throws MaintainException {
        for (int i = 0; i < importJobs.length; i++) {
            try {
                pm.persist(importJobs[i]);
            } catch (PersistenceException e) {
                String msg = "unable to maintain ImportJob with id: " + importJobs[i];
                logger.error(msg);
                throw new MaintainException(msg, e);
            }
        }
        return importJobs;
    }

    static ImportConfigurationLink[] maintain(PersistenceManager pm,
            ImportConfigurationLink... configurationLinks) throws MaintainException {
        for (int i = 0; i < configurationLinks.length; i++) {
            try {
                configurationLinks[i] = pm.persist(configurationLinks[i]);
            } catch (PersistenceException e) {
                String msg = "unable to maintain ImportConfigurationLink with id: "
                        + configurationLinks[i].getId();
                logger.error(msg);
                throw new MaintainException(msg, e);
            }
        }

        return configurationLinks;
    }

}
