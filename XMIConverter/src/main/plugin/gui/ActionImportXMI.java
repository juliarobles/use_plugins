package main.plugin.gui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;

import main.plugin.utils.Utils;

/**
 * This action opens a dialog to select file for import,
 * which later performs import if file is selected
 *
 * @author Emil Huseynli
 *
 */
public class ActionImportXMI implements IPluginActionDelegate {

	/**
	 * Default constructor
	 */
	public ActionImportXMI() {	}

	@Override
	public void performAction(IPluginAction pluginAction) {
		// Getting MainWindow object from Proxy
		MainWindow fMainWindow = pluginAction.getParent();

		if(pluginAction.getSession().hasSystem()) {
			Object[] options = { "Yes", "No" };
			int option = JOptionPane.showOptionDialog(fMainWindow,
					"Do you want to import a XMI file and discard your current system state?",
					"XMI Converter", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[1]);
			if (option == JOptionPane.YES_OPTION) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(Utils.getCurrentDirectory());
				fileChooser.setDialogTitle("Import from XMI - Select directory for generated use file");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(fMainWindow);
				if (result == JFileChooser.APPROVE_OPTION) {
					XMIHandlerView view = new XMIHandlerView(fMainWindow, pluginAction.getSession(), XMIHandlerView.ViewMode.IMPORT, fileChooser.getSelectedFile());
					view.setVisible(true);
				}
			} else {
				return;
			}
		}
	}

}
