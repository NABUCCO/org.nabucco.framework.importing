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
package org.nabucco.framework.importing.ui.rcp.search.importjob.model;

import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.importing.facade.message.ImportJobListMsg;
import org.nabucco.framework.importing.facade.message.search.ImportJobSearchRq;
import org.nabucco.framework.importing.ui.rcp.browser.importjob.ImportJobListViewBrowserElement;
import org.nabucco.framework.importing.ui.rcp.communication.ImportingComponentServiceDelegateFactory;
import org.nabucco.framework.importing.ui.rcp.communication.search.SearchImportingDelegate;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchModel;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;

/**
 * ImportJobSearchBusinessModel
 * 
 * @author Christian Nicolaus, PRODYNA AG
 */
public class ImportJobSearchBusinessModel implements NabuccoComponentSearchModel {

    public static final String ID = "org.nabucco.framework.importing.ui.rcp.search.importJob.model.ImportJobSearchBusinessModel";

    /**
     * {@inheritDoc}
     */
    @Override
    public ImportJobListViewBrowserElement search(NabuccoComponentSearchParameter searchParameter) {

        if (searchParameter instanceof ImportJobSearchViewModel) {
            try {
                ImportJobSearchViewModel searchViewModel = (ImportJobSearchViewModel) searchParameter;

                SearchImportingDelegate searchDelegate = ImportingComponentServiceDelegateFactory
                        .getInstance().getSearchImporting();

                ImportJobSearchRq request = createImportJoburationSearchRqMsg(searchViewModel);

                ImportJobListMsg response = searchDelegate.searchImportJob(request);

                if (!response.getImportJobList().isEmpty()) {
                    return new ImportJobListViewBrowserElement(response.getImportJobList());
                }

            } catch (ClientException e) {
                Activator.getDefault().logError(e);
            }
        }
        return null;
    }

    private ImportJobSearchRq createImportJoburationSearchRqMsg(
            ImportJobSearchViewModel searchViewModel) {

        ImportJobSearchRq msg = new ImportJobSearchRq();

        Name name = this.getNameFromModel(searchViewModel);
        Owner owner = this.getOwnerFromModel(searchViewModel);
        Description description = this.getDescriptionFromModel(searchViewModel);

        msg.setOwner(owner);
        msg.setName(name);
        msg.setDescription(description);

        return msg;
    }

    private Name getNameFromModel(ImportJobSearchViewModel searchViewModel) {
        String name = searchViewModel.getImportJobName();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return new Name(name);
    }

    private Owner getOwnerFromModel(ImportJobSearchViewModel searchViewModel) {
        String owner = searchViewModel.getImportJobOwner();
        if (owner == null || owner.isEmpty()) {
            return null;
        }
        return new Owner(owner);
    }

    private Description getDescriptionFromModel(ImportJobSearchViewModel searchViewModel) {
        String description = searchViewModel.getImportJobDescription();
        if (description == null || description.isEmpty()) {
            return null;
        }
        return new Description(description);
    }

}
