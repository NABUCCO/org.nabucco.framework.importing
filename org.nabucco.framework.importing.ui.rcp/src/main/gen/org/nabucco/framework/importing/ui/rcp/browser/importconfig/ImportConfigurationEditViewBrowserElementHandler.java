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

import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel;
import org.nabucco.framework.plugin.base.command.CommandHandler;

/**
 * ImportConfigurationEditViewBrowserElementHandler<p/>Leading datatype of the ImportConfigEditView<p/>
 *
 * @author Undefined
 */
public interface ImportConfigurationEditViewBrowserElementHandler extends CommandHandler {

    /**
     * LoadFull.
     *
     * @param importConfiguration the ImportConfigEditViewModel.
     * @return the ImportConfigEditViewModel.
     */
    ImportConfigEditViewModel loadFull(final ImportConfigEditViewModel importConfiguration);

    /**
     * CreateChildren.
     *
     * @param element the ImportConfigurationEditViewBrowserElement.
     * @param viewModel the ImportConfigEditViewModel.
     */
    void createChildren(final ImportConfigEditViewModel viewModel,
            final ImportConfigurationEditViewBrowserElement element);
}
