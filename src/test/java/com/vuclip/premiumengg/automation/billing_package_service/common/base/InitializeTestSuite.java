package com.vuclip.premiumengg.automation.billing_package_service.common.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.BPSSaveCountryRequest;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BPSSavePartnerRequest;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BPSSaveProductRequest;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSContext;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSDBUtil;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSHelper;
import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.JDBCTemplate;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

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

			int id = RandomUtils.nextInt(100, 100000);
			BPSHelper bPSHelper = new BPSHelper();
			BPSSaveCountryRequest saveCountryRequest = ObjectMapperUtils.readValue(
					"src/test/resources/configurations/billing-package-service/request/saveCountry.json",
					BPSSaveCountryRequest.class);
			bPSHelper.saveCountry(saveCountryRequest);
			BPSSavePartnerRequest savePartnerRequest = ObjectMapperUtils.readValue(
					"src/test/resources/configurations/billing-package-service/request/savePartner.json",
					BPSSavePartnerRequest.class);
			savePartnerRequest.setPartnerId(id);
			savePartnerRequest.setPartnerName("PN_" + id);
			bPSHelper.savePartner(savePartnerRequest);
			BPSSaveProductRequest saveProductRequest = ObjectMapperUtils.readValue(
					"src/test/resources/configurations/billing-package-service/request/saveProduct.json",
					BPSSaveProductRequest.class);
			saveProductRequest.getProduct().setProductId(id);
			saveProductRequest.getProduct().setProductName("PN_" + id);
			saveProductRequest.getPricePoints().get(0).setPartnerId(id);
			saveProductRequest.getPricePoints().get(0).setProductId(id);
			saveProductRequest.getPricePoints().get(0).setBillingCode("billingCode" + id + "1");
			saveProductRequest.getPricePoints().get(0).setFallbackPpBillingCode("billingCode" + id + "2");
			saveProductRequest.getPricePoints().get(0).setFreeTrialBillingCode("billingCode" + id + "3");
			bPSHelper.saveProduct(saveProductRequest);
			BPSContext.saveCountryRequest = saveCountryRequest;
			BPSContext.savePartnerRequest = savePartnerRequest;
			BPSContext.saveProductRequest = saveProductRequest;

			BillingPackage.PACKAGE1 = new BillingPackage(id, "PN_" + id, id, "PN_" + id, "IN", "IN",
					"billingCode" + id + "1", saveProductRequest.getPricePoints().get(0).getServiceId(),
					saveProductRequest.getPricePoints().get(0).getPrice(), saveCountryRequest.getCurrency(), 0,
					saveProductRequest.getPricePoints().get(0).getParkingPeriod(),
					saveProductRequest.getPricePoints().get(0).getSuspendPeriod(),
					saveProductRequest.getPricePoints().get(0).getNotificationWaitPeriod(),
					saveProductRequest.getPricePoints().get(0).getTimeUnit(),
					saveProductRequest.getPricePoints().get(0).getItemId(),
					saveProductRequest.getPricePoints().get(0).getItemTypeId(), 0,
					saveProductRequest.getPricePoints().get(0).getStatus(),
					saveProductRequest.getPricePoints().get(0).getBalanceCheckRequired(), false, false, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite(alwaysRun = true)
	public void teardown() throws Exception {
		JDBCTemplate.closeAllConnections();
		new BPSDBUtil().cleanupBillingPackage();
	}
}