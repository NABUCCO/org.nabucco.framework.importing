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
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;
import org.nabucco.framework.importing.facade.datatype.ImportJob;

/**
 * ExecuteImportRs<p/>Response message of the executeImport service operation.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-23
 */
public class ExecuteImportRs extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m1,n;", "m0,n;" };

    public static final String EXECUTEDIMPORTJOBS = "executedImportJobs";

    public static final String IMPORTDATA = "importData";

    /** Contains information about the executed import. */
    private NabuccoList<ImportJob> executedImportJobs;

    /** Contains the imported data. */
    private NabuccoList<NabuccoDatatype> importData;

    /** Constructs a new ExecuteImportRs instance. */
    public ExecuteImportRs() {
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
        propertyMap.put(EXECUTEDIMPORTJOBS, PropertyDescriptorSupport.createCollection(EXECUTEDIMPORTJOBS,
                ImportJob.class, 0, PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(IMPORTDATA, PropertyDescriptorSupport.createCollection(IMPORTDATA, NabuccoDatatype.class, 1,
                PROPERTY_CONSTRAINTS[1], false, PropertyAssociationType.COMPONENT));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ExecuteImportRs.getPropertyDescriptor(EXECUTEDIMPORTJOBS),
                this.executedImportJobs));
        properties.add(super.createProperty(ExecuteImportRs.getPropertyDescriptor(IMPORTDATA), this.importData));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(EXECUTEDIMPORTJOBS) && (property.getType() == ImportJob.class))) {
            this.executedImportJobs = ((NabuccoList<ImportJob>) property.getInstance());
            return true;
        } else if ((property.getName().equals(IMPORTDATA) && (property.getType() == NabuccoDatatype.class))) {
            this.importData = ((NabuccoList<NabuccoDatatype>) property.getInstance());
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
        final ExecuteImportRs other = ((ExecuteImportRs) obj);
        if ((this.executedImportJobs == null)) {
            if ((other.executedImportJobs != null))
                return false;
        } else if ((!this.executedImportJobs.equals(other.executedImportJobs)))
            return false;
        if ((this.importData == null)) {
            if ((other.importData != null))
                return false;
        } else if ((!this.importData.equals(other.importData)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.executedImportJobs == null) ? 0 : this.executedImportJobs.hashCode()));
        result = ((PRIME * result) + ((this.importData == null) ? 0 : this.importData.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * Contains information about the executed import.
     *
     * @return the NabuccoList<ImportJob>.
     */
    public NabuccoList<ImportJob> getExecutedImportJobs() {
        if ((this.executedImportJobs == null)) {
            this.executedImportJobs = new NabuccoListImpl<ImportJob>(NabuccoCollectionState.INITIALIZED);
        }
        return this.executedImportJobs;
    }

    /**
     * Contains the imported data.
     *
     * @return the NabuccoList<NabuccoDatatype>.
     */
    public NabuccoList<NabuccoDatatype> getImportData() {
        if ((this.importData == null)) {
            this.importData = new NabuccoListImpl<NabuccoDatatype>(NabuccoCollectionState.INITIALIZED);
        }
        return this.importData;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ExecuteImportRs.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ExecuteImportRs.class).getAllProperties();
    }
}
