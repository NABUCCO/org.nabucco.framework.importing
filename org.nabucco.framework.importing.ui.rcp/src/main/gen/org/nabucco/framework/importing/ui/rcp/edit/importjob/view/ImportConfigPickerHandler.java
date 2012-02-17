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
package org.nabucco.framework.importing.ui.rcp.edit.importjob.view;

import org.eclipse.swt.events.TypedEvent;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerListener;

/**
 * ImportConfigPickerHandler<p/><p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ImportConfigPickerHandler implements ElementPickerListener {

    private ImportJobEditViewModel model;

    /**
     * Constructs a new ImportConfigPickerHandler instance.
     *
     * @param model the ImportJobEditViewModel.
     */
    public ImportConfigPickerHandler(final ImportJobEditViewModel model) {
        super();
        this.model = model;
    }

    @Override
    public void elementSelected(TypedEvent event) {
        if ((event.data instanceof ImportConfiguration)) {
            model.setImportConfig(((ImportConfiguration) event.data));
        } else if ((event.data == null)) {
            model.setImportConfig(null);
        }
    }
}
