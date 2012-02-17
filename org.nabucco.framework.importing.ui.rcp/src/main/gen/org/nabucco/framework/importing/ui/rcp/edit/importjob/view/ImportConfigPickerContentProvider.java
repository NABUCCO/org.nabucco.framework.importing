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

import java.util.Map;
import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjectionReciever;
import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerContentProvider;

/**
 * ImportConfigPickerContentProvider<p/><p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ImportConfigPickerContentProvider implements ElementPickerContentProvider, NabuccoInjectionReciever {

    private ImportConfigPickerContentProviderHandler handler = NabuccoInjector.getInstance(
            ImportConfigPickerContentProvider.class).inject(ImportConfigPickerContentProviderHandler.class);

    private Map<String, ImportConfiguration[]> values;

    private ImportJobEditViewModel viewModel;

    /**
     * Constructs a new ImportConfigPickerContentProvider instance.
     *
     * @param viewModel the ImportJobEditViewModel.
     */
    public ImportConfigPickerContentProvider(ImportJobEditViewModel viewModel) {
        super();
        this.viewModel = viewModel;
    }

    /** InitValues. */
    private void initValues() {
        values = handler.loadAllImportConfiguration(viewModel);
    }

    @Override
    public String[] getPaths() {
        this.initValues();
        return values.keySet().toArray(new String[values.size()]);
    }

    @Override
    public Object[] getElements(Object arg0) {
        this.initValues();
        if ((arg0 instanceof String)) {
            return values.get(arg0);
        }
        return new Object[0];
    }

    @Override
    public void dispose() {
        values = null;
    }

    @Override
    public void inputChanged(Viewer arg0, Object anOldValue, Object anNewValue) {
    }
}
