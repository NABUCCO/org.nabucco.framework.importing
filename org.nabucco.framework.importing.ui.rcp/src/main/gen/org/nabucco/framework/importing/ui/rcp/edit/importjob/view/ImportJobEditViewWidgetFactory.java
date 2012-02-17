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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditViewModel;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerComposite;
import org.nabucco.framework.plugin.base.component.picker.dialog.ElementPickerParameter;
import org.nabucco.framework.plugin.base.component.picker.dialog.LabelForDialog;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * ImportJobEditViewWidgetFactory<p/><p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ImportJobEditViewWidgetFactory extends WidgetFactory {

    private ImportJobEditViewModel model;

    public static final String SECTION = "SectionName";

    public static final String LABEL_NAME = "importJob.name";

    public static final String OBSERVE_VALUE_NAME = ImportJobEditViewModel.PROPERTY_IMPORTJOB_NAME;

    public static final String LABEL_DESCRIPTION = "importJob.description";

    public static final String OBSERVE_VALUE_DESCRIPTION = ImportJobEditViewModel.PROPERTY_IMPORTJOB_DESCRIPTION;

    public static final String LABEL_OWNER = "importJob.owner";

    public static final String OBSERVE_VALUE_OWNER = ImportJobEditViewModel.PROPERTY_IMPORTJOB_OWNER;

    public static final String LABEL_IMPORTCONFIGPICKER = "importJob.importConfig";

    public static final String TITLE_IMPORTCONFIGPICKER = "newTITLE";

    public static final String MESSAGE_IMPORTCONFIGPICKER = "newMESSAGE";

    public static final String SHELL_TITLE_IMPORTCONFIGPICKER = "newSHELL_TITLE";

    public static final String MESSAGE_TABLE_IMPORTCONFIGPICKER = "newMESSAGE_TABLE";

    public static final String MESSAGE_COMBO_IMPORTCONFIGPICKER = "newMESSAGE_COMBO";

    public static final String PATH_LABEL_IMPORTCONFIGPICKER = "newPATH_LABEL";

    public static final String OBSERVE_VALUE_IMPORTCONFIGPICKER = ImportJobEditViewModel.PROPERTY_IMPORTCONFIG_NAME;

    /**
     * Constructs a new ImportJobEditViewWidgetFactory instance.
     *
     * @param model the ImportJobEditViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public ImportJobEditViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit, ImportJobEditViewModel model) {
        super(nabuccoFormToolKit);
        this.model = model;
    }

    /**
     * CreateSectionHeading.
     *
     * @param parent the Composite.
     * @return the Section.
     */
    public Section createSectionHeading(Composite parent) {
        return nabuccoFormToolKit.createSection(parent, SECTION, new GridLayout());
    }

    /**
     * CreateLabelName.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelName(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_NAME);
    }

    /**
     * CreateInputFieldName.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldName(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_NAME);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelDescription.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelDescription(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_DESCRIPTION);
    }

    /**
     * CreateInputFieldDescription.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldDescription(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_DESCRIPTION);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelOwner.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelOwner(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_OWNER);
    }

    /**
     * CreateInputFieldOwner.
     *
     * @param parent the Composite.
     * @return the Text.
     */
    public Text createInputFieldOwner(Composite parent) {
        Text result = nabuccoFormToolKit.createTextInput(parent);
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement = SWTObservables.observeText(result, SWT.Modify);
        IObservableValue modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_OWNER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        return result;
    }

    /**
     * CreateLabelImportConfigPicker.
     *
     * @param parent the Composite.
     * @return the Label.
     */
    public Label createLabelImportConfigPicker(Composite parent) {
        return nabuccoFormToolKit.createRealLabel(parent, LABEL_IMPORTCONFIGPICKER);
    }

    /**
     * CreateElementPickerImportConfigPicker.
     *
     * @param params the ElementPickerParameter.
     * @param parent the Composite.
     */
    public void createElementPickerImportConfigPicker(Composite parent, ElementPickerParameter params) {
        ElementPickerComposite picker = new ElementPickerComposite(parent, SWT.NONE, params,
                params.getInputFieldLabelProvider(), new LabelForDialog(TITLE_IMPORTCONFIGPICKER,
                        MESSAGE_IMPORTCONFIGPICKER, SHELL_TITLE_IMPORTCONFIGPICKER, MESSAGE_TABLE_IMPORTCONFIGPICKER,
                        MESSAGE_COMBO_IMPORTCONFIGPICKER, PATH_LABEL_IMPORTCONFIGPICKER));
        DataBindingContext bindingContext = new DataBindingContext();
        IObservableValue uiElement;
        IObservableValue modelElement;
        uiElement = SWTObservables.observeText(picker.getInputText(), SWT.Modify);
        modelElement = BeansObservables.observeValue(model, OBSERVE_VALUE_IMPORTCONFIGPICKER);
        bindingContext.bindValue(uiElement, modelElement, null, null);
        picker.addElementSelected(new ImportConfigPickerHandler(model));
    }
}
