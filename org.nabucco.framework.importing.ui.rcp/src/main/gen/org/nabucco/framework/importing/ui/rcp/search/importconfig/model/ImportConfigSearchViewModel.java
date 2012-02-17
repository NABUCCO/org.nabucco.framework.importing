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

import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportType;
import org.nabucco.framework.plugin.base.Activator;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchParameter;
import org.nabucco.framework.plugin.base.component.search.model.NabuccoComponentSearchViewModel;

/**
 * ImportConfigSearchViewModel<p/>search view for an import configuration<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-20
 */
public class ImportConfigSearchViewModel extends NabuccoComponentSearchViewModel<ImportConfiguration> implements
        NabuccoComponentSearchParameter {

    public static final String ID = "org.nabucco.framework.importing.ui.search.importconfig.ImportConfigSearchViewModel";

    private ImportConfiguration importConfig;

    public static final String PROPERTY_IMPORTCONFIG_NAME = "importConfigName";

    public static final String PROPERTY_IMPORTCONFIG_DESCRIPTION = "importConfigDescription";

    public static final String PROPERTY_IMPORTCONFIG_OWNER = "importConfigOwner";

    public static final String PROPERTY_IMPORTCONFIG_IMPORTTYPE = "importConfigImportType";

    public static String TITLE = (ID + "Title");

    /**
     * Constructs a new ImportConfigSearchViewModel instance.
     *
     * @param viewId the String.
     */
    public ImportConfigSearchViewModel(String viewId) {
        super();
        correspondingListView = viewId;
        this.importConfig = new ImportConfiguration();
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

    @Override
    public String getId() {
        return ImportConfigSearchViewModel.ID;
    }
}
