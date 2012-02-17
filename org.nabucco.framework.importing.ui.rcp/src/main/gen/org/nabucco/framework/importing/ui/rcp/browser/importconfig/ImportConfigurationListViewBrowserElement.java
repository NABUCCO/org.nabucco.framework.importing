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
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.model.ImportConfigurationListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * ImportConfigurationListViewBrowserElement
 *
 * @author Undefined
 */
public class ImportConfigurationListViewBrowserElement extends BrowserListElement<ImportConfigurationListViewModel>
        implements NabuccoInjectionReciever {

    private ImportConfigurationListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new ImportConfigurationListViewBrowserElement instance.
     *
     * @param datatypeList the List<ImportConfiguration>.
     */
    public ImportConfigurationListViewBrowserElement(final List<ImportConfiguration> datatypeList) {
        this(datatypeList.toArray(new ImportConfiguration[datatypeList.size()]));
    }

    /**
     * Constructs a new ImportConfigurationListViewBrowserElement instance.
     *
     * @param datatypeArray the ImportConfiguration[].
     */
    public ImportConfigurationListViewBrowserElement(final ImportConfiguration[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ImportConfigurationListViewBrowserElement.class);
        listViewBrowserElementHandler = instance.inject(ImportConfigurationListViewBrowserElementHandler.class);
        viewModel = new ImportConfigurationListViewModel();
        viewModel.setElements(datatypeArray);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        listViewBrowserElementHandler.createChildren(viewModel, this);
    }

    @Override
    public void removeBrowserElement(final BrowserElement element) {
        super.removeBrowserElement(element);
        listViewBrowserElementHandler.removeChild(element, this);
    }
}
