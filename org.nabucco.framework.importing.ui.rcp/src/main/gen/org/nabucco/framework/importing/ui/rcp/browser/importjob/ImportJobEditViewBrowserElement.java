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
package org.nabucco.framework.importing.ui.rcp.browser.importjob;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditViewModel;
import org.nabucco.framework.plugin.base.model.browser.DatatypeBrowserElement;

/**
 * ImportJobEditViewBrowserElement<p/>Leading datatype of the ImportJobEditView<p/>
 *
 * @author Undefined
 */
public class ImportJobEditViewBrowserElement extends DatatypeBrowserElement implements NabuccoInjectionReciever {

    private ImportJobEditViewModel viewModel;

    private ImportJobEditViewBrowserElementHandler browserHandler;

    /**
     * Constructs a new ImportJobEditViewBrowserElement instance.
     *
     * @param datatype the ImportJob.
     */
    public ImportJobEditViewBrowserElement(final ImportJob datatype) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ImportJobEditViewBrowserElement.class);
        browserHandler = instance.inject(ImportJobEditViewBrowserElementHandler.class);
        viewModel = new ImportJobEditViewModel();
        viewModel.setImportJob(datatype);
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
     * @return the ImportJobEditViewModel.
     */
    public ImportJobEditViewModel getViewModel() {
        return this.viewModel;
    }

    /**
     * Setter for the ViewModel.
     *
     * @param viewModel the ImportJobEditViewModel.
     */
    public void setViewModel(ImportJobEditViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
