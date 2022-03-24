package main.plugin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.tzi.use.util.Log;

public class Utils {

	private static PrintWriter logWriter = null;

	private static File currentDirectory = null;


	public static void out(String output) {
		Log.println(output);
		if (logWriter != null) {
			logWriter.println(output);
		}
	}

	public static void error(Exception error) {
		error.printStackTrace();
		String errMsg = error.getMessage();
		if (errMsg == null || errMsg.isEmpty()) {
			errMsg = "Unknown Error";
		} else {
			errMsg = "Error: " + errMsg;
		}
		if (Log.isDebug()) {
			Log.error(error);
		} else {
			Log.error(errMsg);
		}
		if (logWriter != null) {
			logWriter.println(errMsg);
		}
	}

	public static void setLogWriter(PrintWriter theLogWriter) {
		logWriter = theLogWriter;
	}


	public static boolean canWrite(File file) {
		try {
			new FileOutputStream(file, true).close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static File getCurrentDirectory() {
		if (currentDirectory == null) {
			currentDirectory = new File (System.getProperty("user.home"));
		}
		if (!currentDirectory.isDirectory()) {
			currentDirectory = currentDirectory.getParentFile();
		}
		return currentDirectory;
	}

	public static void setCurrentDirectory(File theCurrentDirectory) {
		currentDirectory = theCurrentDirectory;
	}

}
