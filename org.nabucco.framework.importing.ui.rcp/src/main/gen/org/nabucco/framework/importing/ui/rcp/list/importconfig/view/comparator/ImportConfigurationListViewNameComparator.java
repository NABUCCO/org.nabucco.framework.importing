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
package org.nabucco.framework.importing.ui.rcp.list.importconfig.view.comparator;

import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.plugin.base.component.list.view.NabuccoColumComparator;

/**
 * ImportConfigurationListViewNameComparator
 *
 * @author Undefined
 */
public class ImportConfigurationListViewNameComparator extends
        NabuccoColumComparator<ImportConfiguration> {

    /** Constructs a new ImportConfigurationListViewNameComparator instance. */
    public ImportConfigurationListViewNameComparator() {
        super();
    }

    @Override
    public int compareConcrete(ImportConfiguration object1, ImportConfiguration object2) {
        return this.compareBasetype(object1.getName(), object2.getName());
    }
}
