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
package org.nabucco.framework.importing.impl.component;

/**
 * ImportingComponentJndiNames<p/>Component for XML, CSV, etc. imports.. The component was named 'Importing' as opposed to 'Import', because 'import' is a reserved java keyword.<p/>
 *
 * @version 1.0
 * @author Lasse Asbach, PRODYNA AG, 2010-07-22
 */
public interface ImportingComponentJndiNames {

    final String COMPONENT_RELATION_SERVICE_LOCAL = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.component.ComponentRelationService/local";

    final String COMPONENT_RELATION_SERVICE_REMOTE = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.component.ComponentRelationService/remote";

    final String QUERY_FILTER_SERVICE_LOCAL = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.component.QueryFilterService/local";

    final String QUERY_FILTER_SERVICE_REMOTE = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.component.QueryFilterService/remote";

    final String PRODUCE_IMPORTING_LOCAL = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.produce.ProduceImporting/local";

    final String PRODUCE_IMPORTING_REMOTE = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.produce.ProduceImporting/remote";

    final String RESOLVE_IMPORTING_LOCAL = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.resolve.ResolveImporting/local";

    final String RESOLVE_IMPORTING_REMOTE = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.resolve.ResolveImporting/remote";

    final String EXECUTE_IMPORTING_LOCAL = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.execute.ExecuteImporting/local";

    final String EXECUTE_IMPORTING_REMOTE = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.execute.ExecuteImporting/remote";

    final String MAINTAIN_IMPORTING_LOCAL = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.maintain.MaintainImporting/local";

    final String MAINTAIN_IMPORTING_REMOTE = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.maintain.MaintainImporting/remote";

    final String SEARCH_IMPORTING_LOCAL = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.search.SearchImporting/local";

    final String SEARCH_IMPORTING_REMOTE = "nabucco/org.nabucco.framework.importing/org.nabucco.framework.importing.facade.service.search.SearchImporting/remote";
}
