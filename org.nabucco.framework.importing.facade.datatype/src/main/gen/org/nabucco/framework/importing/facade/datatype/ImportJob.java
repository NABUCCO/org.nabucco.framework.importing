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
import org.nabucco.framework.base.facade.datatype.DateTime;
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.log.LogTrace;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportStateType;

/**
 * ImportJob<p/>Contains information about an executed import-operation.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-22
 */
public class ImportJob extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final ImportStateType STATE_DEFAULT = ImportStateType.NEW;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l3,12;u0,n;m1,1;",
            "l0,255;u0,n;m0,1;", "l0,n;u0,n;m1,1;", "l0,n;u0,n;m1,1;", "m1,1;", "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;",
            "m1,1;", "m1,1;" };

    public static final String NAME = "name";

    public static final String OWNER = "owner";

    public static final String DESCRIPTION = "description";

    public static final String STARTTIME = "startTime";

    public static final String ENDTIME = "endTime";

    public static final String SOURCE = "source";

    public static final String LOGTRACE = "logTrace";

    public static final String ERRORTRACE = "errorTrace";

    public static final String STATE = "state";

    public static final String CONFIGURATION = "configuration";

    /** Name of the import job. */
    private Name name;

    /** Owner of the import job. */
    private Owner owner;

    /** Owner of the import job. */
    private Description description;

    /** The time that the import job was started. */
    private DateTime startTime;

    /** The time that the import job was finished. */
    private DateTime endTime;

    /** Url to the file(s) to import. */
    private NabuccoDatatype source;

    private Long sourceRefId;

    /** Log traces of the import job. */
    private LogTrace logTrace;

    /** Error traces of the import job. */
    private LogTrace errorTrace;

    /** State of the import job. */
    private ImportStateType state;

    /** Configuration of the import job. */
    private ImportConfiguration configuration;

    /** Constructs a new ImportJob instance. */
    public ImportJob() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        state = STATE_DEFAULT;
    }

    /**
     * CloneObject.
     *
     * @param clone the ImportJob.
     */
    protected void cloneObject(ImportJob clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getOwner() != null)) {
            clone.setOwner(this.getOwner().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        if ((this.getStartTime() != null)) {
            clone.setStartTime(this.getStartTime().cloneObject());
        }
        if ((this.getEndTime() != null)) {
            clone.setEndTime(this.getEndTime().cloneObject());
        }
        if ((this.getSource() != null)) {
            clone.setSource(this.getSource().cloneObject());
        }
        if ((this.getSourceRefId() != null)) {
            clone.setSourceRefId(this.getSourceRefId());
        }
        if ((this.getLogTrace() != null)) {
            clone.setLogTrace(this.getLogTrace().cloneObject());
        }
        if ((this.getErrorTrace() != null)) {
            clone.setErrorTrace(this.getErrorTrace().cloneObject());
        }
        clone.setState(this.getState());
        if ((this.getConfiguration() != null)) {
            clone.setConfiguration(this.getConfiguration().cloneObject());
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
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 3, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(OWNER,
                PropertyDescriptorSupport.createBasetype(OWNER, Owner.class, 4, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, Description.class, 5,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(STARTTIME,
                PropertyDescriptorSupport.createBasetype(STARTTIME, DateTime.class, 6, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(ENDTIME,
                PropertyDescriptorSupport.createBasetype(ENDTIME, DateTime.class, 7, PROPERTY_CONSTRAINTS[4], false));
        propertyMap.put(SOURCE, PropertyDescriptorSupport.createDatatype(SOURCE, NabuccoDatatype.class, 8,
                PROPERTY_CONSTRAINTS[5], false, PropertyAssociationType.COMPONENT));
        propertyMap.put(LOGTRACE,
                PropertyDescriptorSupport.createBasetype(LOGTRACE, LogTrace.class, 9, PROPERTY_CONSTRAINTS[6], false));
        propertyMap.put(ERRORTRACE, PropertyDescriptorSupport.createBasetype(ERRORTRACE, LogTrace.class, 10,
                PROPERTY_CONSTRAINTS[7], false));
        propertyMap.put(STATE, PropertyDescriptorSupport.createEnumeration(STATE, ImportStateType.class, 11,
                PROPERTY_CONSTRAINTS[8], false));
        propertyMap.put(CONFIGURATION, PropertyDescriptorSupport.createDatatype(CONFIGURATION,
                ImportConfiguration.class, 12, PROPERTY_CONSTRAINTS[9], false, PropertyAssociationType.AGGREGATION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(OWNER), this.owner, null));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(DESCRIPTION), this.description, null));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(STARTTIME), this.startTime, null));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(ENDTIME), this.endTime, null));
        properties
                .add(super.createProperty(ImportJob.getPropertyDescriptor(SOURCE), this.getSource(), this.sourceRefId));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(LOGTRACE), this.logTrace, null));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(ERRORTRACE), this.errorTrace, null));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(STATE), this.getState(), null));
        properties.add(super.createProperty(ImportJob.getPropertyDescriptor(CONFIGURATION), this.getConfiguration(),
                null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OWNER) && (property.getType() == Owner.class))) {
            this.setOwner(((Owner) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(STARTTIME) && (property.getType() == DateTime.class))) {
            this.setStartTime(((DateTime) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ENDTIME) && (property.getType() == DateTime.class))) {
            this.setEndTime(((DateTime) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SOURCE) && (property.getType() == NabuccoDatatype.class))) {
            this.setSource(((NabuccoDatatype) property.getInstance()));
            return true;
        } else if ((property.getName().equals(LOGTRACE) && (property.getType() == LogTrace.class))) {
            this.setLogTrace(((LogTrace) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ERRORTRACE) && (property.getType() == LogTrace.class))) {
            this.setErrorTrace(((LogTrace) property.getInstance()));
            return true;
        } else if ((property.getName().equals(STATE) && (property.getType() == ImportStateType.class))) {
            this.setState(((ImportStateType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CONFIGURATION) && (property.getType() == ImportConfiguration.class))) {
            this.setConfiguration(((ImportConfiguration) property.getInstance()));
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
        final ImportJob other = ((ImportJob) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.owner == null)) {
            if ((other.owner != null))
                return false;
        } else if ((!this.owner.equals(other.owner)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.startTime == null)) {
            if ((other.startTime != null))
                return false;
        } else if ((!this.startTime.equals(other.startTime)))
            return false;
        if ((this.endTime == null)) {
            if ((other.endTime != null))
                return false;
        } else if ((!this.endTime.equals(other.endTime)))
            return false;
        if ((this.source == null)) {
            if ((other.source != null))
                return false;
        } else if ((!this.source.equals(other.source)))
            return false;
        if ((this.sourceRefId == null)) {
            if ((other.sourceRefId != null))
                return false;
        } else if ((!this.sourceRefId.equals(other.sourceRefId)))
            return false;
        if ((this.logTrace == null)) {
            if ((other.logTrace != null))
                return false;
        } else if ((!this.logTrace.equals(other.logTrace)))
            return false;
        if ((this.errorTrace == null)) {
            if ((other.errorTrace != null))
                return false;
        } else if ((!this.errorTrace.equals(other.errorTrace)))
            return false;
        if ((this.state == null)) {
            if ((other.state != null))
                return false;
        } else if ((!this.state.equals(other.state)))
            return false;
        if ((this.configuration == null)) {
            if ((other.configuration != null))
                return false;
        } else if ((!this.configuration.equals(other.configuration)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.owner == null) ? 0 : this.owner.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.startTime == null) ? 0 : this.startTime.hashCode()));
        result = ((PRIME * result) + ((this.endTime == null) ? 0 : this.endTime.hashCode()));
        result = ((PRIME * result) + ((this.source == null) ? 0 : this.source.hashCode()));
        result = ((PRIME * result) + ((this.sourceRefId == null) ? 0 : this.sourceRefId.hashCode()));
        result = ((PRIME * result) + ((this.logTrace == null) ? 0 : this.logTrace.hashCode()));
        result = ((PRIME * result) + ((this.errorTrace == null) ? 0 : this.errorTrace.hashCode()));
        result = ((PRIME * result) + ((this.state == null) ? 0 : this.state.hashCode()));
        result = ((PRIME * result) + ((this.configuration == null) ? 0 : this.configuration.hashCode()));
        return result;
    }

    @Override
    public ImportJob cloneObject() {
        ImportJob clone = new ImportJob();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Name of the import job.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of the import job.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Name of the import job.
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * Owner of the import job.
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Owner of the import job.
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Owner of the import job.
     *
     * @param owner the String.
     */
    public void setOwner(String owner) {
        if ((this.owner == null)) {
            if ((owner == null)) {
                return;
            }
            this.owner = new Owner();
        }
        this.owner.setValue(owner);
    }

    /**
     * Owner of the import job.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Owner of the import job.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Owner of the import job.
     *
     * @param description the String.
     */
    public void setDescription(String description) {
        if ((this.description == null)) {
            if ((description == null)) {
                return;
            }
            this.description = new Description();
        }
        this.description.setValue(description);
    }

    /**
     * The time that the import job was started.
     *
     * @return the DateTime.
     */
    public DateTime getStartTime() {
        return this.startTime;
    }

    /**
     * The time that the import job was started.
     *
     * @param startTime the DateTime.
     */
    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * The time that the import job was started.
     *
     * @param startTime the Long.
     */
    public void setStartTime(Long startTime) {
        if ((this.startTime == null)) {
            if ((startTime == null)) {
                return;
            }
            this.startTime = new DateTime();
        }
        this.startTime.setValue(startTime);
    }

    /**
     * The time that the import job was finished.
     *
     * @return the DateTime.
     */
    public DateTime getEndTime() {
        return this.endTime;
    }

    /**
     * The time that the import job was finished.
     *
     * @param endTime the DateTime.
     */
    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * The time that the import job was finished.
     *
     * @param endTime the Long.
     */
    public void setEndTime(Long endTime) {
        if ((this.endTime == null)) {
            if ((endTime == null)) {
                return;
            }
            this.endTime = new DateTime();
        }
        this.endTime.setValue(endTime);
    }

    /**
     * Url to the file(s) to import.
     *
     * @param source the NabuccoDatatype.
     */
    public void setSource(NabuccoDatatype source) {
        this.source = source;
        if ((source != null)) {
            this.setSourceRefId(source.getId());
        } else {
            this.setSourceRefId(null);
        }
    }

    /**
     * Url to the file(s) to import.
     *
     * @return the NabuccoDatatype.
     */
    public NabuccoDatatype getSource() {
        return this.source;
    }

    /**
     * Getter for the SourceRefId.
     *
     * @return the Long.
     */
    public Long getSourceRefId() {
        return this.sourceRefId;
    }

    /**
     * Setter for the SourceRefId.
     *
     * @param sourceRefId the Long.
     */
    public void setSourceRefId(Long sourceRefId) {
        this.sourceRefId = sourceRefId;
    }

    /**
     * Log traces of the import job.
     *
     * @return the LogTrace.
     */
    public LogTrace getLogTrace() {
        return this.logTrace;
    }

    /**
     * Log traces of the import job.
     *
     * @param logTrace the LogTrace.
     */
    public void setLogTrace(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     * Log traces of the import job.
     *
     * @param logTrace the String.
     */
    public void setLogTrace(String logTrace) {
        if ((this.logTrace == null)) {
            if ((logTrace == null)) {
                return;
            }
            this.logTrace = new LogTrace();
        }
        this.logTrace.setValue(logTrace);
    }

    /**
     * Error traces of the import job.
     *
     * @return the LogTrace.
     */
    public LogTrace getErrorTrace() {
        return this.errorTrace;
    }

    /**
     * Error traces of the import job.
     *
     * @param errorTrace the LogTrace.
     */
    public void setErrorTrace(LogTrace errorTrace) {
        this.errorTrace = errorTrace;
    }

    /**
     * Error traces of the import job.
     *
     * @param errorTrace the String.
     */
    public void setErrorTrace(String errorTrace) {
        if ((this.errorTrace == null)) {
            if ((errorTrace == null)) {
                return;
            }
            this.errorTrace = new LogTrace();
        }
        this.errorTrace.setValue(errorTrace);
    }

    /**
     * State of the import job.
     *
     * @return the ImportStateType.
     */
    public ImportStateType getState() {
        return this.state;
    }

    /**
     * State of the import job.
     *
     * @param state the ImportStateType.
     */
    public void setState(ImportStateType state) {
        this.state = state;
    }

    /**
     * State of the import job.
     *
     * @param state the String.
     */
    public void setState(String state) {
        if ((state == null)) {
            this.state = null;
        } else {
            this.state = ImportStateType.valueOf(state);
        }
    }

    /**
     * Configuration of the import job.
     *
     * @param configuration the ImportConfiguration.
     */
    public void setConfiguration(ImportConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Configuration of the import job.
     *
     * @return the ImportConfiguration.
     */
    public ImportConfiguration getConfiguration() {
        return this.configuration;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ImportJob.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ImportJob.class).getAllProperties();
    }
}
