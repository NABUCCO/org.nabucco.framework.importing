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
package org.nabucco.framework.importing.ui.rcp.edit.importjob.model;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.ui.rcp.communication.ImportingComponentServiceDelegateFactory;
import org.nabucco.framework.importing.ui.rcp.communication.maintain.MaintainImportingDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.model.BusinessModel;

/**
 * ImportJobEditBusinessModel
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportJobEditBusinessModel implements BusinessModel {

    public static String ID = "org.nabucco.framework.importing.ui.rcp.edit.importing.model.ImportJobEditBusinessModel";

    /**
     * Save an import job.
     * 
     * @param importJob
     *            should be saved
     */
    public ImportJobMsg save(ImportJob importJob) {

        try {
            MaintainImportingDelegate maintainDelegate = ImportingComponentServiceDelegateFactory
                    .getInstance().getMaintainImporting();

            ImportJobMsg message = createImportJobMaintainMsg(importJob);
            return maintainDelegate.maintainImportJob(message);

        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return null;
    }

    /**
     * Create the maintain message.
     * 
     * @param importJob
     *            the current import job
     * 
     * @return the message
     */
    private ImportJobMsg createImportJobMaintainMsg(final ImportJob importJob) {
        ImportJobMsg message = new ImportJobMsg();
        message.setImportJob(importJob);
        return message;
    }
}
