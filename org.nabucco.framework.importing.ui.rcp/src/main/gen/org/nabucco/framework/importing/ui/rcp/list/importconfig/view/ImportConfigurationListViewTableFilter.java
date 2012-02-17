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

import org.eclipse.jface.viewers.Viewer;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoTableFilter;

/**
 * ImportConfigurationListViewTableFilter<p/>ListView for ImportConfiguration<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ImportConfigurationListViewTableFilter extends NabuccoTableFilter {

    /** Constructs a new ImportConfigurationListViewTableFilter instance. */
    public ImportConfigurationListViewTableFilter() {
        super();
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        boolean result = false;
        if (((null == searchFilter.getFilter()) || (0 == searchFilter.getFilter().length()))) {
            result = true;
        } else if ((element instanceof ImportConfiguration)) {
            ImportConfiguration datatype = ((ImportConfiguration) element);
            result = (result || this.contains(datatype.getOwner(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getName(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getDescription(), searchFilter.getFilter()));
            result = (result || this.contains(datatype.getScriptName(), searchFilter.getFilter()));
        }
        return result;
    }
}
