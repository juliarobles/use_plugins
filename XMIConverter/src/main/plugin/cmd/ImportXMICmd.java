package main.plugin.cmd;

import java.io.File;

import org.tzi.use.main.shell.runtime.IPluginShellCmd;
import main.plugin.XMIHandlerPlugin;
import org.tzi.use.runtime.shell.IPluginShellCmdDelegate;

public class ImportXMICmd implements IPluginShellCmdDelegate {

	@Override
	public final void performCommand(IPluginShellCmd pluginCommand) {
		String[] args = pluginCommand.getCmdArgumentList();
		if(args.length > 1) {
			File xmiFile = new File(args[0].trim());
			File pathGeneratedUse = new File(args[1].trim());
			XMIHandlerPlugin.getXMIHandlerPluginInstance().importFromXMI(xmiFile, pathGeneratedUse,
					pluginCommand.getSession(), null);
		} else {
			System.out.println("Not enough arguments have been introduced.");
		}
		
	}

}
