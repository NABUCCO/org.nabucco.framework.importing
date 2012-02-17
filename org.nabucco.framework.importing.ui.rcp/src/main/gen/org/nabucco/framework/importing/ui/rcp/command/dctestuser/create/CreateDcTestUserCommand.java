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
package org.nabucco.framework.importing.ui.rcp.command.dctestuser.create;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * CreateDcTestUserCommand<p/>TODO remove later. TEMPORARY command for testing and documentation purposes for DynamicCode.<p/>
 *
 * @author Lasse Asbach, PRODYNA AG, 2010-09-27
 */
public class CreateDcTestUserCommand implements NabuccoCommand {

    private CreateDcTestUserHandler createDcTestUserHandler = NabuccoInjector
            .getInstance(CreateDcTestUserCommand.class).inject(CreateDcTestUserHandler.class);

    public static final String ID = "org.nabucco.framework.importing.ui.command.dctestuser.create.CreateDcTestUserCommand";

    /** Constructs a new CreateDcTestUserCommand instance. */
    public CreateDcTestUserCommand() {
        super();
    }

    @Override
    public void run() {
        createDcTestUserHandler.createDcTestUser();
    }

    @Override
    public String getId() {
        return CreateDcTestUserCommand.ID;
    }
}
