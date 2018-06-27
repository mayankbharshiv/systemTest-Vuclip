package com.vuclip.premiumengg.automation.billing_package_service.common.utils;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingPackage;
import com.vuclip.premiumengg.automation.utils.DBUtils;

/**
 * Created by Kohitij_Das on 23/03/18.
 */
public class BPSDBUtil extends DBUtils {

	// private Statement sqlStatement;

	/**
	 * @param billingCode
	 * @param dbConnection
	 * @throws Exception
	 */
	public void cleanBillingPackageTable(String billingCode) {
		cleanTable("billing_package", "billing_code='" + billingCode + "'");
	}

	/**
	 * @param billingCode
	 * @param dbConnection
	 */
	public void cleanBillingPackageMappingTable(String billingCode) {
		// String query = "delete from billing_package_mapping where billing_code='" +
		// billingCode + "'";
		cleanTable("billing_package_mapping", "billing_code='" + billingCode + "'");

	}

	/**
	 * @param partnerId
	 * @param dbConnection
	 */
	public void cleanBillingPartnerTable(int partnerId) {
		// String query = "delete from partner where partner_id='" + partnerId + "'";
		cleanTable("partner", "partner_id='" + partnerId + "'");

	}

	/**
	 * @param productId
	 * @param dbConnection
	 */
	public void cleanBillingProductTable(int productId) {
		// String query = "delete from product where product_id='" + productId + "'";
		cleanTable("product", "product_id='" + productId + "'");

	}

	public void cleanupBillingPackage() throws Exception {
		cleanBillingPackageTable(BillingPackage.PACKAGE1.getBillingCode());
		cleanBillingPackageMappingTable(BillingPackage.PACKAGE1.getBillingCode());
		cleanBillingPartnerTable(BillingPackage.PACKAGE1.getPartnerId());
		cleanBillingProductTable(BillingPackage.PACKAGE1.getProductId());
	}
}
