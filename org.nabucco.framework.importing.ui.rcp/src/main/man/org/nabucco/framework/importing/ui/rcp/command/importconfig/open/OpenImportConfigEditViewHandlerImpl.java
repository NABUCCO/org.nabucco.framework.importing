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

import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.view.ImportConfigEditView;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.view.ImportConfigListView;
import org.nabucco.framework.plugin.base.command.AbstractOpenCorrespondingEditViewHandlerImpl;


/**
 * OpenImportConfigEditViewHandlerImpl
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class OpenImportConfigEditViewHandlerImpl
        extends
        AbstractOpenCorrespondingEditViewHandlerImpl<ImportConfigEditViewModel, ImportConfiguration>
        implements OpenImportConfigEditViewHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void openImportConfigEditView() {
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void openCorrespondingEditView(ImportConfigEditViewModel model,
            ImportConfiguration selected) {
        model.setImportConfig(selected);
    }

    /**
     * Getter.
     * 
     * @return value
     */
    @Override
    protected String getEditorViewId() {
        return ImportConfigEditView.ID;
    }

    @Override
    public String getListViewId() {
        return ImportConfigListView.ID;
    }

}
