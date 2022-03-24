package main.plugin.cmd;

import java.io.File;

import org.tzi.use.main.shell.runtime.IPluginShellCmd;
import main.plugin.XMIHandlerPlugin;
import org.tzi.use.runtime.shell.IPluginShellCmdDelegate;

public class ExportXMICmd implements IPluginShellCmdDelegate {

	@Override
	public final void performCommand(IPluginShellCmd pluginCommand) {
		File xmiFile = new File(pluginCommand.getCmdArguments().trim());
		XMIHandlerPlugin.getXMIHandlerPluginInstance().exportToXMI(xmiFile, pluginCommand.getSession(), null);
	}

}
