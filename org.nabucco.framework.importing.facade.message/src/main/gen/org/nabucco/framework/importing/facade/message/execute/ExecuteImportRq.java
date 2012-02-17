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
package org.nabucco.framework.importing.facade.message.execute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.importing.ImportContainer;
import org.nabucco.framework.base.facade.datatype.importing.ImportContext;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;

/**
 * ExecuteImportRq<p/>Message that contains the necessary information to start / execute imports.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class ExecuteImportRq extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "m1,1;", "m1,1;", "m1,n;" };

    public static final String NAME = "name";

    public static final String CONTAINER = "container";

    public static final String CONTEXT = "context";

    public static final String CONFIGURATIONS = "configurations";

    /** Name of the import job to start / execute. */
    private Name name;

    /** data container for the import. */
    private ImportContainer container;

    /** Context Information */
    private ImportContext context;

    /** Import configuration for the import. */
    private NabuccoList<ImportConfiguration> configurations;

    /** Constructs a new ExecuteImportRq instance. */
    public ExecuteImportRq() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 0, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(CONTAINER, PropertyDescriptorSupport.createDatatype(CONTAINER, ImportContainer.class, 1,
                PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(CONTEXT, PropertyDescriptorSupport.createDatatype(CONTEXT, ImportContext.class, 2,
                PROPERTY_CONSTRAINTS[2], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(CONFIGURATIONS, PropertyDescriptorSupport.createCollection(CONFIGURATIONS,
                ImportConfiguration.class, 3, PROPERTY_CONSTRAINTS[3], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ExecuteImportRq.getPropertyDescriptor(NAME), this.name));
        properties.add(super.createProperty(ExecuteImportRq.getPropertyDescriptor(CONTAINER), this.getContainer()));
        properties.add(super.createProperty(ExecuteImportRq.getPropertyDescriptor(CONTEXT), this.getContext()));
        properties
                .add(super.createProperty(ExecuteImportRq.getPropertyDescriptor(CONFIGURATIONS), this.configurations));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CONTAINER) && (property.getType() == ImportContainer.class))) {
            this.setContainer(((ImportContainer) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CONTEXT) && (property.getType() == ImportContext.class))) {
            this.setContext(((ImportContext) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CONFIGURATIONS) && (property.getType() == ImportConfiguration.class))) {
            this.configurations = ((NabuccoList<ImportConfiguration>) property.getInstance());
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
        final ExecuteImportRq other = ((ExecuteImportRq) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.container == null)) {
            if ((other.container != null))
                return false;
        } else if ((!this.container.equals(other.container)))
            return false;
        if ((this.context == null)) {
            if ((other.context != null))
                return false;
        } else if ((!this.context.equals(other.context)))
            return false;
        if ((this.configurations == null)) {
            if ((other.configurations != null))
                return false;
        } else if ((!this.configurations.equals(other.configurations)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.container == null) ? 0 : this.container.hashCode()));
        result = ((PRIME * result) + ((this.context == null) ? 0 : this.context.hashCode()));
        result = ((PRIME * result) + ((this.configurations == null) ? 0 : this.configurations.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Name of the import job to start / execute.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of the import job to start / execute.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * data container for the import.
     *
     * @return the ImportContainer.
     */
    public ImportContainer getContainer() {
        return this.container;
    }

    /**
     * data container for the import.
     *
     * @param container the ImportContainer.
     */
    public void setContainer(ImportContainer container) {
        this.container = container;
    }

    /**
     * Context Information
     *
     * @return the ImportContext.
     */
    public ImportContext getContext() {
        return this.context;
    }

    /**
     * Context Information
     *
     * @param context the ImportContext.
     */
    public void setContext(ImportContext context) {
        this.context = context;
    }

    /**
     * Import configuration for the import.
     *
     * @return the NabuccoList<ImportConfiguration>.
     */
    public NabuccoList<ImportConfiguration> getConfigurations() {
        if ((this.configurations == null)) {
            this.configurations = new NabuccoListImpl<ImportConfiguration>(NabuccoCollectionState.INITIALIZED);
        }
        return this.configurations;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ExecuteImportRq.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ExecuteImportRq.class).getAllProperties();
    }
}
