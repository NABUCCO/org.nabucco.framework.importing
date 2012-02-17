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
package org.nabucco.framework.importing.ui.rcp.command.save.importjob;

import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.message.ImportJobMsg;
import org.nabucco.framework.importing.ui.rcp.command.importjob.save.SaveImportJobHandler;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditBusinessModel;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditBusinessModel;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditViewModel;
import org.nabucco.framework.plugin.base.command.AbstractSaveCommandHandlerImpl;

/**
 * SaveImportJobHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class SaveImportJobHandlerImpl extends
        AbstractSaveCommandHandlerImpl<ImportJobEditBusinessModel, ImportJobEditViewModel> implements
        SaveImportJobHandler {

    @Override
    public void saveImportJob() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBusinessModelId() {
        return ImportConfigEditBusinessModel.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveModel(ImportJobEditViewModel viewModel, ImportJobEditBusinessModel businessModel) {
        ImportJob importJob = viewModel.getImportJob();
        ImportJobMsg result = businessModel.save(importJob);
        viewModel.setImportJob(result.getImportJob());
    }
}
