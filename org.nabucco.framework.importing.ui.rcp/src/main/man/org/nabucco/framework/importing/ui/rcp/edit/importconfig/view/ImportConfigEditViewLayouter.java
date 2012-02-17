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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Section;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel;
import org.nabucco.framework.importing.ui.rcp.edit.importconfig.view.label.ImportConfigEditViewImportTypeLabelProvider;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableLabelProvider;
import org.nabucco.framework.plugin.base.component.picker.combo.ElementPickerComboParameter;
import org.nabucco.framework.plugin.base.layout.BaseLayouter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * ImportingEditViewLayouter
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportConfigEditViewLayouter extends BaseLayouter<ImportConfigEditViewModel> {

    private ImportConfigEditViewWidgetFactory widgetFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Composite layoutConcrete(Composite parent, NabuccoMessageManager messageManager,
            ImportConfigEditViewModel model, Layoutable view) {
        widgetFactory = new ImportConfigEditViewWidgetFactory(super.nabuccoFormToolkit, model);
        return layoutSection(parent);
    }

    /**
     * Layouts the section.
     * 
     * @param parent
     *            the parent of the section
     * 
     * @return the layouted composite
     */
    private Composite layoutSection(Composite parent) {
        Section section = widgetFactory.createSectionHeading(parent);

        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 10;
        layout.horizontalSpacing = 20;

        Composite sectionBody = widgetFactory.getNabuccoFormToolKit().createComposite(section,
                layout);
        section.setClient(sectionBody);

        layoutLabelAndInputFieldName(sectionBody);

        layoutLabelAndInputFieldDescription(sectionBody);

        layoutLabelAndInputFieldOwner(sectionBody);

        layoutLabelAndInputFieldScriptName(sectionBody);

        layoutLabelAndInputFieldImportType(sectionBody);

        return section;
    }

    /**
     * Layout the code name.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldName(Composite parent) {
        Label label = widgetFactory.createLabelName(parent);
        LayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldName(parent);
        LayouterUtility.layoutDefault(text);

        TextModifyListener listener = new TextModifyListener(text, messageManager);
        text.addModifyListener(listener);
    }

    /**
     * Layout the code description.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldDescription(Composite parent) {
        Label label = widgetFactory.createLabelDescription(parent);
        LayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldDescription(parent);
        LayouterUtility.layoutDefault(text);

        TextModifyListener listener = new TextModifyListener(text, messageManager);
        text.addModifyListener(listener);
    }

    /**
     * Layout the code owner.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldOwner(Composite parent) {
        Label label = widgetFactory.createLabelOwner(parent);
        LayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldOwner(parent);
        LayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the import script name.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldScriptName(Composite parent) {
        Label label = widgetFactory.createLabelScriptName(parent);
        LayouterUtility.layoutDefault(label);

        Text text = widgetFactory.createInputFieldScriptName(parent);
        LayouterUtility.layoutDefault(text);
    }

    /**
     * Layout the import type.
     * 
     * @param parent
     *            the parent section
     */
    private void layoutLabelAndInputFieldImportType(Composite parent) {
        // Label label = widgetFactory.createLabelImportType(parent);
        // LayouterUtility.layoutDefault(label);

        ElementPickerComboParameter params = new ElementPickerComboParameter(
                new ImportConfigEditViewImportTypeContentProvider(), new NabuccoTableLabelProvider(
                        new ILabelProvider[] { new ImportConfigEditViewImportTypeLabelProvider() }));
        widgetFactory.createLabelImportType(parent);
        widgetFactory.createElementComboImportType(parent, params);
    }

}
