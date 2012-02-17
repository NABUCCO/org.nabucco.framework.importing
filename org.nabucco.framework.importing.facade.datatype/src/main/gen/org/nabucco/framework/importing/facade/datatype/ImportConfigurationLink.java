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
package org.nabucco.framework.importing.facade.datatype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;

/**
 * ImportConfigurationLink<p/>Links ImportConfigurations together<p/>
 *
 * @version 1.0
 * @author Silas Schwarz, PRODYNA AG, 2011-02-02
 */
public class ImportConfigurationLink extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "l0,n;u0,n;m1,1;" };

    public static final String CONFIG = "config";

    public static final String PRIORITY = "priority";

    /** Link to a ExportConfiguration */
    private ImportConfiguration config;

    /** used to maintain order; */
    private Number priority;

    /** Constructs a new ImportConfigurationLink instance. */
    public ImportConfigurationLink() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the ImportConfigurationLink.
     */
    protected void cloneObject(ImportConfigurationLink clone) {
        super.cloneObject(clone);
        if ((this.getConfig() != null)) {
            clone.setConfig(this.getConfig().cloneObject());
        }
        if ((this.getPriority() != null)) {
            clone.setPriority(this.getPriority().cloneObject());
        }
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(NabuccoDatatype.class).getPropertyMap());
        propertyMap.put(CONFIG, PropertyDescriptorSupport.createDatatype(CONFIG, ImportConfiguration.class, 3,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(PRIORITY,
                PropertyDescriptorSupport.createBasetype(PRIORITY, Number.class, 4, PROPERTY_CONSTRAINTS[1], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ImportConfigurationLink.getPropertyDescriptor(CONFIG), this.getConfig(),
                null));
        properties.add(super.createProperty(ImportConfigurationLink.getPropertyDescriptor(PRIORITY), this.priority,
                null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(CONFIG) && (property.getType() == ImportConfiguration.class))) {
            this.setConfig(((ImportConfiguration) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PRIORITY) && (property.getType() == Number.class))) {
            this.setPriority(((Number) property.getInstance()));
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final ImportConfigurationLink other = ((ImportConfigurationLink) obj);
        if ((this.config == null)) {
            if ((other.config != null))
                return false;
        } else if ((!this.config.equals(other.config)))
            return false;
        if ((this.priority == null)) {
            if ((other.priority != null))
                return false;
        } else if ((!this.priority.equals(other.priority)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.config == null) ? 0 : this.config.hashCode()));
        result = ((PRIME * result) + ((this.priority == null) ? 0 : this.priority.hashCode()));
        return result;
    }

    @Override
    public ImportConfigurationLink cloneObject() {
        ImportConfigurationLink clone = new ImportConfigurationLink();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Link to a ExportConfiguration
     *
     * @param config the ImportConfiguration.
     */
    public void setConfig(ImportConfiguration config) {
        this.config = config;
    }

    /**
     * Link to a ExportConfiguration
     *
     * @return the ImportConfiguration.
     */
    public ImportConfiguration getConfig() {
        return this.config;
    }

    /**
     * used to maintain order;
     *
     * @return the Number.
     */
    public Number getPriority() {
        return this.priority;
    }

    /**
     * used to maintain order;
     *
     * @param priority the Number.
     */
    public void setPriority(Number priority) {
        this.priority = priority;
    }

    /**
     * used to maintain order;
     *
     * @param priority the Integer.
     */
    public void setPriority(Integer priority) {
        if ((this.priority == null)) {
            if ((priority == null)) {
                return;
            }
            this.priority = new Number();
        }
        this.priority.setValue(priority);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ImportConfigurationLink.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ImportConfigurationLink.class).getAllProperties();
    }
}
