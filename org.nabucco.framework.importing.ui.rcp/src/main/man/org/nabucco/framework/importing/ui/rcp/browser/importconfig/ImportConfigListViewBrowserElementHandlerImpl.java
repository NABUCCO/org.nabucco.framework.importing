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

import java.util.List;

import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.model.ImportConfigurationListViewModel;
import org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;

/**
 * ImportConfigListViewBrowserElementHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportConfigListViewBrowserElementHandlerImpl
        extends
        AbstractBrowserListViewHandlerImpl<ImportConfiguration, ImportConfigurationListViewModel, ImportConfigurationListViewBrowserElement, ImportConfigurationEditViewBrowserElement>
        implements ImportConfigurationListViewBrowserElementHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void createChildren(ImportConfigurationListViewModel viewModel,
            ImportConfigurationListViewBrowserElement element) {

        for (ImportConfiguration config : viewModel.getElements()) {
            element.addBrowserElement(new ImportConfigurationEditViewBrowserElement(config));
        }
    }

    @Override
    public void removeChild(BrowserElement toBeRemoved,
            ImportConfigurationListViewBrowserElement element) {
        removeChildren((ImportConfigurationEditViewBrowserElement) toBeRemoved, element);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl#haveSameId
     * (org.nabucco.framework.base.facade.datatype.NabuccoDatatype,
     * org.nabucco.framework.plugin.base.model.browser.BrowserElement)
     */
    @Override
    public boolean haveSameId(ImportConfiguration importConfiguration,
            ImportConfigurationEditViewBrowserElement dynamicCodeCodeEditViewBrowserElement) {

        boolean result = false;
        result = importConfiguration.getId().equals(
                dynamicCodeCodeEditViewBrowserElement.getViewModel().getImportConfig().getId());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.nabucco.framework.plugin.base.model.browser.AbstractBrowserListViewHandlerImpl#
     * updateViewModel(java.util.List, org.nabucco.framework.plugin.base.model.ListViewModel)
     */
    @Override
    public void updateViewModel(List<ImportConfiguration> elements,
            ImportConfigurationListViewModel viewModel) {

        viewModel.setElements(elements.toArray(new ImportConfiguration[0]));

    }

}
