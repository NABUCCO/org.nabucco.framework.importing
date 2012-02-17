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
 * OpenCorrespondingImportConfigEditViewFromBrowserCommand<p/>Command for opening the ImportConfigEditView by double-clicking an item in the browser view<p/>
 *
 * @author Christian Nicolaus, PRODYNA AG, 2010-08-23
 */
public class OpenCorrespondingImportConfigEditViewFromBrowserCommand implements NabuccoCommand {

    private OpenImportConfigEditViewFromBrowserHandler openImportConfigEditViewFromBrowserHandler = NabuccoInjector
            .getInstance(OpenCorrespondingImportConfigEditViewFromBrowserCommand.class).inject(
                    OpenImportConfigEditViewFromBrowserHandler.class);

    public static final String ID = "org.nabucco.framework.importing.ui.command.importconfig.open.OpenCorrespondingImportConfigEditViewFromBrowserCommand";

    /** Constructs a new OpenCorrespondingImportConfigEditViewFromBrowserCommand instance. */
    public OpenCorrespondingImportConfigEditViewFromBrowserCommand() {
        super();
    }

    @Override
    public void run() {
        openImportConfigEditViewFromBrowserHandler.openImportConfigEditViewFromBrowser();
    }

    @Override
    public String getId() {
        return OpenCorrespondingImportConfigEditViewFromBrowserCommand.ID;
    }
}
