package com.vuclip.premiumengg.automation.scheduled_activity_service.common.base;

import com.vuclip.premiumengg.automation.common.*;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
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
        Log4J.getLogger().info("====== SettingUp scheduled-activity-service-tests execution ======");
        FileInputStream inputStream = null;
        Properties properties = new Properties();
        try {
            String filePath = System.getProperty("propertiesFile");
            File configFile = new File(filePath);

            inputStream = new FileInputStream(configFile);
            properties.load(inputStream);

            Configuration.sasServer = properties.getProperty("sasServer");
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
            Log4J.getLogger().info("Cleanup Database Tables");

            // Delete queue for product because no consumer attached in system test.
            RabbitUtil.deleteAllActivityQueue(SASUtils.productId, SASUtils.partnerId, SASUtils.countryCode);
            SASDBHelper.cleanAllTables(null);

            SASUtils.productId = 6789;
            SASUtils.partnerId = SASUtils.productId;
            SASUtils.countryCode = "IN";
            SASUtils.billingCode = "b1";
            SASUtils.productConfig = SASUtils.loadJson("publishConfigVO.json", PublishConfigRequest.class);
            // change billing code
            SASUtils.productConfig.getPricePoints().get(0).setBillingCode(SASUtils.billingCode);
            // change product partner id and country code in config json
            String jsonString = ObjectMapperUtils.writeValueAsString(SASUtils.productConfig);
            jsonString = jsonString.replaceAll("1111", String.valueOf(SASUtils.productId));
            jsonString = jsonString.replaceAll("9999", String.valueOf(SASUtils.partnerId));
            jsonString = jsonString.replaceAll("CCDE", SASUtils.countryCode);
            SASUtils.productConfig = ObjectMapperUtils.readValueFromString(jsonString, PublishConfigRequest.class);
            SASValidationHelper.validate_sas_api_response(new SASHelper().saveProduct(SASUtils.productConfig));

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