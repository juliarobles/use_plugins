package main.plugin;

import java.io.File;
import java.io.PrintWriter;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.main.Session;
import org.tzi.use.runtime.impl.Plugin;
import org.tzi.use.uml.mm.MModel;
import org.tzi.use.uml.sys.MSystem;

import main.model.Generators;
import main.plugin.utils.Utils;

public class XMIHandlerPlugin extends Plugin {

	private static String PLUGIN_NAME = "XMIConverter";

	private static XMIHandlerPlugin xmiHandlerPlugin = new XMIHandlerPlugin();

	public static XMIHandlerPlugin getXMIHandlerPluginInstance() {
		return xmiHandlerPlugin;
	}

	@Override
	public String getName() {
		return PLUGIN_NAME;
	}

	public void exportToXMI(File file, Session session, PrintWriter logWriter) {
		Utils.setLogWriter(logWriter);
		try {
			String source = getFilename(session);
			String destiny = file.getAbsolutePath();
			Utils.out("Filename: " + source);
			Utils.out("File: " + destiny);
			Generators.fromUSEtoUML(source, destiny);
			Utils.out("Successfully exported to: " + destiny);
		} catch (Exception ex) {
			Utils.error(ex);
			Utils.out("Export failed.");
		}
	}

	public void importFromXMI(File file, Session session, PrintWriter logWriter) {
		Utils.setLogWriter(logWriter);
		try {
			Utils.out("Filename: " + getFilename(session));
			Utils.out("File: " + file.getAbsolutePath());
		} catch (Exception ex) {
			Utils.error(ex);
			Utils.out("Import failed.");
		}
	}
	
	private String getFilename(Session session) {
		return session.system().model().filename();
	}

}
