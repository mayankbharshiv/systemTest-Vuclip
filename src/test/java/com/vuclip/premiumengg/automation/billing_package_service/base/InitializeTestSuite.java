package com.vuclip.premiumengg.automation.billing_package_service.base;

import com.vuclip.premiumengg.automation.common.Configuration;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}