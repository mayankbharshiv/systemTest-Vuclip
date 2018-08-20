package com.vuclip.premiumengg.automation.ad_network_service.common.base;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveProduct;
import com.vuclip.premiumengg.automation.ad_network_service.common.utils.*;
import com.vuclip.premiumengg.automation.common.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

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
            ANSValidationHelper.validate_ans_api_response(ANSHelper.saveProduct(saveProduct));


            ANSValidationHelper.validate_ans_api_response(
                    ANSHelper.saveCountry(ANSTestContext.countryCode, ANSTestContext.timezone));

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