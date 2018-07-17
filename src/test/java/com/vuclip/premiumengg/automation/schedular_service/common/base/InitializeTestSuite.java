package com.vuclip.premiumengg.automation.schedular_service.common.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitAdminConnection;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;

/**
 * @author Rahul Sahu
 */
@Test
public class InitializeTestSuite {

	/**
	 * Method to be invoked before launch of any Suite execution.
	 */
	@BeforeSuite(alwaysRun = true)
	public final void init() {
		Log4J.getLogger().info("====== SettingUp schedular-service-tests execution ======");
		FileInputStream inputStream = null;
		Properties properties = new Properties();
		try {
			String filePath = System.getProperty("propertiesFile");
			File configFile = new File(filePath);

			inputStream = new FileInputStream(configFile);
			properties.load(inputStream);

			Configuration.ssServer = properties.getProperty("ssServer");
			Configuration.dbServer = properties.getProperty("dbServer");
			Configuration.dbPort = properties.getProperty("dbPort");
			Configuration.dbName = properties.getProperty("dbName");
			Configuration.dbUser = properties.getProperty("dbUser");
			Configuration.dbPassword = properties.getProperty("dbPassword");

			Configuration.rabbitMQServer = properties.getProperty("rabbitMQServer");
			Configuration.rabbitMQPort = properties.getProperty("rabbitMQPort");
			Configuration.rabbitMQUser = properties.getProperty("rabbitMQUser");
			Configuration.rabbitMQPassword = properties.getProperty("rabbitMQPassword");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void teardown() throws Exception {
		JDBCTemplate.closeAllConnections();
		RabbitMQConnection.closeAllConnection();
		RabbitAdminConnection.closeAllConnection();
	}

}