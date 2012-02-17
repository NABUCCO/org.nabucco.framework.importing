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
package org.nabucco.framework.importing.ui.rcp.list.importconfig.view;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.model.ImportConfigurationListViewModel;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.view.comparator.ImportConfigListViewDescriptionComparator;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.view.comparator.ImportConfigListViewNameComparator;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.view.label.ImportConfigListViewDescriptionLabelProvider;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.view.label.ImportConfigListViewNameLabelProvider;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoAbstractListLayouter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoComponentListView;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultListContentProvider;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoDefaultTableSorter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableColumnInfo;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.Layoutable;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;
import org.nabucco.framework.plugin.base.view.NabuccoMessageManager;

/**
 * ImportConfigListViewLayouter
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportConfigListViewLayouter extends
        NabuccoAbstractListLayouter<ImportConfigurationListViewModel> {

    @Override
    public NabuccoTableViewer layout(Composite parent, NabuccoMessageManager messageManager,
            ImportConfigurationListViewModel model, Layoutable view) {

        NabuccoFormToolkit ntk = new NabuccoFormToolkit(parent);

        ImportConfigListViewWidgetFactory widgetFactory = new ImportConfigListViewWidgetFactory(ntk);

        NabuccoCommand doubleClickCommand = null;

        if (view instanceof NabuccoComponentListView) {
            doubleClickCommand = ((NabuccoComponentListView) view).getDoubleClickCommand();
        }

        NabuccoTableParameter parameter = new NabuccoTableParameter(
                new NabuccoDefaultTableSorter<ImportConfiguration>(createTableComparator()),
                new ImportConfigListViewTableFilter(),
                new NabuccoDefaultListContentProvider(model), createTableColumnInfo(),
                doubleClickCommand);

        return widgetFactory.createTable(parent, parameter, model);
    }

    /**
     * @return
     */
    private List<Comparator<ImportConfiguration>> createTableComparator() {
        List<Comparator<ImportConfiguration>> result = new LinkedList<Comparator<ImportConfiguration>>();
        result.add(new ImportConfigListViewNameComparator());
        result.add(new ImportConfigListViewDescriptionComparator());
        return result;
    }

    private NabuccoTableColumnInfo[] createTableColumnInfo() {
        NabuccoTableColumnInfo[] result = {
                new NabuccoTableColumnInfo(
                        "org.nabucco.framework.importing.ui.rcp.list.importconfig.view.ImportConfigListViewLayouter.columnNameLabel",
                        "org.nabucco.framework.importing.ui.rcp.list.importconfig.view.ImportConfigListViewLayouter.columnNameDescription",
                        200, SWT.CENTER, SWT.CENTER, new ImportConfigListViewNameLabelProvider()),
                new NabuccoTableColumnInfo(
                        "org.nabucco.framework.importing.ui.rcp.list.importconfig.view.ImportConfigListViewLayouter.columnDescriptionLabel",
                        "org.nabucco.framework.importing.ui.rcp.list.importconfig.view.ImportConfigListViewLayouter.columnDescriptionDescription",
                        300, SWT.RIGHT, SWT.RIGHT,
                        new ImportConfigListViewDescriptionLabelProvider()) };

        return result;
    }
}
