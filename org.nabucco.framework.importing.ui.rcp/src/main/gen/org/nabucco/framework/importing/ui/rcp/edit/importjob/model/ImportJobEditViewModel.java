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
package org.nabucco.framework.importing.ui.rcp.edit.importjob.model;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * ImportJobEditViewModel<p/><p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ImportJobEditViewModel extends EditViewModel implements Loggable {

    private ImportJob importJob;

    private ImportConfiguration importConfig;

    public static final String PROPERTY_IMPORTJOB_NAME = "importJobName";

    public static final String PROPERTY_IMPORTJOB_DESCRIPTION = "importJobDescription";

    public static final String PROPERTY_IMPORTJOB_OWNER = "importJobOwner";

    public static final String PROPERTY_IMPORTCONFIG_NAME = "importConfigName";

    /** Constructs a new ImportJobEditViewModel instance. */
    public ImportJobEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     *
     * @return the String.
     */
    public String getID() {
        return "org.nabucco.framework.importing.ui.rcp.edit.importjob.model.ImportJobEditViewModel";
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_IMPORTJOB_OWNER, this.getImportJobOwner());
        result.put(PROPERTY_IMPORTJOB_NAME, this.getImportJobName());
        result.put(PROPERTY_IMPORTJOB_DESCRIPTION, this.getImportJobDescription());
        result.put(PROPERTY_IMPORTCONFIG_NAME, this.getImportConfigName());
        return result;
    }

    /**
     * Setter for the ImportJob.
     *
     * @param newValue the ImportJob.
     */
    public void setImportJob(ImportJob newValue) {
        ImportJob oldValue = this.importJob;
        this.importJob = newValue;
        this.updateProperty(PROPERTY_IMPORTJOB_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_IMPORTJOB_NAME, ((oldValue != null) ? oldValue.getName() : ""),
                ((newValue != null) ? newValue.getName() : ""));
        this.updateProperty(PROPERTY_IMPORTJOB_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
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
     * Setter for the ImportConfig.
     *
     * @param newValue the ImportConfiguration.
     */
    public void setImportConfig(ImportConfiguration newValue) {
        ImportConfiguration oldValue = this.importConfig;
        this.importConfig = newValue;
        this.updateProperty(PROPERTY_IMPORTCONFIG_NAME, ((oldValue != null) ? oldValue.getName() : ""),
                ((newValue != null) ? newValue.getName() : ""));
    }

    /**
     * Getter for the ImportConfig.
     *
     * @return the ImportConfiguration.
     */
    public ImportConfiguration getImportConfig() {
        return this.importConfig;
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

    /**
     * Setter for the ImportConfigName.
     *
     * @param newName the String.
     */
    public void setImportConfigName(String newName) {
        if (((importConfig != null) && (importConfig.getName() == null))) {
            Name name = new Name();
            importConfig.setName(name);
        }
        String oldVal = importConfig.getName().getValue();
        importConfig.getName().setValue(newName);
        this.updateProperty(PROPERTY_IMPORTCONFIG_NAME, oldVal, newName);
        if (((!oldVal.equals(newName)) && importConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ImportConfigName.
     *
     * @return the String.
     */
    public String getImportConfigName() {
        if ((((importConfig == null) || (importConfig.getName() == null)) || (importConfig.getName().getValue() == null))) {
            return "";
        }
        return importConfig.getName().getValue();
    }
}
