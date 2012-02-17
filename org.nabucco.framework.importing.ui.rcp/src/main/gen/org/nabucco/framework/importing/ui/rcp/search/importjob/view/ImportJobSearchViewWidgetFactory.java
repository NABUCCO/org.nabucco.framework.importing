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
package org.nabucco.framework.importing.ui.rcp.search.importjob.view;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.nabucco.framework.importing.ui.rcp.search.importjob.model.ImportJobSearchViewModel;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * ImportJobSearchViewWidgetFactory<p/>@TODO<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ImportJobSearchViewWidgetFactory extends WidgetFactory {

    private ImportJobSearchViewModel model;

    public static final String LABEL_NAME = "importJob.name";

    public static final String OBSERVE_VALUE_NAME = ImportJobSearchViewModel.PROPERTY_IMPORTJOB_NAME;

    public static final String LABEL_DESCRIPTION = "importJob.description";

    public static final String OBSERVE_VALUE_DESCRIPTION = ImportJobSearchViewModel.PROPERTY_IMPORTJOB_DESCRIPTION;

    public static final String LABEL_OWNER = "importJob.owner";

    public static final String OBSERVE_VALUE_OWNER = ImportJobSearchViewModel.PROPERTY_IMPORTJOB_OWNER;

    /**
     * Constructs a new ImportJobSearchViewWidgetFactory instance.
     *
     * @param aModel the ImportJobSearchViewModel.
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public ImportJobSearchViewWidgetFactory(final NabuccoFormToolkit nabuccoFormToolKit,
            final ImportJobSearchViewModel aModel) {
        super(nabuccoFormToolKit);
        model = aModel;
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
}
