package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingResponse;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSHelper;
import com.vuclip.premiumengg.automation.billing_package_service.common.utils.BPSValidationHelper;
import com.vuclip.premiumengg.automation.common.Log4J;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Kohitij_Das on 20/03/18.
 */
public class GetAllBillingOptions {

	private BPSHelper bpsHelper;
	private BPSValidationHelper validationHelper;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		bpsHelper = new BPSHelper();
		validationHelper = new BPSValidationHelper();
	}

	@Test
	public void verify_get_all_billing_options_success_response() throws Exception {
		Log4J.getLogger()
				.info("===================================>TEST: verify_get_all_billing_options_success_response");
		validationHelper.validate_billing_response(bpsHelper.getAllBillingOptions(), BillingResponse.SUCCESS);
	}

	@Test
	public void verify_get_all_billing_options_billingPackages() throws Exception {
		Log4J.getLogger()
				.info("===================================>TEST: verify_get_all_billing_options_billingPackages");

		validationHelper.validate_billing_packages(bpsHelper.getAllBillingOptions(), BillingPackage.PACKAGE1,
				"billingPackages");
	}
}
