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
package org.nabucco.framework.importing.impl.service.maintain;

import java.util.Arrays;

import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.impl.service.ServiceHandler;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;

/**
 * MaintainImportConfigurationListServiceHandlerImpl
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class MaintainImportConfigurationListServiceHandlerImpl extends
        MaintainImportConfigurationListServiceHandler implements ServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected ImportConfigurationListMsg maintainImportConfigurationList(
            ImportConfigurationListMsg msg) throws MaintainException {

        NabuccoList<ImportConfiguration> configs = msg.getImportConfigurationList();

        ImportConfiguration[] result = ImportElementMaintainer.maintain(getPersistenceManager(),
                configs.toArray(new ImportConfiguration[configs.size()]));

        ImportConfigurationListMsg response = new ImportConfigurationListMsg();
        response.getImportConfigurationList().addAll(Arrays.asList(result));

        return response;
    }
}
