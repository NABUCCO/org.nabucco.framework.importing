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

import java.util.List;

import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.search.QuerySupport;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.search.ImportJobSearchRq;

/**
 * SearchImportJobServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class SearchImportJobServiceHandlerImpl extends SearchImportJobServiceHandler implements
        ServiceHandler {

    private static final long serialVersionUID = 1L;

    private static final String PROP_DESCRIPTION = "description";

    private static final String PROP_END_TIME = "end_time";

    private static final String PROP_ERROR_TRACE = "error_trace";

    private static final String PROP_LOG_TRACE = "log_trace";

    private static final String PROP_NAME = "name";

    private static final String PROP_OWNER = "owner";

    private static final String PROP_START_TIME = "start_time";

    private static final String QUERY = "select e from ImportJob e where "
            + "e.description like :description or :description is null "
            + "and (e.endTime = :end_time or :end_time is null) "
            + "and (e.errorTrace = :error_trace or :error_trace is null) "
            + "and (e.logTrace = :log_trace or :log_trace is null) "
            + "and (e.name = :name or :name is null) "
            + "and (e.owner = :owner or :owner is null) "
            + "and (e.startTime = :start_time or :start_time is null)";

    @Override
    protected ImportJobListMsg searchImportJob(ImportJobSearchRq msg) throws SearchException {
        ImportJobListMsg result = new ImportJobListMsg();

        try {
            NabuccoQuery<ImportJob> query = super.getPersistenceManager().createQuery(QUERY);
            query.setParameter(PROP_DESCRIPTION, QuerySupport.searchParameter(msg.getDescription()));
            query.setParameter(PROP_END_TIME, msg.getEndTime());
            query.setParameter(PROP_ERROR_TRACE, msg.getErrorTrace());
            query.setParameter(PROP_LOG_TRACE, msg.getLogTrace());
            query.setParameter(PROP_NAME, msg.getName());
            query.setParameter(PROP_OWNER, msg.getOwner());
            query.setParameter(PROP_START_TIME, msg.getStartTime());

            List<ImportJob> resultList = query.getResultList();
            result.getImportJobList().addAll(resultList);

        } catch (Exception e) {
            throw new SearchException("Error searching for import jobs.", e);
        }

        return result;
    }
}
