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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * ImportJobSearchViewModel<p/>@TODO<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ImportJobSearchViewModel extends NabuccoComponentSearchViewModel<ImportJob> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.importing.ui.search.importjob.ImportJobSearchViewModel";

    private ImportJob importJob;

    public static final String PROPERTY_IMPORTJOB_NAME = "importJobName";

    public static final String PROPERTY_IMPORTJOB_DESCRIPTION = "importJobDescription";

    public static final String PROPERTY_IMPORTJOB_OWNER = "importJobOwner";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new ImportJobSearchViewModel instance.
     *
     * @param viewId the String.
     */
    public ImportJobSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.importJob = new ImportJob();
    }

    @Override
    public String getSearchModelId() {
        return searchModelId;
    }

    @Override
    public NabuccoComponentSearchParameter getSearchParameter() {
        return this;
    }

    /**
     * Getter for the ImportJob.
     *
     * @return the ImportJob.
     */
    public ImportJob getImportJob() {
        return this.importJob;
    }

    /**
     * Setter for the ImportJobName.
     *
     * @param newName the String.
     */
    public void setImportJobName(String newName) {
        if (((importJob != null) && (importJob.getName() == null))) {
            Name name = new Name();
            importJob.setName(name);
        }
        String oldVal = importJob.getName().getValue();
        importJob.getName().setValue(newName);
        this.updateProperty(PROPERTY_IMPORTJOB_NAME, oldVal, newName);
        if (((!oldVal.equals(newName)) && importJob.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importJob.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ImportJobName.
     *
     * @return the String.
     */
    public String getImportJobName() {
        if ((((importJob == null) || (importJob.getName() == null)) || (importJob.getName().getValue() == null))) {
            return "";
        }
        return importJob.getName().getValue();
    }

    /**
     * Setter for the ImportJobDescription.
     *
     * @param newDescription the String.
     */
    public void setImportJobDescription(String newDescription) {
        if (((importJob != null) && (importJob.getDescription() == null))) {
            Description description = new Description();
            importJob.setDescription(description);
        }
        String oldVal = importJob.getDescription().getValue();
        importJob.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_IMPORTJOB_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && importJob.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importJob.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ImportJobDescription.
     *
     * @return the String.
     */
    public String getImportJobDescription() {
        if ((((importJob == null) || (importJob.getDescription() == null)) || (importJob.getDescription().getValue() == null))) {
            return "";
        }
        return importJob.getDescription().getValue();
    }

    /**
     * Setter for the ImportJobOwner.
     *
     * @param newOwner the String.
     */
    public void setImportJobOwner(String newOwner) {
        if (((importJob != null) && (importJob.getOwner() == null))) {
            Owner owner = new Owner();
            importJob.setOwner(owner);
        }
        String oldVal = importJob.getOwner().getValue();
        importJob.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_IMPORTJOB_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && importJob.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importJob.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ImportJobOwner.
     *
     * @return the String.
     */
    public String getImportJobOwner() {
        if ((((importJob == null) || (importJob.getOwner() == null)) || (importJob.getOwner().getValue() == null))) {
            return "";
        }
        return importJob.getOwner().getValue();
    }

    @Override
    public String getId() {
        return ImportJobSearchViewModel.ID;
    }
}
