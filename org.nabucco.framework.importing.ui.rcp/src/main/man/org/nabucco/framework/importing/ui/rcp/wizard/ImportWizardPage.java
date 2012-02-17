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

import java.io.File;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.wizard.listener.ImportBrowseButtonListener;
import org.nabucco.framework.importing.ui.rcp.wizard.listener.ImportSelectionListener;
import org.nabucco.framework.importing.ui.rcp.wizard.tree.ImportConfigurationTree;
import org.nabucco.framework.importing.ui.rcp.wizard.tree.listener.ImportTreeCheckListener;
import org.nabucco.framework.importing.ui.rcp.wizard.tree.listener.ImportTreeSelectionChangeListener;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * ImportWizardPage
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ImportWizardPage extends WizardPage implements ImportWizardConstants {

    private CheckboxTreeViewer viewer;

    private ImportWizardModel model;

    private List<ImportConfiguration> configurations;

    private ImportWizardStore store;

    /**
     * Creates a new {@link ImportWizardPage} instance.
     * 
     * @param configurations
     *            the import configurations
     */
    protected ImportWizardPage(ImportWizardModel model, List<ImportConfiguration> configurations) {
        super(I18N.i18n(PAGE_NAME), I18N.i18n(PAGE_TITLE), null);

        this.model = model;
        this.configurations = configurations;
    }

    @Override
    public void createControl(Composite parent) {
        super.initializeDialogUnits(parent);

        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayout(new GridLayout());

        GridData data = new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL);
        composite.setLayoutData(data);

        NabuccoFormToolkit toolkit = new NabuccoFormToolkit(parent);

        this.store = new ImportWizardStore(super.getDialogSettings(), this.model);
        this.store.restoreWidgetValues();

        this.layoutConfigGroup(toolkit, composite);
        this.layoutDestinationGroup(composite);

        super.setControl(parent);

        Dialog.applyDialogFont(composite);
    }

    /**
     * Finish the import page.
     */
    public boolean finish() {

        boolean valid = this.validatePage();

        if (valid) {
            this.store.saveWidgetValues();

            return true;
        }

        return false;
    }

    /**
     * Validate the current
     */
    private boolean validatePage() {

        // Validate Source
        if (this.model.getDestination() == null) {
            super.setErrorMessage(I18N.i18n(ERROR_INVALID_FILE));
            return false;
        }

        // Validate Source
        File file = new File(this.model.getDestination());
        if (!file.exists() || !file.isFile()) {
            super.setErrorMessage(I18N.i18n(ERROR_INVALID_FILE));
            return false;
        }

        // Validate Configurations
        if (!this.model.isImportAll()) {
            Object[] checkedElements = this.viewer.getCheckedElements();
            if (checkedElements == null || checkedElements.length == 0) {
                super.setErrorMessage(I18N.i18n(ERROR_NO_SELECTION));
                return false;
            }
        }

        return true;
    }

    /**
     * Layout the import configuration group
     * 
     * @param toolkit
     *            the toolkit
     * @param parent
     *            the parent composite
     * 
     * @return the layouted group
     */
    private Group layoutConfigGroup(NabuccoFormToolkit toolkit, Composite parent) {

        Button importAll = this.layoutImportAll(parent);

        Group group = new Group(parent, SWT.NONE);

        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        data.horizontalIndent = IDialogConstants.INDENT;

        Object compositeLayout = parent.getLayout();
        if (compositeLayout instanceof GridLayout) {
            data.horizontalIndent -= ((GridLayout) compositeLayout).marginWidth;
            data.horizontalIndent -= ((GridLayout) compositeLayout).marginLeft;
        }

        group.setLayoutData(data);

        GridLayout layout = new GridLayout();
        group.setLayout(layout);

        ImportConfigurationTree tree = this.layoutTree(group);
        CheckboxTreeViewer viewer = tree.getViewer();

        importAll.addSelectionListener(new ImportSelectionListener(viewer, false));

        this.layoutDescription(toolkit, group);
        this.layoutSelectionButtons(group, viewer);

        viewer.setInput(this.configurations);

        return group;
    }

    /**
     * Layout the import all checkbox.
     * 
     * @param parent
     *            the parent composite
     */
    private Button layoutImportAll(Composite parent) {
        Button importAll = new Button(parent, SWT.CHECK);
        importAll.setText(I18N.i18n(CHECKBOX_EXPORT_ALL));

        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeSelection(importAll);
        IObservableValue modelElement = BeansObservables.observeValue(this.model,
                ImportWizardModel.PROPERTY_EXPORTALL);
        bindingContext.bindValue(uiElement, modelElement);

        return importAll;
    }

    /**
     * Create and layout the import configuration tree.
     * 
     * @param parent
     *            the parent composite
     * 
     * @return the layouted tree
     */
    private ImportConfigurationTree layoutTree(Composite parent) {

        ImportConfigurationTree tree = new ImportConfigurationTree(parent);
        GridData data = new GridData(GridData.FILL_BOTH);
        tree.setLayoutData(data);

        viewer = tree.getViewer();
        viewer.addSelectionChangedListener(new ImportTreeSelectionChangeListener(viewer, this.model));
        viewer.addCheckStateListener(new ImportTreeCheckListener(viewer, this.model));

        DataBindingContext bindingContext = new DataBindingContext();
        IViewerObservableSet uiElement = ViewersObservables.observeCheckedElements(viewer,
                ImportConfiguration.class);
        IObservableSet modelElement = BeansObservables.observeSet(this.model,
                ImportWizardModel.PROPERTY_CONFIGURATIONS);
        bindingContext.bindSet(uiElement, modelElement);

        return tree;
    }

    /**
     * Create and layout the description widgets.
     * 
     * @param toolkit
     *            the toolkit
     * @param parent
     *            the parent element
     */
    private void layoutDescription(NabuccoFormToolkit toolkit, Composite parent) {
        Label label = toolkit.createRealLabel(parent, LABEL_DESCRIPTION);
        label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL_HORIZONTAL;
        data.grabExcessHorizontalSpace = true;

        Text text = new Text(parent, SWT.V_SCROLL | SWT.READ_ONLY | SWT.BORDER | SWT.WRAP);
        GridData descriptionData = new GridData(GridData.FILL_BOTH);
        descriptionData.heightHint = super.convertHeightInCharsToPixels(3);
        text.setLayoutData(descriptionData);

        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(text);
        IObservableValue modelElement = BeansObservables.observeValue(this.model,
                ImportWizardModel.PROPERTY_DESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement);
    }

    /**
     * Add the selection and deselection buttons to the composite.
     * 
     * @param parent
     *            the parent composite
     * @param viewer
     *            the checkbox tree viewer
     */
    private Composite layoutSelectionButtons(Composite parent, CheckboxTreeViewer viewer) {

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));

        GridData data = new GridData(GridData.GRAB_HORIZONTAL);
        data.grabExcessHorizontalSpace = true;
        composite.setLayoutData(data);
        Font parentFont = parent.getFont();
        composite.setFont(parentFont);

        IObservableValue uiElement;
        IObservableValue modelElement;
        DataBindingContext bindingContext = new DataBindingContext();

        Button selectAllButton = this.createButton(composite, IDialogConstants.SELECT_ALL_ID,
                I18N.i18n(BUTTON_SELECT));

        uiElement = SWTObservables.observeEnabled(selectAllButton);
        modelElement = BeansObservables.observeValue(this.model,
                ImportWizardModel.PROPERTY_EXPORTSEPERATE);
        bindingContext.bindValue(uiElement, modelElement);

        selectAllButton.addSelectionListener(new ImportSelectionListener(viewer, true));
        selectAllButton.setFont(parentFont);

        Button deselectAllButton = createButton(composite, IDialogConstants.DESELECT_ALL_ID,
                I18N.i18n(BUTTON_DESELECT));

        uiElement = SWTObservables.observeEnabled(deselectAllButton);
        modelElement = BeansObservables.observeValue(this.model,
                ImportWizardModel.PROPERTY_EXPORTSEPERATE);
        bindingContext.bindValue(uiElement, modelElement);

        deselectAllButton.addSelectionListener(new ImportSelectionListener(viewer, false));
        deselectAllButton.setFont(parentFont);

        return composite;
    }

    /**
     * Creates a new button with the given id.
     * 
     * @param parent
     *            the parent composite
     * @param id
     *            the id of the button (see <code>IDialogConstants.*_ID</code> constants for
     *            standard dialog button ids)
     * @param label
     *            the label of the button
     */
    private Button createButton(Composite parent, int id, String label) {
        // increment the number of columns in the button bar
        ((GridLayout) parent.getLayout()).numColumns++;

        Button button = new Button(parent, SWT.PUSH);
        button.setFont(parent.getFont());

        setButtonLayoutData(button);

        button.setData(new Integer(id));
        button.setText(label);

        return button;
    }

    /**
     * Create the import destination specification widgets
     * 
     * @param parent
     *            the parent composite
     */
    private void layoutDestinationGroup(Composite parent) {

        Composite group = new Composite(parent, SWT.NONE);

        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        group.setLayout(layout);

        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
        group.setLayoutData(data);

        Label dest = new Label(group, SWT.NONE);
        dest.setText(I18N.i18n(LABEL_DESTINATION));

        // Destination Field
        Combo combo = new Combo(group, SWT.SINGLE | SWT.BORDER);
        combo.setFocus();

        for (String history : this.model.getDestinationNames()) {
            combo.add(history);
        }

        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeSelection(combo);
        IObservableValue modelElement = BeansObservables.observeValue(this.model,
                ImportWizardModel.PROPERTY_DESTINATION);
        bindingContext.bindValue(uiElement, modelElement);

        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        combo.setLayoutData(data);

        // Browse Button
        Button button = new Button(group, SWT.PUSH);
        button.setText(I18N.i18n(BUTTON_BROWSE));
        setButtonLayoutData(button);

        button.addSelectionListener(new ImportBrowseButtonListener(super.getContainer().getShell(),
                this.model));

        // Vertical Spacer
        new Label(parent, SWT.NONE);
    }

}
