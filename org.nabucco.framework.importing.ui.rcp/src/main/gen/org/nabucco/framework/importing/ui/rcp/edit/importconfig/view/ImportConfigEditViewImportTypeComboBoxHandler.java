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
package org.nabucco.framework.importing.ui.rcp.edit.importconfig.view;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel;

/**
 * ImportConfigEditViewImportTypeComboBoxHandler<p/>the import configuraton edit view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ImportConfigEditViewImportTypeComboBoxHandler implements SelectionListener {

    private ImportConfigEditViewModel model;

    /**
     * Constructs a new ImportConfigEditViewImportTypeComboBoxHandler instance.
     *
     * @param model the ImportConfigEditViewModel.
     */
    public ImportConfigEditViewImportTypeComboBoxHandler(final ImportConfigEditViewModel model) {
        super();
        this.model = model;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent selectionEvent) {
        if ((selectionEvent.widget instanceof Combo)) {
            Combo combo = ((Combo) selectionEvent.widget);
            model.setImportConfigImportType(combo.getText());
        }
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        if ((selectionEvent.widget instanceof Combo)) {
            Combo combo = ((Combo) selectionEvent.widget);
            model.setImportConfigImportType(combo.getText());
        }
    }
}
