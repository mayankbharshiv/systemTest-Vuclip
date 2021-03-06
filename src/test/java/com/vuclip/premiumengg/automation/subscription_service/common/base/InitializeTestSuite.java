package com.vuclip.premiumengg.automation.subscription_service.common.base;

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
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SaveProductRequest;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SDBHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SHelper;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SUtils;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.SValidationHelper;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

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
		Log4J.getLogger().info("====== SettingUp subscription-service-tests execution ======");
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
			RabbitMQConnection.getRabbitTemplate().setMessageConverter(new Jackson2JsonMessageConverter());

			Configuration.redisServers = properties.getProperty("ss.redis.clusters");
			RedisTemplateConnection.getRedisConnection().getConnectionFactory().getConnection().flushAll();
			SDBHelper.cleanAllTables(null);
			SUtils.productId = 8181;
			SUtils.productConfig = SUtils.loadJson("saveProduct.json", SaveProductRequest.class);
			String jsonString = ObjectMapperUtils.writeValueAsString(SUtils.productConfig);
			jsonString = jsonString.replaceAll("1111", String.valueOf(SUtils.productId));
			SUtils.productConfig = ObjectMapperUtils.readValueFromString(jsonString, SaveProductRequest.class);
			new SHelper();
			SValidationHelper.validate_ss_api_response(SHelper.saveProduct(SUtils.productConfig));

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