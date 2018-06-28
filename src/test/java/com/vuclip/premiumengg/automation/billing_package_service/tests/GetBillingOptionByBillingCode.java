package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingResponse;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSContext;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSHelper;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSValidationHelper;
import com.vuclip.premiumengg.automation.common.Log4J;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Kohitij_Das on 22/03/18.
 */
public class GetBillingOptionByBillingCode {

	private BPSHelper bpsHelper;
	private BPSValidationHelper validationHelper;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		bpsHelper = new BPSHelper();
		validationHelper = new BPSValidationHelper();
	}

	@Test
	public void verify_get_billing_options_with_valid_billingCode() throws Exception {
		Log4J.getLogger()
				.info("===================================>TEST: verify_get_billing_options_with_valid_billingCode");

		final String validBillingCode = BPSContext.saveProductRequest.getPricePoints().get(0).getBillingCode();
		validationHelper.validate_billing_packages(bpsHelper.getBillingOptionByBillingCode(validBillingCode),
				BillingPackage.PACKAGE1, "billingPackages");
	}

	@Test
	public void verify_get_billing_options_with_invalid_billingCode() throws Exception {
		Log4J.getLogger()
				.info("===================================>TEST: verify_get_billing_options_with_invalid_billingCode");

		final String invalidBillingCode = "999999";
		validationHelper.validate_billing_response(bpsHelper.getBillingOptionByBillingCode(invalidBillingCode),
				BillingResponse.NOTFOUND);
	}
}
