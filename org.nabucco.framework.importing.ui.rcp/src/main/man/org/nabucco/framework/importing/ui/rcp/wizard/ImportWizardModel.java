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
package org.nabucco.framework.importing.ui.rcp.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;

/**
 * ImportWizardModel
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ImportWizardModel {

    static final String PROPERTY_DESCRIPTION = "description";

    static final String PROPERTY_DESTINATION = "destination";

    static final String PROPERTY_EXPORTALL = "importAll";

    static final String PROPERTY_EXPORTSEPERATE = "importSeperate";

    static final String PROPERTY_CONFIGURATIONS = "configurations";

    /** Adapter for property changes. */
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private String description = "";

    private String destination = "";

    private boolean importAll = true;

    private boolean importSeperate = false;

    private List<String> destinationNames = new ArrayList<String>();

    private Set<ImportConfiguration> configurations = new HashSet<ImportConfiguration>();

    /**
     * Adds a property change listener to the model.
     * 
     * @param propertyName
     *            the property to listen on
     * @param listener
     *            the property change listener to add
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Removes a property change listener from the model.
     * 
     * @param listener
     *            the property change listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Getter for the description.
     * 
     * @return Returns the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for the description.
     * 
     * @param newValue
     *            The description to set.
     */
    public void setDescription(String newValue) {
        String oldValue = this.description;
        this.description = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_DESCRIPTION, oldValue, newValue);
    }

    /**
     * Getter for the destination.
     * 
     * @return Returns the destination.
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Setter for the destination.
     * 
     * @param newValue
     *            The destination to set.
     */
    public void setDestination(String newValue) {
        String oldValue = this.destination;
        this.destination = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_DESTINATION, oldValue, newValue);
    }

    /**
     * Getter for the importAll.
     * 
     * @return Returns the importAll.
     */
    public boolean isImportAll() {
        return this.importAll;
    }

    /**
     * Setter for the importAll.
     * 
     * @param newValue
     *            The importAll to set.
     */
    public void setImportAll(boolean newValue) {
        boolean oldValue = this.importAll;
        this.importAll = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_EXPORTALL, oldValue, newValue);

        this.setImportSeperate(!newValue);
    }

    /**
     * Getter for the importSeperate.
     * 
     * @return Returns the importSeperate.
     */
    public boolean isImportSeperate() {
        return this.importSeperate;
    }

    /**
     * Setter for the importSeperate.
     * 
     * @param importSeperate
     *            The importSeperate to set.
     */
    public void setImportSeperate(boolean newValue) {
        boolean oldValue = this.importSeperate;
        this.importSeperate = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_EXPORTSEPERATE, oldValue, newValue);
    }

    /**
     * Getter for the configurationList.
     * 
     * @return Returns the configurationList.
     */
    public Set<ImportConfiguration> getConfigurations() {
        return this.configurations;
    }

    /**
     * Setter for the configurationList.
     * 
     * @param newValue
     *            The configurationList to set.
     */
    public void setConfigurations(Set<ImportConfiguration> newValue) {
        Set<ImportConfiguration> oldValue = this.configurations;
        this.configurations = newValue;

        this.propertyChangeSupport.firePropertyChange(PROPERTY_CONFIGURATIONS, oldValue, newValue);
    }

    /**
     * Getter for the destinationNames.
     * 
     * @return Returns the destinationNames.
     */
    public List<String> getDestinationNames() {
        return this.destinationNames;
    }

}
