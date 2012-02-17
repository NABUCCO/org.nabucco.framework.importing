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
package org.nabucco.framework.importing.ui.rcp.browser.importconfig;

import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.message.ImportConfigurationMsg;
import org.nabucco.framework.importing.ui.rcp.communication.ImportingComponentServiceDelegateFactory;
import org.nabucco.framework.importing.ui.rcp.communication.resolve.ResolveImportingDelegate;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel;
import org.nabucco.framework.plugin.base.Activator;

/**
 * ImportConfigEditViewBrowserElementHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportConfigEditViewBrowserElementHandlerImpl implements
        ImportConfigurationEditViewBrowserElementHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public ImportConfigEditViewModel loadFull(ImportConfigEditViewModel viewModel) {

        try {

            ResolveImportingDelegate resolveImportingDelegate = ImportingComponentServiceDelegateFactory
                    .getInstance().getResolveImporting();

            // create request message
            ImportConfigurationMsg resolveRequestMessage = new ImportConfigurationMsg();
            ImportConfiguration config = viewModel.getImportConfig();
            resolveRequestMessage.setImportConfiguration(config);

            // send request to service
            ImportConfigurationMsg resolveResponseMessage = resolveImportingDelegate
                    .resolveImportConfiguration(resolveRequestMessage);

            // process response
            ImportConfiguration resolved = resolveResponseMessage.getImportConfiguration();

            viewModel.setImportConfig(resolved);

        } catch (Exception e) {
            Activator.getDefault().logError(e);
        }
        // loadDynamicCodeList(result);
        return viewModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createChildren(ImportConfigEditViewModel viewModel,
            ImportConfigurationEditViewBrowserElement element) {
        // ImportConfigurations do not have any children.
    }
}
