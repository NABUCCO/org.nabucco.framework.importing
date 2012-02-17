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
package org.nabucco.framework.importing.ui.rcp.edit.importconfig.model;

import java.io.Serializable;
import java.util.Map;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportType;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.edit.model.EditViewModel;
import org.nabucco.framework.plugin.base.logging.Loggable;

/**
 * ImportConfigEditViewModel<p/>the import configuraton edit view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-19
 */
public class ImportConfigEditViewModel extends EditViewModel implements Loggable {

    private ImportConfiguration importConfig;

    public static final String PROPERTY_IMPORTCONFIG_NAME = "importConfigName";

    public static final String PROPERTY_IMPORTCONFIG_DESCRIPTION = "importConfigDescription";

    public static final String PROPERTY_IMPORTCONFIG_OWNER = "importConfigOwner";

    public static final String PROPERTY_IMPORTCONFIG_SCRIPTNAME = "importConfigScriptName";

    public static final String PROPERTY_IMPORTCONFIG_IMPORTTYPE = "importConfigImportType";

    /** Constructs a new ImportConfigEditViewModel instance. */
    public ImportConfigEditViewModel() {
        super();
    }

    /**
     * Getter for the ID.
     *
     * @return the String.
     */
    public String getID() {
        return "org.nabucco.framework.importing.ui.rcp.edit.importconfig.model.ImportConfigEditViewModel";
    }

    /**
     * Getter for the Values.
     *
     * @return the Map<String, Serializable>.
     */
    public Map<String, Serializable> getValues() {
        Map<String, Serializable> result = super.getValues();
        result.put(PROPERTY_IMPORTCONFIG_DESCRIPTION, this.getImportConfigDescription());
        result.put(PROPERTY_IMPORTCONFIG_OWNER, this.getImportConfigOwner());
        result.put(PROPERTY_IMPORTCONFIG_IMPORTTYPE, this.getImportConfigImportType());
        result.put(PROPERTY_IMPORTCONFIG_SCRIPTNAME, this.getImportConfigScriptName());
        result.put(PROPERTY_IMPORTCONFIG_NAME, this.getImportConfigName());
        return result;
    }

    /**
     * Setter for the ImportConfig.
     *
     * @param newValue the ImportConfiguration.
     */
    public void setImportConfig(ImportConfiguration newValue) {
        ImportConfiguration oldValue = this.importConfig;
        this.importConfig = newValue;
        this.updateProperty(PROPERTY_IMPORTCONFIG_IMPORTTYPE, ((oldValue != null) ? oldValue.getImportType() : ""),
                ((newValue != null) ? newValue.getImportType() : ""));
        this.updateProperty(PROPERTY_IMPORTCONFIG_SCRIPTNAME, ((oldValue != null) ? oldValue.getScriptName() : ""),
                ((newValue != null) ? newValue.getScriptName() : ""));
        this.updateProperty(PROPERTY_IMPORTCONFIG_DESCRIPTION, ((oldValue != null) ? oldValue.getDescription() : ""),
                ((newValue != null) ? newValue.getDescription() : ""));
        this.updateProperty(PROPERTY_IMPORTCONFIG_NAME, ((oldValue != null) ? oldValue.getName() : ""),
                ((newValue != null) ? newValue.getName() : ""));
        this.updateProperty(PROPERTY_IMPORTCONFIG_OWNER, ((oldValue != null) ? oldValue.getOwner() : ""),
                ((newValue != null) ? newValue.getOwner() : ""));
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

    /**
     * Setter for the ImportConfigDescription.
     *
     * @param newDescription the String.
     */
    public void setImportConfigDescription(String newDescription) {
        if (((importConfig != null) && (importConfig.getDescription() == null))) {
            Description description = new Description();
            importConfig.setDescription(description);
        }
        String oldVal = importConfig.getDescription().getValue();
        importConfig.getDescription().setValue(newDescription);
        this.updateProperty(PROPERTY_IMPORTCONFIG_DESCRIPTION, oldVal, newDescription);
        if (((!oldVal.equals(newDescription)) && importConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ImportConfigDescription.
     *
     * @return the String.
     */
    public String getImportConfigDescription() {
        if ((((importConfig == null) || (importConfig.getDescription() == null)) || (importConfig.getDescription()
                .getValue() == null))) {
            return "";
        }
        return importConfig.getDescription().getValue();
    }

    /**
     * Setter for the ImportConfigOwner.
     *
     * @param newOwner the String.
     */
    public void setImportConfigOwner(String newOwner) {
        if (((importConfig != null) && (importConfig.getOwner() == null))) {
            Owner owner = new Owner();
            importConfig.setOwner(owner);
        }
        String oldVal = importConfig.getOwner().getValue();
        importConfig.getOwner().setValue(newOwner);
        this.updateProperty(PROPERTY_IMPORTCONFIG_OWNER, oldVal, newOwner);
        if (((!oldVal.equals(newOwner)) && importConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ImportConfigOwner.
     *
     * @return the String.
     */
    public String getImportConfigOwner() {
        if ((((importConfig == null) || (importConfig.getOwner() == null)) || (importConfig.getOwner().getValue() == null))) {
            return "";
        }
        return importConfig.getOwner().getValue();
    }

    /**
     * Setter for the ImportConfigScriptName.
     *
     * @param newScriptName the String.
     */
    public void setImportConfigScriptName(String newScriptName) {
        if (((importConfig != null) && (importConfig.getScriptName() == null))) {
            Name scriptName = new Name();
            importConfig.setScriptName(scriptName);
        }
        String oldVal = importConfig.getScriptName().getValue();
        importConfig.getScriptName().setValue(newScriptName);
        this.updateProperty(PROPERTY_IMPORTCONFIG_SCRIPTNAME, oldVal, newScriptName);
        if (((!oldVal.equals(newScriptName)) && importConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }

    /**
     * Getter for the ImportConfigScriptName.
     *
     * @return the String.
     */
    public String getImportConfigScriptName() {
        if ((((importConfig == null) || (importConfig.getScriptName() == null)) || (importConfig.getScriptName()
                .getValue() == null))) {
            return "";
        }
        return importConfig.getScriptName().getValue();
    }

    /**
     * Getter for the ImportConfigImportType.
     *
     * @return the String.
     */
    public String getImportConfigImportType() {
        if (((importConfig == null) || (importConfig.getImportType() == null))) {
            return "";
        }
        return importConfig.getImportType().name();
    }

    /**
     * Setter for the ImportConfigImportType.
     *
     * @param newImportType the String.
     */
    public void setImportConfigImportType(final String newImportType) {
        String oldVal = "";
        if ((this.importConfig.getImportType() != null)) {
            oldVal = this.importConfig.getImportType().name();
        }
        this.importConfig.setImportType(ImportType.valueOf(newImportType));
        this.updateProperty(PROPERTY_IMPORTCONFIG_IMPORTTYPE, oldVal, newImportType);
        if (((!oldVal.equals(newImportType)) && importConfig.getDatatypeState().equals(DatatypeState.PERSISTENT))) {
            importConfig.setDatatypeState(DatatypeState.MODIFIED);
        }
    }
}
