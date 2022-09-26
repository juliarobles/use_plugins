package main.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;

import org.tzi.use.main.Session;
import org.tzi.use.parser.use.USECompiler;
import org.tzi.use.runtime.impl.Plugin;
import org.tzi.use.uml.mm.MModel;
import org.tzi.use.uml.mm.ModelFactory;
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
			Utils.out("Exporting USE file with path " + source + "...");
			Generators.fromUSEtoUML(source, destiny);
			Utils.out("Check the console. If no errors occurred, the file has been successfully exported to: " + destiny + "\n");
		} catch (Exception ex) {
			Utils.error(ex);
			Utils.out("Export failed. (Check plugin limitations)\n");
		}
	}

	public void importFromXMI(File file, File pathGeneratedUse, Session session, PrintWriter logWriter) {
		Utils.setLogWriter(logWriter);
		try {
			Path destinyPath = pathGeneratedUse.toPath();
			String destiny = destinyPath.toString();
			String source = file.getAbsolutePath();
			Utils.out("Importing UML file with path " + source + "...");
			Generators.fromUMLtoUSE(source, destiny);
			Utils.out("Check the console. If no errors occurred, the file has been correctly converted to USE and stored in " + destiny);
			Optional<Path> lastFilePath = Files.list(destinyPath)    // here we get the stream with full directory listing
				    .filter(f -> !Files.isDirectory(f) && f.getFileName().toString().startsWith("modelConverter_"))  // exclude subdirectories from listing
				    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));  // finally get the last file using simple comparator by lastModified field

			if ( lastFilePath.isPresent() ) // your folder may be empty
			{
				destiny = lastFilePath.get().toString();
				try (FileInputStream specStream = new FileInputStream(destiny)){
					Utils.out("Compiling generated specification...");
					MModel model = USECompiler.compileSpecification(specStream,
						destiny, new PrintWriter(System.err), new ModelFactory());
					session.setSystem(new MSystem(model));
					Utils.out("Model loaded successfully.\n");
				} catch (FileNotFoundException e) {
					Utils.out("File `" + destiny + "' not found.");
					System.exit(1);
				} catch (IOException e1) {
					// close failed
				}
			} else {
				Utils.out("File `" + destinyPath + "' not found.");
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception ex) {
				Utils.error(ex);
				Utils.out("Import failed. (Check plugin limitations)");
		}
	}
	
	private String getFilename(Session session) {
		return session.system().model().filename();
	}

}
