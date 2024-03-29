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
package org.nabucco.framework.importing.ui.rcp.command.dctestuser.save;

import org.nabucco.framework.base.facade.component.injector.NabuccoInjector;
import org.nabucco.framework.plugin.base.command.NabuccoCommand;

/**
 * SaveDcTestUserCommand<p/>Command for saving an instance of DcTestUser. TODO REMOVE later, TEMPORARY example.<p/>
 *
 * @author Lasse ASbach, PRODYNA AG, 2010-09-27
 */
public class SaveDcTestUserCommand implements NabuccoCommand {

    private SaveDcTestUserHandler saveDcTestUserHandler = NabuccoInjector.getInstance(SaveDcTestUserCommand.class)
            .inject(SaveDcTestUserHandler.class);

    public static final String ID = "org.nabucco.framework.importing.ui.command.dctestuser.save.SaveDcTestUserCommand";

    /** Constructs a new SaveDcTestUserCommand instance. */
    public SaveDcTestUserCommand() {
        super();
    }

    @Override
    public void run() {
        saveDcTestUserHandler.saveDcTestUser();
    }

    @Override
    public String getId() {
        return SaveDcTestUserCommand.ID;
    }
}
