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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;
import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.importing.facade.component.ImportingComponentLocator;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportConfigurationLink;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRq;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRs;

/**
 * NewExecuteImportServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class NewExecuteImportServiceHandlerImpl extends ExecuteImportServiceHandler implements
        ServiceHandler {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private static final NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(
            NewExecuteImportServiceHandlerImpl.class);

    @Override
    protected ExecuteImportRs executeImport(ExecuteImportRq msg) throws ImportException {
        Map<Long, Integer> rating = new HashMap<Long, Integer>();
        ExecuteImportRs result = new ExecuteImportRs();

        LinkedHashSet<ImportConfiguration> workload = new LinkedHashSet<ImportConfiguration>();
        NabuccoList<ImportConfiguration> configurations = msg.getConfigurations();

        for (ImportConfiguration current : configurations) {
            resolveOrdered(getContext(), workload, current, rating);
        }

        for (ImportConfiguration run : ratingOrder(workload, rating)) {
            result.getExecutedImportJobs().add(
                    ImportHandlerFactory.getInstance()
                            .getImportHandler(run.getImportType(), getContext())
                            .executeImport(run, msg));
        }
        return result;
    }

    private static Collection<ImportConfiguration> ratingOrder(Set<ImportConfiguration> work,
            final Map<Long, Integer> rating) {
        List<ImportConfiguration> result = new ArrayList<ImportConfiguration>(work);
        Collections.sort(result, new Comparator<ImportConfiguration>() {
            @Override
            public int compare(ImportConfiguration o1, ImportConfiguration o2) {
                return -1 * rating.get(o1.getId()).compareTo(rating.get(o2.getId()));
            }
        });

        return result;
    }

    private static void resolveOrdered(ServiceMessageContext c,
            LinkedHashSet<ImportConfiguration> work, ImportConfiguration root,
            Map<Long, Integer> rating) throws ImportException {
        ImportConfiguration importConfiguration = resolve(c, root);

        Long currentID = importConfiguration.getId();
        if (rating.get(currentID) == null) {
            rating.put(currentID, new Integer(0));
        } else {
            rating.put(currentID, rating.get(currentID) + 1);
        }

        if (work.add(importConfiguration)) {
            for (ImportConfigurationLink currentLink : order(importConfiguration.getDependencies())) {
                resolveOrdered(c, work, currentLink.getConfig(), rating);
            }
        }
    }

    private static List<ImportConfigurationLink> order(List<ImportConfigurationLink> list) {
        Collections.sort(list, new Comparator<ImportConfigurationLink>() {

            @Override
            public int compare(ImportConfigurationLink o1, ImportConfigurationLink o2) {
                return o1.getPriority().getValue().compareTo(o2.getPriority().getValue());
            }

        });
        return list;
    }

    private static ImportConfiguration resolve(ServiceMessageContext c,
            ImportConfiguration importConfiguration) throws ImportException {

        ServiceRequest<ImportConfigurationMsg> rq = new ServiceRequest<ImportConfigurationMsg>(c);
        ImportConfigurationMsg requestMessage = new ImportConfigurationMsg();
        requestMessage.setImportConfiguration(importConfiguration);
        rq.setRequestMessage(requestMessage);
        ImportConfigurationMsg responseMessage;
        try {
            responseMessage = ImportingComponentLocator.getInstance().getComponent()
                    .getResolveImporting().resolveImportConfiguration(rq).getResponseMessage();
            return responseMessage.getImportConfiguration();
        } catch (ResolveException e) {
            String msg = "Unable to resovle ImportConfiguration with id: "
                    + importConfiguration.getId();
            logger.error(msg);
            throw new ImportException(msg, e);
        } catch (ServiceException e) {
            String msg = "Unable to resovle ImportConfiguration with id: "
                    + importConfiguration.getId();
            logger.error(msg);
            throw new ImportException(msg, e);
        } catch (ConnectionException e) {
            String msg = "Unable to resovle ImportConfiguration with id: "
                    + importConfiguration.getId();
            logger.error(msg);
            throw new ImportException(msg, e);
        }
    }

}
