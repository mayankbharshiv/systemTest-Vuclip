package com.vuclip.premiumengg.automation.core_activity_service.common.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.SavePartnerRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.SaveProductRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASDBUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASHelper;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASUtils;
import com.vuclip.premiumengg.automation.core_activity_service.common.utils.CASValidationHelper;

/**
 * @author Kohitij_Das
 */
public class InitializeTestSuite {

	/**
	 * Method to be invoked before launch of any Suite execution.
	 */
	@BeforeSuite(alwaysRun = true)
	public final void init() {
		System.out.println("====== SettingUp core-activity-service-system-tests execution ======");
		FileInputStream inputStream = null;
		Properties properties = new Properties();
		try {
			String filePath = System.getProperty("propertiesFile");
			File configFile = new File(filePath);

			inputStream = new FileInputStream(configFile);
			properties.load(inputStream);

			Configuration.casServer = properties.getProperty("casServer");
			Configuration.dbServer = properties.getProperty("dbServer");
			Configuration.dbPort = properties.getProperty("dbPort");
			Configuration.dbName = properties.getProperty("dbName");
			Configuration.dbUser = properties.getProperty("dbUser");
			Configuration.dbPassword = properties.getProperty("dbPassword");
			Configuration.redisServers = properties.getProperty("cas.redis.clusters");
			CASUtils.uBSMockURL = properties.getProperty("uBSMockURL");
			CASUtils.partnerId = 1;
			CASUtils.productId = 1;

			CASDBUtils.dbCleanUp(CASUtils.productId = 1, CASUtils.partnerId = 1);
			System.out.println("Save Product");
			SaveProductRequestVO saveProductRequest = CASUtils.saveProductRequest(CASUtils.productId);
			CASValidationHelper.validateResponse(CASHelper.saveProduct(saveProductRequest));

			System.out.println("Save Partner");
			SavePartnerRequestVO savePartnerRequest = CASUtils.savePartnerRequest(CASUtils.partnerId,
					CASUtils.uBSMockURL);
			CASValidationHelper.validateResponse(CASHelper.savePartner(savePartnerRequest));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void teardown() throws Exception {
		JDBCTemplate.closeAllConnections();
	}
}