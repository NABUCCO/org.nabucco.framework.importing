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
package org.nabucco.framework.importing.ui.rcp.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.nabucco.framework.base.facade.datatype.utils.I18N;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.importing.facade.datatype.ImportConfiguration;
import org.nabucco.framework.importing.facade.datatype.ImportType;
import org.nabucco.framework.importing.facade.message.ImportConfigurationListMsg;
import org.nabucco.framework.importing.facade.message.search.ImportConfigurationSearchRq;
import org.nabucco.framework.importing.ui.rcp.communication.ImportingComponentServiceDelegateFactory;
import org.nabucco.framework.importing.ui.rcp.communication.search.SearchImportingDelegate;
import org.nabucco.framework.plugin.base.Activator;

/**
 * ImportWizard
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ImportWizard extends Wizard implements IImportWizard {

    private ImportWizardModel model;

    private List<ImportConfiguration> configurations = new ArrayList<ImportConfiguration>();

    private ImportWizardPage view;

    /**
     * Creates a new {@link ImportWizard} instance.
     */
    public ImportWizard() {
        try {

            IDialogSettings dialogSettings = WorkbenchPlugin.getDefault().getDialogSettings();
            super.setDialogSettings(dialogSettings);

            this.model = new ImportWizardModel();
            this.configurations.addAll(this.loadConfigurations());
            this.view = new ImportWizardPage(this.model, this.configurations);
        } catch (ClientException ce) {
            Activator.getDefault().logError(ce);
        }
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        super.setWindowTitle(I18N.i18n(ImportWizardConstants.TITLE));

        super.setDefaultPageImageDescriptor(WorkbenchImages
                .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_IMPORT_PREF_WIZ));

        super.addPage(this.view);
        this.view.setWizard(this);

        super.setNeedsProgressMonitor(true);
        super.setHelpAvailable(true);
    }

    @Override
    public boolean performFinish() {

        boolean valid = this.view.finish();

        if (valid) {
            ImportAction action = new ImportAction(this.model, this.configurations);

            try {
                return action.execute();

            } catch (ClientException exception) {
                Activator.getDefault().logError(exception);

                String message = I18N.i18n(ImportWizardConstants.ERROR_EXCEPTION);
                this.view.setErrorMessage(message);
            } catch (Exception exception) {
                Activator.getDefault().logError(exception);

                String message = I18N.i18n(ImportWizardConstants.ERROR_EXCEPTION);
                this.view.setErrorMessage(message);
            }
        }

        return false;
    }

    /**
     * Loads all persistent import configurations.
     * 
     * @return the list of configurations
     * 
     * @throws ClientException
     *             when the load failed
     */
    private List<ImportConfiguration> loadConfigurations() throws ClientException {

        SearchImportingDelegate searchService = ImportingComponentServiceDelegateFactory
                .getInstance().getSearchImporting();

        ImportConfigurationSearchRq request = this.createSearchRequest();
        ImportConfigurationListMsg response = searchService.searchImportConfiguration(request);

        return response.getImportConfigurationList();
    }

    /**
     * Create the importing request message.
     * 
     * @return the request message
     */
    private ImportConfigurationSearchRq createSearchRequest() {
        ImportConfigurationSearchRq request = new ImportConfigurationSearchRq();

        request.setImportType(ImportType.XML);

        return request;
    }

}
