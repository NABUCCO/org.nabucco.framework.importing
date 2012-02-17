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
package org.nabucco.framework.importing.ui.rcp.command.importconfig.create;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * CreateImportConfigCommand<p/>Command for opening the Import Configuration Edit View by clicking into the context menu in navigator view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-25
 */
public class CreateImportConfigCommand implements NabuccoCommand {

    private CreateImportConfigHandler createImportConfigHandler = NabuccoInjector.getInstance(
            CreateImportConfigCommand.class).inject(CreateImportConfigHandler.class);

    public static final String ID = "org.nabucco.framework.importing.ui.command.importconfig.create.CreateImportConfigCommand";

    /** Constructs a new CreateImportConfigCommand instance. */
    public CreateImportConfigCommand() {
        super();
    }

    @Override
    public void run() {
        createImportConfigHandler.createImportConfig();
    }

    @Override
    public String getId() {
        return CreateImportConfigCommand.ID;
    }
}
