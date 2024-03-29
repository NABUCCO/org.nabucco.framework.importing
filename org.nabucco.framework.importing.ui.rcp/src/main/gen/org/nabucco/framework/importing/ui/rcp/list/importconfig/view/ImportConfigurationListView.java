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
import org.eclipse.ui.forms.widgets.Form;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.importing.ui.rcp.list.importconfig.model.ImportConfigurationListViewModel;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoComponentListView;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoCompositeTextFilter;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableViewer;

/**
 * ImportConfigurationListView<p/>ListView for ImportConfiguration<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ImportConfigurationListView extends NabuccoComponentListView {

    public static final String ID = "org.nabucco.framework.importing.ui.list.importconfig.ImportConfigListView";

    public static final String TITLE = (ID + ".title");

    /** Constructs a new ImportConfigurationListView instance. */
    public ImportConfigurationListView() {
        super();
        model = new ImportConfigurationListViewModel();
    }

    @Override
    protected NabuccoCompositeTextFilter createFilter(final Composite parent) {
        return new NabuccoCompositeTextFilter(parent);
    }

    @Override
    protected void createFormControl(Form form) {
        Composite o = this.getLayouter().layout(form.getBody(), this.getMessageManager(), model,
                this);
        if ((o instanceof NabuccoTableViewer)) {
            tableViewer = ((NabuccoTableViewer) o);
        }
    }

    @Override
    public String getManagedFormTitle() {
        return I18N.i18n(TITLE);
    }

    @Override
    public void setFocus() {
    }

    @Override
    public String getId() {
        return ImportConfigurationListView.ID;
    }
}
