package main.plugin.gui;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;

public class ActionExportXMI implements IPluginActionDelegate {

	/**
	 * Default constructor
	 */
	public ActionExportXMI() {	}

	@Override
	public void performAction(IPluginAction pluginAction) {
		// Getting MainWindow object from Proxy
		MainWindow fMainWindow = pluginAction.getParent();

		XMIHandlerView view = new XMIHandlerView(fMainWindow, pluginAction.getSession(), XMIHandlerView.ViewMode.EXPORT);
		view.setVisible(true);
	}

}
