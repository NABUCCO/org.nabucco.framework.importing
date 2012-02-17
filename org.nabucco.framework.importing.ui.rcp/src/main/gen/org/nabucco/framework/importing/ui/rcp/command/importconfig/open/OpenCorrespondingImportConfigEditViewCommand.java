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
package org.nabucco.framework.importing.ui.rcp.command.importconfig.open;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * OpenCorrespondingImportConfigEditViewCommand<p/>Command for opening the corresponding Import Configuration Edit View<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-23
 */
public class OpenCorrespondingImportConfigEditViewCommand implements NabuccoCommand {

    private OpenImportConfigEditViewHandler openImportConfigEditViewHandler = NabuccoInjector.getInstance(
            OpenCorrespondingImportConfigEditViewCommand.class).inject(OpenImportConfigEditViewHandler.class);

    public static final String ID = "org.nabucco.framework.importing.ui.command.importconfig.open.OpenCorrespondingImportConfigEditViewCommand";

    /** Constructs a new OpenCorrespondingImportConfigEditViewCommand instance. */
    public OpenCorrespondingImportConfigEditViewCommand() {
        super();
    }

    @Override
    public void run() {
        openImportConfigEditViewHandler.openImportConfigEditView();
    }

    @Override
    public String getId() {
        return OpenCorrespondingImportConfigEditViewCommand.ID;
    }
}
