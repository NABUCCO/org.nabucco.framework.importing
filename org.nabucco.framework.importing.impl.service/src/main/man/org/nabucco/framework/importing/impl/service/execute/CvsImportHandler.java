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
package org.nabucco.framework.importing.impl.service.execute;

import org.nabucco.framework.base.facade.exception.service.ImportException;
import org.nabucco.framework.base.facade.message.context.ServiceMessageContext;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportJob;
import org.nabucco.framework.importing.facade.datatype.ImportType;
import org.nabucco.framework.importing.facade.message.execute.ExecuteImportRq;

/**
 * CvsImportHandler
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class CvsImportHandler implements ImportHandler {

    @SuppressWarnings("unused")
    private ServiceMessageContext context;

    /**
     * @param context
     */
    public CvsImportHandler(ServiceMessageContext context) {
        this.context = context;
    }

    @Override
    public ImportType getType() {
        return ImportType.CSV;
    }

    @Override
    public ImportJob executeImport(ImportConfiguration config, ExecuteImportRq rq)
            throws ImportException {
        // TODO Auto-generated method stub
        return null;
    }

}
