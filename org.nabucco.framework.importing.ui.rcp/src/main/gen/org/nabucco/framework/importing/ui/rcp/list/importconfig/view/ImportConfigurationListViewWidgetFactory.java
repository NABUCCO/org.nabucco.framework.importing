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

import org.eclipse.swt.widgets.Composite;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableParameter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;
import org.nabucco.framework.plugin.base.layout.WidgetFactory;
import org.nabucco.framework.plugin.base.model.ListViewModel;
import org.nabucco.framework.plugin.base.view.NabuccoFormToolkit;

/**
 * ImportConfigurationListViewWidgetFactory<p/>ListView for ImportConfiguration<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ImportConfigurationListViewWidgetFactory extends WidgetFactory {

    /**
     * Constructs a new ImportConfigurationListViewWidgetFactory instance.
     *
     * @param nabuccoFormToolKit the NabuccoFormToolkit.
     */
    public ImportConfigurationListViewWidgetFactory(NabuccoFormToolkit nabuccoFormToolKit) {
        super(nabuccoFormToolKit);
    }

    /**
     * CreateTable.
     *
     * @param model the ListViewModel<?>.
     * @param parameter the NabuccoTableParameter.
     * @param parent the Composite.
     * @return the NabuccoTableViewer.
     */
    public NabuccoTableViewer createTable(Composite parent, NabuccoTableParameter parameter,
            ListViewModel<?> model) {
        return nabuccoFormToolKit.createNabuccoTable(parent, parameter, model);
    }
}
