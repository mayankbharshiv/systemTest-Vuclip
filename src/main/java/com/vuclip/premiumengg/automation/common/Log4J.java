package com.vuclip.premiumengg.automation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author mayank.bharshiv
 */
public class Log4J {
	private static Logger logger;

	private Log4J() {
	}

	public static Logger getLogger() {
		if (logger == null) {
			System.out.println("========== Creating LOG4J ============");
			String currentDir = System.getProperty("user.dir");
			File logFile = new File(currentDir + "/AutomationTestNG.log");

			logger = Logger.getLogger(Log4J.class);
			if (logFile.exists()) {
				logFile.delete();
			}

			try {
				Properties logProperties = new Properties();
				// load log4j properties configuration file
				logProperties.load(new FileInputStream("log4j.properties"));
				PropertyConfigurator.configure(logProperties);
				logger.info("Logging initialized.");
			} catch (IOException e) {
				logger.error("Unable to load logging property :", e);
			}
		}
		return logger;
	}
}
