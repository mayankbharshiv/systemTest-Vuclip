package com.vuclip.premiumengg.automation.ad_network_service.common.base;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveProduct;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSDBUtils;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSHelper;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSTestContext;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSUtils;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.ANSValidationHelper;
import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitAdminConnection;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.common.RedisConnection;
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.DateTimeUtil;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.TimeUnitEnum;
import com.vuclip.premiumengg.automation.utils.RedisUtil;

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
		Log4J.getLogger().info("====== SettingUp ad-network-service-tests execution ======");
		FileInputStream inputStream = null;
		Properties properties = new Properties();
		try {
			String filePath = System.getProperty("propertiesFile");
			File configFile = new File(filePath);

			inputStream = new FileInputStream(configFile);
			properties.load(inputStream);

			Configuration.adNetworkServiceServer = properties.getProperty("ansServer");
			Configuration.dbServer = properties.getProperty("dbServer");
			Configuration.dbPort = properties.getProperty("dbPort");
			Configuration.dbName = properties.getProperty("dbName");
			Configuration.dbUser = properties.getProperty("dbUser");
			Configuration.dbPassword = properties.getProperty("dbPassword");

			Configuration.rabbitMQServer = properties.getProperty("rabbitMQServer");
			Configuration.rabbitMQPort = properties.getProperty("rabbitMQPort");
			Configuration.rabbitMQUser = properties.getProperty("rabbitMQUser");
			Configuration.rabbitMQPassword = properties.getProperty("rabbitMQPassword");

			Configuration.redisServers = properties.getProperty("ans.redis.clusters");

			Log4J.getLogger().info("Clean up Tables");
			ANSDBUtils.cleanAllTable();

			// Log4J.getLogger().info("Flush Redis");
			// new RedisUtil().flushAll(RedisConnection.getRedisConnection());

			ANSTestContext.productId = RandomUtils.nextInt(5000, 8000);
			ANSTestContext.partnerId = ANSTestContext.productId;
			ANSTestContext.adNetworkId = ANSTestContext.productId;
			ANSTestContext.billingCode = "BC" + ANSTestContext.productId;
			RabbitMQConnection.getRabbitTemplate().setMessageConverter(new Jackson2JsonMessageConverter());

			SaveProduct saveProduct = ANSUtils.generateSaveProductConfig(ANSTestContext.productId,
					ANSTestContext.productName, ANSTestContext.partnerId, ANSTestContext.adNetworkId,
					ANSTestContext.billingCode);

			ANSValidationHelper.validate_ans_api_response(
					ANSHelper.saveCountry(ANSTestContext.countryCode, ANSTestContext.timezone));

			// TODO RAHUL
			String sourceIdentifier = "D_KIM" + RandomUtils.nextInt(100, 900);
			ANSTestContext.requestParamName = "voluum_tid" + RandomStringUtils.random(5, true, true);
			ANSValidationHelper.validate_ans_api_response(ANSHelper.saveAdNetwork(ANSTestContext.adNetworkId,
					ANSTestContext.requestParamName, sourceIdentifier));

			ANSValidationHelper.validate_ans_api_response(ANSHelper.saveProduct(saveProduct));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void teardown() throws Exception {
		JDBCTemplate.closeAllConnections();
		RabbitMQConnection.closeAllConnection();
		RabbitAdminConnection.closeAllConnection();
		RedisTemplateConnection.closeConnection();
	}

}