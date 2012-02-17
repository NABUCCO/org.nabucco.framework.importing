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
package org.nabucco.framework.importing.ui.rcp.wizard.tree.listener;

import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportConfigurationLink;
import org.nabucco.framework.importing.ui.rcp.wizard.ImportWizardModel;

/**
 * ImportTreeCheckListener
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ImportTreeCheckListener implements ICheckStateListener {

    private CheckboxTreeViewer viewer;

    private ImportWizardModel model;

    /**
     * Creates a new {@link ImportTreeCheckListener} instance.
     * 
     * @param viewer
     *            the tree viewer
     * @param model
     *            the import wizard model
     */
    public ImportTreeCheckListener(CheckboxTreeViewer viewer, ImportWizardModel model) {
        this.viewer = viewer;
        this.model = model;
    }

    @Override
    public void checkStateChanged(CheckStateChangedEvent event) {

        this.model.setImportAll(false);

        if (event.getChecked() && event.getElement() instanceof ImportConfiguration) {
            ImportConfiguration configuration = (ImportConfiguration) event.getElement();
            for (ImportConfigurationLink link : configuration.getDependencies()) {
                this.selectElement(link.getConfig().getId());
            }
        } else if (!event.getChecked()
                && event.getElement() instanceof ImportConfiguration
                && this.model.getConfigurations().contains(event.getElement())) {
            this.model.getConfigurations().remove(event.getElement());
        }

    }

    /**
     * Select the element with the given id in the checkbox tree.
     * 
     * @param id
     *            the id to select
     */
    private void selectElement(Long id) {

        Object input = this.viewer.getInput();

        if (!(input instanceof List<?>)) {
            return;
        }

        for (Object entry : (List<?>) input) {
            if (entry instanceof ImportConfiguration) {
                ImportConfiguration current = (ImportConfiguration) entry;

                if (current.getId().equals(id)) {
                    this.model.getConfigurations().add(current);
                    this.viewer.setSubtreeChecked(current, true);

                    // Recursion!
                    for (ImportConfigurationLink link : current.getDependencies()) {
                        this.selectElement(link.getConfig().getId());
                    }
                }
            }
        }
    }

}
