package com.vuclip.premiumengg.automation.schedular_service.common.base;

import com.vuclip.premiumengg.automation.common.*;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSDBHelper;
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

            SSDBHelper.cleanTestData();
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