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

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditViewModel;
import org.nabucco.framework.plugin.base.layout.BaseLayouter;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * ImportJobEditViewLayouter
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportJobEditViewLayouter extends BaseLayouter<ImportJobEditViewModel> {

    @Override
    public Composite layoutConcrete(Composite parent, NabuccoMessageManager messageManager,
            ImportJobEditViewModel model, Layoutable view) {
        // TODO Auto-generated method stub
        return null;
    }

    // private ImportJobEditViewWidgetFactory widgetFactory;
    //
    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public Composite layoutConcrete(Composite parent, NabuccoMessageManager messageManager,
    // ImportJobEditViewModel model, Layoutable view) {
    // widgetFactory = new ImportJobEditViewWidgetFactory(super.nabuccoFormToolkit, model);
    // return layoutSection(parent);
    // }
    //
    // /**
    // * Layouts the section.
    // *
    // * @param parent
    // * the parent of the section
    // *
    // * @return the layouted composite
    // */
    // private Composite layoutSection(Composite parent) {
    // Section section = widgetFactory.createSectionHeading(parent);
    //
    // GridLayout layout = new GridLayout(2, false);
    // layout.verticalSpacing = 10;
    // layout.horizontalSpacing = 20;
    //
    // Composite sectionBody = widgetFactory.getNabuccoFormToolKit().createComposite(section,
    // layout);
    // section.setClient(sectionBody);
    //
    // layoutLabelAndInputFieldName(sectionBody);
    //
    // layoutLabelAndInputFieldDescription(sectionBody);
    //
    // layoutLabelAndInputFieldOwner(sectionBody);
    //
    // layoutLabelAndInputFieldSource(sectionBody);
    //
    // return section;
    // }
    //
    // /**
    // * Layout the code name.
    // *
    // * @param parent
    // * the parent section
    // */
    // private void layoutLabelAndInputFieldName(Composite parent) {
    // Label label = widgetFactory.createLabelName(parent);
    // LayouterUtility.layoutDefault(label);
    //
    // Text text = widgetFactory.createInputFieldName(parent);
    // LayouterUtility.layoutDefault(text);
    //
    // TextModifyListener listener = new TextModifyListener(text, messageManager);
    // text.addModifyListener(listener);
    // }
    //
    // /**
    // * Layout the code description.
    // *
    // * @param parent
    // * the parent section
    // */
    // private void layoutLabelAndInputFieldDescription(Composite parent) {
    // Label label = widgetFactory.createLabelDescription(parent);
    // LayouterUtility.layoutDefault(label);
    //
    // Text text = widgetFactory.createInputFieldDescription(parent);
    // LayouterUtility.layoutDefault(text);
    //
    // TextModifyListener listener = new TextModifyListener(text, messageManager);
    // text.addModifyListener(listener);
    // }
    //
    // /**
    // * Layout the code owner.
    // *
    // * @param parent
    // * the parent section
    // */
    // private void layoutLabelAndInputFieldOwner(Composite parent) {
    // Label label = widgetFactory.createLabelOwner(parent);
    // LayouterUtility.layoutDefault(label);
    //
    // Text text = widgetFactory.createInputFieldOwner(parent);
    // LayouterUtility.layoutDefault(text);
    // }
    //
    // /**
    // * Layout the source.
    // *
    // * @param parent
    // * the parent section
    // */
    // private void layoutLabelAndInputFieldSource(Composite parent) {
    // Label label = widgetFactory.createLabelSource(parent);
    // LayouterUtility.layoutDefault(label);
    //
    // Text text = widgetFactory.createInputFieldSource(parent);
    // LayouterUtility.layoutDefault(text);
    // }
    //
    // /**
    // * Create ImportConfigPicker.
    // *
    // * @param parent
    // * the parent composite
    // */
    // private void layoutImportConfigPicker(Composite parent) {
    // ElementPickerParameter params = new ElementPickerParameter(
    // new NabuccoDefaultTableSorter<ImportConfiguration>(createTableColumnComparators()),
    // new ImportConfigListViewTableFilter(), new ImportConfigListViewNameLabelProvider(),
    // new ImportConfigPickerContentProvider(), createTableColumnInfo());
    //
    // widgetFactory.createLabelImportConfigPicker(parent);
    // widgetFactory.createElementPickerImportConfigPicker(parent, params);
    // }
    //
    // /**
    // * Create column information for the codegroup picker table.
    // *
    // * @return the column information
    // */
    // private NabuccoTableColumnInfo[] createTableColumnInfo() {
    //
    // NabuccoTableColumnInfo info;
    // List<NabuccoTableColumnInfo> columnInfoList = new ArrayList<NabuccoTableColumnInfo>(2);
    //
    // info = new NabuccoTableColumnInfo("Name", "Name of the Code", 100, SWT.LEFT, SWT.CENTER,
    // new ImportConfigListViewNameLabelProvider());
    // info.setResizable(false);
    // info.setMoveable(false);
    // columnInfoList.add(info);
    //
    // return columnInfoList.toArray(new NabuccoTableColumnInfo[columnInfoList.size()]);
    // }
    //
    // private List<Comparator<ImportConfiguration>> createTableColumnComparators() {
    // List<Comparator<ImportConfiguration>> result = new
    // LinkedList<Comparator<ImportConfiguration>>();
    // result.add(new ImportConfigListViewNameComparator());
    // return result;
    // }

}
