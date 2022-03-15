package main.plugin;

import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;


import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.main.Session;
import org.tzi.use.uml.mm.MModel;
import org.tzi.use.uml.sys.MSystem;
import main.model.Generators;


public class MainConverterPlugin implements IPluginActionDelegate {
	
	public MainConverterPlugin () {
		
	}

	@Override
	public void performAction(IPluginAction pluginAction) {
		
		// TODO Auto-generated method stub
		
		Session curSession = pluginAction.getSession();
		MSystem curSystem = curSession.system();
		MModel model = curSystem.model();
		
		MainWindow curMainWindow = pluginAction.getParent();
		
		String fileName = model.filename();
		if (fileName != null) {
			Generators.fromUSEtoUML(fileName, "C:/Users/julia");
			System.out.println("Filename: " + fileName);
			curMainWindow.logWriter().println("Filename: " + fileName);
		}
		System.out.println("FUNCIONA!");
		
	}
}
