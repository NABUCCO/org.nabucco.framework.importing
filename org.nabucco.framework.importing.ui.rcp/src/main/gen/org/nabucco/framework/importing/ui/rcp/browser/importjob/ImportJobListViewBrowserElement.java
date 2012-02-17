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

import java.util.List;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.ui.rcp.list.importjob.model.ImportJobListViewModel;
import org.nabucco.framework.plugin.base.model.browser.BrowserElement;
import org.nabucco.framework.plugin.base.model.browser.BrowserListElement;

/**
 * ImportJobListViewBrowserElement
 *
 * @author Undefined
 */
public class ImportJobListViewBrowserElement extends BrowserListElement<ImportJobListViewModel> implements
        NabuccoInjectionReciever {

    private ImportJobListViewBrowserElementHandler listViewBrowserElementHandler;

    /**
     * Constructs a new ImportJobListViewBrowserElement instance.
     *
     * @param datatypeList the List<ImportJob>.
     */
    public ImportJobListViewBrowserElement(final List<ImportJob> datatypeList) {
        this(datatypeList.toArray(new ImportJob[datatypeList.size()]));
    }

    /**
     * Constructs a new ImportJobListViewBrowserElement instance.
     *
     * @param datatypeArray the ImportJob[].
     */
    public ImportJobListViewBrowserElement(final ImportJob[] datatypeArray) {
        super();
        NabuccoInjector instance = NabuccoInjector.getInstance(ImportJobListViewBrowserElement.class);
        listViewBrowserElementHandler = instance.inject(ImportJobListViewBrowserElementHandler.class);
        viewModel = new ImportJobListViewModel();
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
