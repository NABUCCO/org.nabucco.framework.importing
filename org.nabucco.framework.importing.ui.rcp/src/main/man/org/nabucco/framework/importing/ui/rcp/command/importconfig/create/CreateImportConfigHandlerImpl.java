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
package org.nabucco.framework.importing.ui.rcp.command.importconfig.create;

import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.EmptyServiceMessage;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.ui.rcp.communication.ImportingComponentServiceDelegateFactory;
import org.nabucco.framework.importing.ui.rcp.communication.produce.ProduceImportingDelegate;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.view.ImportConfigEditView;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.command.AbstractAddDatatypeHandlerImpl;

/**
 * CreateImportConfigHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class CreateImportConfigHandlerImpl extends AbstractAddDatatypeHandlerImpl<ImportConfigEditViewModel> implements
        CreateImportConfigHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEditViewId() {
        return ImportConfigEditView.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createImportConfig() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateModel(ImportConfigEditViewModel viewModel) {
        ImportConfiguration importConfig = createNewImportConfig();
        viewModel.setImportConfig(importConfig);
    }

    /**
     * Produce new {@link DynamicCodeCode} instance.
     * 
     * @return the new created dynamic code
     */
    private ImportConfiguration createNewImportConfig() {
        ImportConfiguration result = null;

        try {
            ProduceImportingDelegate produceImporting = ImportingComponentServiceDelegateFactory.getInstance()
                    .getProduceImporting();
            ImportConfigurationMsg response = produceImporting.produceImportConfiguration(new EmptyServiceMessage());
            result = response.getImportConfiguration();
        } catch (ClientException e) {
            Activator.getDefault().logError(e);
        }
        return result;
    }

}
