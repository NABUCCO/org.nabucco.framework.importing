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
import org.nabucco.framework.base.facade.datatype.Description;
import org.nabucco.framework.base.facade.datatype.NabuccoDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.importing.facade.datatype.ImportType;

/**
 * ImportConfiguration<p/>Configures how an import will be executed internally in Importing.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-22
 */
public class ImportConfiguration extends NabuccoDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l3,12;u0,n;m1,1;",
            "l0,255;u0,n;m0,1;", "l0,255;u0,n;m1,1;", "m1,1;", "m0,n;" };

    public static final String NAME = "name";

    public static final String OWNER = "owner";

    public static final String DESCRIPTION = "description";

    public static final String SCRIPTNAME = "scriptName";

    public static final String IMPORTTYPE = "importType";

    public static final String DEPENDENCIES = "dependencies";

    /** Name of this configuration. */
    private Name name;

    /** Owner of this configuration. */
    private Owner owner;

    /** Description of this configuration. */
    private Description description;

    /** Name of the script (support.scripting) to execute. */
    private Name scriptName;

    /** Type of the file to import. */
    private ImportType importType;

    /** List of depenended ImportConfiguration's (thru ImportConfigurationLink). */
    private NabuccoList<ImportConfigurationLink> dependencies;

    /** Constructs a new ImportConfiguration instance. */
    public ImportConfiguration() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CloneObject.
     *
     * @param clone the ImportConfiguration.
     */
    protected void cloneObject(ImportConfiguration clone) {
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
        if ((this.getScriptName() != null)) {
            clone.setScriptName(this.getScriptName().cloneObject());
        }
        clone.setImportType(this.getImportType());
        if ((this.dependencies != null)) {
            clone.dependencies = this.dependencies.cloneCollection();
        }
    }

    /**
     * Getter for the DependenciesJPA.
     *
     * @return the List<ImportConfigurationLink>.
     */
    List<ImportConfigurationLink> getDependenciesJPA() {
        if ((this.dependencies == null)) {
            this.dependencies = new NabuccoListImpl<ImportConfigurationLink>(NabuccoCollectionState.EAGER);
        }
        return ((NabuccoListImpl<ImportConfigurationLink>) this.dependencies).getDelegate();
    }

    /**
     * Setter for the DependenciesJPA.
     *
     * @param dependencies the List<ImportConfigurationLink>.
     */
    void setDependenciesJPA(List<ImportConfigurationLink> dependencies) {
        if ((this.dependencies == null)) {
            this.dependencies = new NabuccoListImpl<ImportConfigurationLink>(NabuccoCollectionState.EAGER);
        }
        ((NabuccoListImpl<ImportConfigurationLink>) this.dependencies).setDelegate(dependencies);
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
        propertyMap.put(SCRIPTNAME,
                PropertyDescriptorSupport.createBasetype(SCRIPTNAME, Name.class, 6, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(IMPORTTYPE, PropertyDescriptorSupport.createEnumeration(IMPORTTYPE, ImportType.class, 7,
                PROPERTY_CONSTRAINTS[4], false));
        propertyMap.put(DEPENDENCIES, PropertyDescriptorSupport.createCollection(DEPENDENCIES,
                ImportConfigurationLink.class, 8, PROPERTY_CONSTRAINTS[5], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(ImportConfiguration.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(ImportConfiguration.getPropertyDescriptor(OWNER), this.owner, null));
        properties.add(super.createProperty(ImportConfiguration.getPropertyDescriptor(DESCRIPTION), this.description,
                null));
        properties.add(super.createProperty(ImportConfiguration.getPropertyDescriptor(SCRIPTNAME), this.scriptName,
                null));
        properties.add(super.createProperty(ImportConfiguration.getPropertyDescriptor(IMPORTTYPE),
                this.getImportType(), null));
        properties.add(super.createProperty(ImportConfiguration.getPropertyDescriptor(DEPENDENCIES), this.dependencies,
                null));
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
        } else if ((property.getName().equals(OWNER) && (property.getType() == Owner.class))) {
            this.setOwner(((Owner) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == Description.class))) {
            this.setDescription(((Description) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SCRIPTNAME) && (property.getType() == Name.class))) {
            this.setScriptName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(IMPORTTYPE) && (property.getType() == ImportType.class))) {
            this.setImportType(((ImportType) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DEPENDENCIES) && (property.getType() == ImportConfigurationLink.class))) {
            this.dependencies = ((NabuccoList<ImportConfigurationLink>) property.getInstance());
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
        final ImportConfiguration other = ((ImportConfiguration) obj);
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
        if ((this.scriptName == null)) {
            if ((other.scriptName != null))
                return false;
        } else if ((!this.scriptName.equals(other.scriptName)))
            return false;
        if ((this.importType == null)) {
            if ((other.importType != null))
                return false;
        } else if ((!this.importType.equals(other.importType)))
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
        result = ((PRIME * result) + ((this.scriptName == null) ? 0 : this.scriptName.hashCode()));
        result = ((PRIME * result) + ((this.importType == null) ? 0 : this.importType.hashCode()));
        return result;
    }

    @Override
    public ImportConfiguration cloneObject() {
        ImportConfiguration clone = new ImportConfiguration();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Name of this configuration.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Name of this configuration.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Name of this configuration.
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
     * Owner of this configuration.
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Owner of this configuration.
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * Owner of this configuration.
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
     * Description of this configuration.
     *
     * @return the Description.
     */
    public Description getDescription() {
        return this.description;
    }

    /**
     * Description of this configuration.
     *
     * @param description the Description.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Description of this configuration.
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
     * Name of the script (support.scripting) to execute.
     *
     * @return the Name.
     */
    public Name getScriptName() {
        return this.scriptName;
    }

    /**
     * Name of the script (support.scripting) to execute.
     *
     * @param scriptName the Name.
     */
    public void setScriptName(Name scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * Name of the script (support.scripting) to execute.
     *
     * @param scriptName the String.
     */
    public void setScriptName(String scriptName) {
        if ((this.scriptName == null)) {
            if ((scriptName == null)) {
                return;
            }
            this.scriptName = new Name();
        }
        this.scriptName.setValue(scriptName);
    }

    /**
     * Type of the file to import.
     *
     * @return the ImportType.
     */
    public ImportType getImportType() {
        return this.importType;
    }

    /**
     * Type of the file to import.
     *
     * @param importType the ImportType.
     */
    public void setImportType(ImportType importType) {
        this.importType = importType;
    }

    /**
     * Type of the file to import.
     *
     * @param importType the String.
     */
    public void setImportType(String importType) {
        if ((importType == null)) {
            this.importType = null;
        } else {
            this.importType = ImportType.valueOf(importType);
        }
    }

    /**
     * List of depenended ImportConfiguration's (thru ImportConfigurationLink).
     *
     * @return the NabuccoList<ImportConfigurationLink>.
     */
    public NabuccoList<ImportConfigurationLink> getDependencies() {
        if ((this.dependencies == null)) {
            this.dependencies = new NabuccoListImpl<ImportConfigurationLink>(NabuccoCollectionState.INITIALIZED);
        }
        return this.dependencies;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(ImportConfiguration.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(ImportConfiguration.class).getAllProperties();
    }
}
