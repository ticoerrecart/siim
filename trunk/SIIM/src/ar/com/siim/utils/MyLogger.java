package ar.com.siim.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class MyLogger {

	private static Logger logger = Logger.getLogger("MyLog");

	private static Boolean LOGGEAR = true;

	private static ResourceBundle resourceBundle;

	static {
		resourceBundle = ResourceBundle.getBundle("config");
		String log = resourceBundle.getString("logger");
		if (log != null) {
			LOGGEAR = Boolean.valueOf(log);
		}
		String path = resourceBundle.getString("logger.path");
		try {
			// This block configure the logger with handler and formatter
			FileHandler fh = new FileHandler(path, true);
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			logger.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void log(String mensaje) {
		// the following statement is used to log any messages
		if (LOGGEAR) {
			logger.log(Level.INFO, mensaje);
		}
	}

	public static void logError(Throwable t) {
		// the following statement is used to log any messages
		if (LOGGEAR) {
			logger.log(Level.SEVERE, stackToString(t));
		}
	}

	public static String stackToString(Throwable t) {
		// recibe una excepción y devuelve un string con el stackTrace
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			return "------\r\n" + sw.toString() + "------\r\n";
		} catch (Exception exc) {
			return "No se pudo recuperar el StackTrace debido a: " + exc.getMessage();
		}
	}

	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

}
