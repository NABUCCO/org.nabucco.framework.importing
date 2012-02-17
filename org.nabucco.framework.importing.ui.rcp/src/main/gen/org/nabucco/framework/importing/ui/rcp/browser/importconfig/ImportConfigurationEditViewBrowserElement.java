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

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;

/**
 * ImportConfigurationEditViewBrowserElement<p/>Leading datatype of the ImportConfigEditView<p/>
 *
 * @author Undefined
 */
public class ImportConfigurationEditViewBrowserElement extends DatatypeBrowserElement implements
        NabuccoInjectionReciever {

    private ImportConfigEditViewModel viewModel;

    private ImportConfigurationEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new ImportConfigurationEditViewBrowserElement instance.
     *
     * @param datatype the ImportConfiguration.
     */
    public ImportConfigurationEditViewBrowserElement(final ImportConfiguration datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ImportConfigurationEditViewBrowserElement.class);
        browserHandler = instance.inject(ImportConfigurationEditViewBrowserElementHandler.class);
        viewModel = new ImportConfigEditViewModel();
        viewModel.setImportConfig(datatype);
    }

    @Override
    protected void fillDatatype() {
        viewModel = browserHandler.loadFull(viewModel);
    }

    @Override
    protected void createChildren() {
        this.clearChildren();
        browserHandler.createChildren(viewModel, this);
    }

    @Override
    public Map<String, Serializable> getValues() {
        return this.viewModel.getValues();
    }

    /**
     * Getter for the ViewModel.
     *
     * @return the ImportConfigEditViewModel.
     */
    public ImportConfigEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     *
     * @param viewModel the ImportConfigEditViewModel.
     */
    public void setViewModel(ImportConfigEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
