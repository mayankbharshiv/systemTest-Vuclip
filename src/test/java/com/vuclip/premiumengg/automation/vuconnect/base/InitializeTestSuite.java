package com.vuclip.premiumengg.automation.vuconnect.base;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.DBConnection;
import com.vuclip.premiumengg.automation.common.RedisConnection;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Kohitij_Das
 */
public class InitializeTestSuite {

    /**
     * Method to be invoked before launch of any Suite execution.
     */
    @BeforeSuite(alwaysRun = true)
    public final void init() {
        System.out.println("====== SettingUp vuconnect-system-tests execution ======");
        FileInputStream inputStream = null;
        Properties properties = new Properties();
        try {
            String filePath = System.getProperty("propertiesFile");
            File configFile = new File(filePath);

            inputStream = new FileInputStream(configFile);
            properties.load(inputStream);

            Configuration.vuConnectServer = properties.getProperty("vuConnectServer");
            Configuration.testDataServer = properties.getProperty("testDataServer");
            Configuration.redisServers = properties.getProperty("redisServers");
            Configuration.redisVuconnectDomain = properties.getProperty("redisVuconnectDomain");
            Configuration.dbServer = properties.getProperty("dbServer");
            Configuration.dbPort = properties.getProperty("dbPort");
            Configuration.vuconnectDbName = properties.getProperty("vuconnectDbName");
            Configuration.dbUser = properties.getProperty("dbUser");
            Configuration.dbPassword = properties.getProperty("dbPassword");
            Configuration.rabbitmqUserName = properties.getProperty("rabbitmqUserName");
            Configuration.rabbitmqPassword = properties.getProperty("rabbitmqPassword");
            Configuration.rabbitmqHost = properties.getProperty("rabbitmqHost");
            Configuration.rabbitmqQueueType = properties.getProperty("rabbitmqQueueType");
            Configuration.vuconnectSystemtestDbName = properties.getProperty("vuconnectSystemtestDbName");


            new DBConnection();
            new RedisConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}