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
package org.nabucco.framework.importing.ui.rcp.search.importconfig.view;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.nabucco.framework.importing.ui.rcp.search.importconfig.model.ImportConfigSearchViewModel;

/**
 * ImportConfigSearchViewImportTypeComboBoxHandler<p/>search view for an import configuration<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ImportConfigSearchViewImportTypeComboBoxHandler implements SelectionListener {

    private ImportConfigSearchViewModel model;

    /**
     * Constructs a new ImportConfigSearchViewImportTypeComboBoxHandler instance.
     *
     * @param model the ImportConfigSearchViewModel.
     */
    public ImportConfigSearchViewImportTypeComboBoxHandler(final ImportConfigSearchViewModel model) {
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
