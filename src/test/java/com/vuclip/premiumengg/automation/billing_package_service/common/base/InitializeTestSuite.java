package com.vuclip.premiumengg.automation.billing_package_service.common.base;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import org.testng.annotations.AfterSuite;
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
        System.out.println("====== SettingUp billing-package-service-system-tests execution ======");
        FileInputStream inputStream = null;
        Properties properties = new Properties();
        try {
            String filePath = System.getProperty("propertiesFile");
            File configFile = new File(filePath);

            inputStream = new FileInputStream(configFile);
            properties.load(inputStream);

            Configuration.billingPackageServer = properties.getProperty("billingPackageServer");
            Configuration.dbServer = properties.getProperty("dbServer");
            Configuration.dbPort = properties.getProperty("dbPort");
            Configuration.dbName = properties.getProperty("dbName");
            Configuration.dbUser = properties.getProperty("dbUser");
            Configuration.dbPassword = properties.getProperty("dbPassword");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() throws Exception {
        JDBCTemplate.closeAllConnections();
    }
}