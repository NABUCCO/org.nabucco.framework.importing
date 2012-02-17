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
package org.nabucco.framework.importing.ui.rcp.overview.view;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.view.ImportConfigEditView;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.view.ImportJobEditView;
import org.nabucco.framework.plugin.base.component.overview.model.NabuccoOverviewAction;
import org.nabucco.framework.plugin.base.component.overview.model.NabuccoOverviewModel;
import org.nabucco.framework.plugin.base.component.overview.view.NabuccoOverviewView;


/**
 * ImportingOverviewView
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportingOverviewView extends NabuccoOverviewView {

    public final static String ID = "org.nabucco.framework.importing.ui.rcp.overview.view.ImportingOverviewView";

    public ImportingOverviewView() {
        createModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createFormControl(Form form) {
        getLayouter().layout(form.getBody(), getMessageManager(), getModel());
    }

    /**
     * Create the view model
     */
    private void createModel() {

        NabuccoOverviewModel localModel = new NabuccoOverviewModel();

        NabuccoOverviewAction action = new NabuccoOverviewAction(
                "org.nabucco.framework.importing.ui.rcp.overview.view.ImportingOverviewView.action.newimportconfig.label",
                "org.nabucco.framework.importing.ui.rcp.overview.view.ImportingOverviewView.action.newimportconfig.description",
                ImportConfigEditView.ID);
        localModel.getComponentActions().add(action);

        action = new NabuccoOverviewAction(
                "org.nabucco.framework.importing.ui.rcp.overview.view.ImportingOverviewView.action.newimportjob.label",
                "org.nabucco.framework.importing.ui.rcp.overview.view.ImportingOverviewView.action.newimportjob.description",
                ImportJobEditView.ID);
        // localModel.getComponentActions().add(action);

        model = localModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createHeadControl(Composite head) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createToolbarActions(IToolBarManager toolbarManager) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getManagedFormTitle() {
        return "Importing Overview";
    }

    public ImportingOverviewLayouter getLayouter() {
        return new ImportingOverviewLayouter();
    }

}
