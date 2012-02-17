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
package org.nabucco.framework.importing.facade.component;

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for ImportingComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class ImportingComponentLocator extends ComponentLocatorSupport<ImportingComponent> implements
        ComponentLocator<ImportingComponent> {

    private static ImportingComponentLocator instance;

    /**
     * Constructs a new ImportingComponentLocator instance.
     *
     * @param component the Class<ImportingComponent>.
     * @param jndiName the String.
     */
    private ImportingComponentLocator(String jndiName, Class<ImportingComponent> component) {
        super(jndiName, component);
    }

    @Override
    public ImportingComponent getComponent() throws ConnectionException {
        ImportingComponent component = super.getComponent();
        if ((component instanceof ImportingComponentLocal)) {
            return new ImportingComponentLocalProxy(((ImportingComponentLocal) component));
        }
        return component;
    }

    /**
     * Getter for the Instance.
     *
     * @return the ImportingComponentLocator.
     */
    public static ImportingComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new ImportingComponentLocator(ImportingComponent.JNDI_NAME, ImportingComponent.class);
        }
        return instance;
    }
}
