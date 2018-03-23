package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.vuclip.premiumengg.automation.billing_package_service.base.BPSValidationHelper;
import com.vuclip.premiumengg.automation.billing_package_service.base.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.base.BillingResponse;
import com.vuclip.premiumengg.automation.helpers.BPSHelper;
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
        validationHelper.validate_billing_response(bpsHelper
                .getAllBillingOptions(), BillingResponse.SUCCESS
        );
    }

    @Test
    public void verify_get_all_billing_options_billingPackages() throws Exception {
        validationHelper.validate_billing_packages(bpsHelper
                .getAllBillingOptions(), BillingPackage.PACKAGE1, "billingPackages"
        );
    }
}
