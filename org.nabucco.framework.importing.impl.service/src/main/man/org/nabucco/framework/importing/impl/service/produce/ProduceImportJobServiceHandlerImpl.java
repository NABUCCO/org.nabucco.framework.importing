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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.DateTime;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.log.LogTrace;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;

/**
 * ProduceImportJobServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ProduceImportJobServiceHandlerImpl extends ProduceImportJobServiceHandler implements
        ServiceHandler {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected ImportJobMsg produceImportJob(EmptyServiceMessage msg) throws ProduceException {
        ImportJobMsg result = new ImportJobMsg();
        ImportJob importJob = new ImportJob();
        importJob.setDescription(new Description());
        importJob.setEndTime(new DateTime());
        importJob.setErrorTrace(new LogTrace());
        importJob.setLogTrace(new LogTrace());
        importJob.setName(new Name());
        importJob.setOwner(new Owner());
        importJob.setStartTime(new DateTime());
        importJob.setDatatypeState(DatatypeState.INITIALIZED);
        result.setImportJob(importJob);
        return result;
    }

}
