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

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportJob;

/**
 * ImportElementResolver
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ImportElementResolver {

    private static final NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            ImportElementResolver.class);

    static ImportConfiguration[] resolve(PersistenceManager manager,
            ImportConfiguration... configurations) throws ResolveException {

        for (int i = 0; i < configurations.length; i++) {
            try {
                configurations[i] = manager.find(configurations[i]);
            } catch (PersistenceException e) {
                logger.error("Unable to resolve ImportConfiguration with id: ",
                        String.valueOf(configurations[i].getId()));
                throw new ResolveException("Unable to resolve ImportConfiguration with id: "
                        + configurations[i].getId(), e);
            }
        }

        return configurations;
    }

    static ImportJob[] resolve(PersistenceManager manager, ImportJob... jobs)
            throws ResolveException {
        for (int i = 0; i < jobs.length; i++) {
            try {
                jobs[i] = manager.find(jobs[i]);
            } catch (PersistenceException e) {
                logger.error("Unable to resolve ImportJob with id: ",
                        String.valueOf(jobs[i].getId()));
                throw new ResolveException("Unable to resolve ImportJob with id: "
                        + jobs[i].getId(), e);
            }
        }
        return jobs;
    }

}
