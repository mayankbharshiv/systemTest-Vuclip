package com.vuclip.premiumengg.automation.scheduled_activity_service.common.base;

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
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASDBHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
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
          
            SASDBHelper.cleanAllTables(null);

			SASUtils.productId = 1234567;
			SASUtils.productConfig = SASUtils.loadJson("publishConfigVO.json", PublishConfigRequest.class);
			String jsonString = ObjectMapperUtils.writeValueAsString(SASUtils.productConfig);
			jsonString=jsonString.replaceAll("1111", String.valueOf(SASUtils.productId));
			SASUtils.productConfig = ObjectMapperUtils.readValueFromString(jsonString, PublishConfigRequest.class);
			SASValidationHelper.validate_sas_api_response(new SASHelper().saveProduct(SASUtils.productConfig));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() throws Exception {
        JDBCTemplate.closeAllConnections();
    }

}