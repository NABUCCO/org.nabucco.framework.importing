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
package org.nabucco.framework.importing.ui.rcp.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.internal.wizards.preferences.WizardPreferencesPage;

/**
 * ImportWizardStore
 * <p/>
 * Stores local user information of the import dialog.
 * 
 * @see WizardPreferencesPage
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
class ImportWizardStore {

    private static final String STORE_DESTINATION_ID = "ImportWizardStore.STORE_DESTINATION_ID";

    private static final String STORE_DESTINATION_NAMES_ID = "ImportWizardStore.STORE_DESTINATION_NAMES_ID";

    private static final String STORE_EXPORTALL_ID = "ImportWizardStore.STORE_EXPORTALL_ID";

    private static final int COMBO_HISTORY_LENGTH = 5;

    private IDialogSettings settings;

    private ImportWizardModel model;

    /**
     * Creates a new {@link ImportWizardStore} instance.
     * 
     * @param settings
     *            the dialog settings
     * @param model
     *            the model
     */
    public ImportWizardStore(IDialogSettings settings, ImportWizardModel model) {
        this.settings = settings;
        this.model = model;
    }

    /**
     * Save selected values to the dialog store.
     */
    public void saveWidgetValues() {
        if (this.settings == null) {
            return;
        }

        String[] directoryNames = this.settings.getArray(STORE_DESTINATION_NAMES_ID);
        if (directoryNames == null) {
            directoryNames = new String[0];
        }

        String current = this.model.getDestination();

        directoryNames = this.addToHistory(directoryNames, current);
        this.settings.put(STORE_DESTINATION_NAMES_ID, directoryNames);

        if (current != null && !current.isEmpty()) {
            this.settings.put(STORE_DESTINATION_ID, current);
        }

        this.settings.put(STORE_EXPORTALL_ID, this.model.isImportAll());
    }

    /**
     * Restore the values held last time this wizard was used to completion.
     */
    public void restoreWidgetValues() {

        if (this.settings == null) {
            return;
        }

        String[] directoryNames = this.settings.getArray(STORE_DESTINATION_NAMES_ID);

        if (directoryNames != null) {

            this.model.setDestination(directoryNames[0]);
            for (int i = 0; i < directoryNames.length; i++) {
                String name = directoryNames[i];
                if (name != null && !name.isEmpty()) {
                    this.model.getDestinationNames().add(name);
                }
            }

            String current = settings.get(STORE_DESTINATION_ID);
            if (current != null) {
                this.model.setDestination(current);
            }
        }

        if (this.settings.get(STORE_EXPORTALL_ID) == null) {
            this.model.setImportAll(true);
        } else {
            this.model.setImportAll(this.settings.getBoolean(STORE_EXPORTALL_ID));
        }
    }

    /**
     * Adds an entry to a history, while taking care of duplicate history items and excessively long
     * histories. The assumption is made that all histories should be of length
     * <code>WizardDataTransferPage.COMBO_HISTORY_LENGTH</code>.
     * 
     * @param history
     *            the current history
     * @param newEntry
     *            the entry to add to the history
     */
    private String[] addToHistory(String[] history, String newEntry) {
        List<String> list = new ArrayList<String>(Arrays.asList(history));
        addToHistory(list, newEntry);
        String[] r = new String[list.size()];
        list.toArray(r);
        return r;
    }

    /**
     * Adds an entry to a history, while taking care of duplicate history items and excessively long
     * histories. The assumption is made that all histories should be of length
     * <code>WizardDataTransferPage.COMBO_HISTORY_LENGTH</code>.
     * 
     * @param history
     *            the current history
     * @param newEntry
     *            the entry to add to the history
     */
    private void addToHistory(List<String> history, String newEntry) {
        history.remove(newEntry);
        history.add(0, newEntry);

        // since only one new item was added, we can be over the limit
        // by at most one item
        if (history.size() > COMBO_HISTORY_LENGTH) {
            history.remove(COMBO_HISTORY_LENGTH);
        }
    }
}
