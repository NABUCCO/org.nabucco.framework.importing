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
package org.nabucco.framework.importing.facade.message.maintain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Number;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.LinkOperationType;

/**
 * ImportConfigurationLinkRq<p/>Response message of the executeExport service operation.<p/>
 *
 * @version 1.0
 * @author Silas Schwarz, PRODYNA AG, 2010-02-08
 */
public class ImportConfigurationLinkRq extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final LinkOperationType OPERATION_DEFAULT = LinkOperationType.ADD;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,1;", "m1,1;", "l0,n;u0,n;m1,1;", "m1,1;" };

    public static final String SOURCE = "source";

    public static final String TARGET = "target";

    public static final String PRIORITY = "priority";

    public static final String OPERATION = "operation";

    /** the source ExportConfiguration */
    private ImportConfiguration source;

    /** the target ExportConfiguration to bind to the source */
    private ImportConfiguration target;

    /** the priority */
    private Number priority;

    /** add(/replace) or remove link */
    private LinkOperationType operation;

    /** Constructs a new ImportConfigurationLinkRq instance. */
    public ImportConfigurationLinkRq() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        operation = OPERATION_DEFAULT;
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(SOURCE, PropertyDescriptorSupport.createDatatype(SOURCE, ImportConfiguration.class, 0,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(TARGET, PropertyDescriptorSupport.createDatatype(TARGET, ImportConfiguration.class, 1,
                PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(PRIORITY,
                PropertyDescriptorSupport.createBasetype(PRIORITY, Number.class, 2, PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(OPERATION, PropertyDescriptorSupport.createEnumeration(OPERATION, LinkOperationType.class, 3,
                PROPERTY_CONSTRAINTS[3], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ImportConfigurationLinkRq.getPropertyDescriptor(SOURCE), this.getSource()));
        properties.add(super.createProperty(ImportConfigurationLinkRq.getPropertyDescriptor(TARGET), this.getTarget()));
        properties.add(super.createProperty(ImportConfigurationLinkRq.getPropertyDescriptor(PRIORITY), this.priority));
        properties.add(super.createProperty(ImportConfigurationLinkRq.getPropertyDescriptor(OPERATION),
                this.getOperation()));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(SOURCE) && (property.getType() == ImportConfiguration.class))) {
            this.setSource(((ImportConfiguration) property.getInstance()));
            return true;
        } else if ((property.getName().equals(TARGET) && (property.getType() == ImportConfiguration.class))) {
            this.setTarget(((ImportConfiguration) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PRIORITY) && (property.getType() == Number.class))) {
            this.setPriority(((Number) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OPERATION) && (property.getType() == LinkOperationType.class))) {
            this.setOperation(((LinkOperationType) property.getInstance()));
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
        final ImportConfigurationLinkRq other = ((ImportConfigurationLinkRq) obj);
        if ((this.source == null)) {
            if ((other.source != null))
                return false;
        } else if ((!this.source.equals(other.source)))
            return false;
        if ((this.target == null)) {
            if ((other.target != null))
                return false;
        } else if ((!this.target.equals(other.target)))
            return false;
        if ((this.priority == null)) {
            if ((other.priority != null))
                return false;
        } else if ((!this.priority.equals(other.priority)))
            return false;
        if ((this.operation == null)) {
            if ((other.operation != null))
                return false;
        } else if ((!this.operation.equals(other.operation)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.source == null) ? 0 : this.source.hashCode()));
        result = ((PRIME * result) + ((this.target == null) ? 0 : this.target.hashCode()));
        result = ((PRIME * result) + ((this.priority == null) ? 0 : this.priority.hashCode()));
        result = ((PRIME * result) + ((this.operation == null) ? 0 : this.operation.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * the source ExportConfiguration
     *
     * @return the ImportConfiguration.
     */
    public ImportConfiguration getSource() {
        return this.source;
    }

    /**
     * the source ExportConfiguration
     *
     * @param source the ImportConfiguration.
     */
    public void setSource(ImportConfiguration source) {
        this.source = source;
    }

    /**
     * the target ExportConfiguration to bind to the source
     *
     * @return the ImportConfiguration.
     */
    public ImportConfiguration getTarget() {
        return this.target;
    }

    /**
     * the target ExportConfiguration to bind to the source
     *
     * @param target the ImportConfiguration.
     */
    public void setTarget(ImportConfiguration target) {
        this.target = target;
    }

    /**
     * the priority
     *
     * @return the Number.
     */
    public Number getPriority() {
        return this.priority;
    }

    /**
     * the priority
     *
     * @param priority the Number.
     */
    public void setPriority(Number priority) {
        this.priority = priority;
    }

    /**
     * add(/replace) or remove link
     *
     * @return the LinkOperationType.
     */
    public LinkOperationType getOperation() {
        return this.operation;
    }

    /**
     * add(/replace) or remove link
     *
     * @param operation the LinkOperationType.
     */
    public void setOperation(LinkOperationType operation) {
        this.operation = operation;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ImportConfigurationLinkRq.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ImportConfigurationLinkRq.class).getAllProperties();
    }
}
