package main.plugin.gui;

import java.io.File;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.main.Session;
import main.plugin.utils.Utils;
import main.plugin.XMIHandlerPlugin;
import main.plugin.utils.IWorkerRunner;

@SuppressWarnings("serial")
public class XMIHandlerView extends JFileChooser {

	private Session session;
	private MainWindow mainWindow;
	private File pathGeneratedUse;

	public enum ViewMode {
		EXPORT, IMPORT
	}

	public XMIHandlerView(MainWindow theParent, Session theSession, ViewMode viewMode, File pathGeneratedUse) {
		this.session = theSession;
		this.mainWindow = theParent;
		this.pathGeneratedUse = pathGeneratedUse;
		initGUI(viewMode);
	}

	@Override
	public Locale getLocale() {
		return Locale.ENGLISH;
	}

	private void initGUI(final ViewMode viewMode) {
		int returnVal = -1;
		setCurrentDirectory(Utils.getCurrentDirectory());
		if (viewMode == ViewMode.EXPORT) {
			setDialogTitle("Export to XMI");
			setFileSelectionMode(DIRECTORIES_ONLY);
			returnVal = showDialog(mainWindow, "Export");
		} else {
			setDialogTitle("Import from XMI - Select XMI file to import");
			setFileFilter(new FileNameExtensionFilter("Eclipse UML2 (v3.x) XMI (*.uml, *.xmi)", "uml", "xmi"));
			returnVal = showDialog(mainWindow, "Import");
		}
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Utils.setCurrentDirectory(getSelectedFile());
			switch (viewMode) {
			case EXPORT:
				XMIHandlerPlugin.getXMIHandlerPluginInstance().exportToXMI(
						getSelectedFile(), session, mainWindow.logWriter());
				break;
			case IMPORT:
				XMIHandlerPlugin.getXMIHandlerPluginInstance().importFromXMI(
						getSelectedFile(), pathGeneratedUse, session, mainWindow.logWriter());
				break;
			}
			/*WaitDialog dlg = new WaitDialog(mainWindow, true);
			dlg.start(new IWorkerRunner() {

				@Override
				public Object doWork() {
					switch (viewMode) {
					case EXPORT:
						XMIHandlerPlugin.getXMIHandlerPluginInstance().exportToXMI(
								getSelectedFile(), session, mainWindow.logWriter());
						break;
					case IMPORT:
						XMIHandlerPlugin.getXMIHandlerPluginInstance().importFromXMI(
								getSelectedFile(), pathGeneratedUse, session, mainWindow.logWriter());
						break;
					}
					return Boolean.TRUE;
				}

				@Override
				public void doUpdate() {
				}
			});
			centerWindow(dlg, mainWindow);
			dlg.setVisible(true);*/

		}
	}

	public void centerWindow(JDialog dlg, JFrame frame) {
		dlg.setLocation(frame.getLocationOnScreen().x + frame.getSize().width
				/ 2 - (dlg.getWidth() / 2), frame.getLocationOnScreen().y
				+ frame.getSize().height / 2);
	}

}
