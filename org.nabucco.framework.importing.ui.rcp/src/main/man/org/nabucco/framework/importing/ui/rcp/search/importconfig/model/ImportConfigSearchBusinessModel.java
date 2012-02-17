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
package org.nabucco.framework.importing.ui.rcp.search.importconfig.model;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.search.ImportConfigurationSearchRq;
import org.nabucco.framework.importing.ui.rcp.browser.importconfig.ImportConfigurationListViewBrowserElement;
import org.nabucco.framework.importing.ui.rcp.communication.ImportingComponentServiceDelegateFactory;
import org.nabucco.framework.importing.ui.rcp.communication.search.SearchImportingDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;

/**
 * ImportConfigSearchBusinessModel
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportConfigSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.framework.importing.ui.rcp.search.importconfig.model.ImportConfigSearchBusinessModel";

    /**
     * {@inheritDoc}
     */
    @Override
    public ImportConfigurationListViewBrowserElement search(
            NabuccoComponentSearchParameter searchParameter) {

        if (searchParameter instanceof ImportConfigSearchViewModel) {
            try {
                ImportConfigSearchViewModel searchViewModel = (ImportConfigSearchViewModel) searchParameter;

                SearchImportingDelegate searchDelegate = ImportingComponentServiceDelegateFactory
                        .getInstance().getSearchImporting();

                ImportConfigurationSearchRq request = createImportConfigurationSearchRq(searchViewModel);

                ImportConfigurationListMsg response = searchDelegate
                        .searchImportConfiguration(request);

                if (!response.getImportConfigurationList().isEmpty()) {
                    return new ImportConfigurationListViewBrowserElement(
                            response.getImportConfigurationList());
                }

            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }
        return null;
    }

    private ImportConfigurationSearchRq createImportConfigurationSearchRq(
            ImportConfigSearchViewModel searchViewModel) {

        ImportConfigurationSearchRq msg = new ImportConfigurationSearchRq();

        Name name = this.getNameFromModel(searchViewModel);
        Owner owner = this.getOwnerFromModel(searchViewModel);
        Description description = this.getDescriptionFromModel(searchViewModel);

        msg.setOwner(owner);
        msg.setName(name);
        msg.setDescription(description);

        return msg;
    }

    private Name getNameFromModel(ImportConfigSearchViewModel searchViewModel) {
        String name = searchViewModel.getImportConfigName();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    private Owner getOwnerFromModel(ImportConfigSearchViewModel searchViewModel) {
        String owner = searchViewModel.getImportConfigOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescriptionFromModel(ImportConfigSearchViewModel searchViewModel) {
        String description = searchViewModel.getImportConfigDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
