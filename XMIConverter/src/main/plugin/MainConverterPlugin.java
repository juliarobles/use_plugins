package main.plugin;

import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;

/*
import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.main.Session;
import org.tzi.use.uml.sys.MSystem;
import org.tzi.use.config.Options;
import main.model.Generators;
*/

public class MainConverterPlugin implements IPluginActionDelegate {
	
	public MainConverterPlugin () {
		
	}

	@Override
	public void performAction(IPluginAction pluginAction) {
		/*
		// TODO Auto-generated method stub
		System.out.println("A0");
		Session curSession = pluginAction.getSession();
		System.out.println("A1");
		MSystem curSystem = curSession.system();
		System.out.println("A2");
		MainWindow curMainWindow = pluginAction.getParent();
		System.out.println("A4");
		
		String fileName = Options.specFilename;
		if (fileName != null) {
			//Generators.fromUSEtoUML(fileName, "C:/Users/julia");
			System.out.println("Filename: " + fileName);
			curMainWindow.logWriter().println("Filename: " + fileName);
		}
		*/
		System.out.println("FUNCIONA!");
		
	}
}
