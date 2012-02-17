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
package org.nabucco.framework.importing.ui.rcp.command.importconfig.open;

import org.nabucco.framework.importing.ui.rcp.browser.importconfig.ImportConfigurationListViewBrowserElement;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.model.ImportConfigurationListViewModel;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.view.ImportConfigListView;
import org.nabucco.framework.plugin.base.command.AbstractNabuccoOpenCorrespondingListViewHandlerImpl;


/**
 * OpenImportConfigListViewHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class OpenImportConfigListViewHandlerImpl
        extends
        AbstractNabuccoOpenCorrespondingListViewHandlerImpl<ImportConfigurationListViewBrowserElement, ImportConfigurationListViewModel>
        implements OpenImportConfigListViewHandler {

    @Override
    public void openImportConfigListView() {
        super.run();
    }

    @Override
    protected String getListViewId() {
        return ImportConfigListView.ID;
    }

}