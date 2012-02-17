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
package org.nabucco.framework.importing.facade.service.execute;

import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRq;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRs;

/**
 * ExecuteImporting<p/>Services that are used to start / execute import operations.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public interface ExecuteImporting extends Service {

    /**
     * Missing description at method executeImport.
     *
     * @param rq the ServiceRequest<ExecuteImportRq>.
     * @return the ServiceResponse<ExecuteImportRs>.
     * @throws ImportException
     */
    ServiceResponse<ExecuteImportRs> executeImport(ServiceRequest<ExecuteImportRq> rq) throws ImportException;
}
