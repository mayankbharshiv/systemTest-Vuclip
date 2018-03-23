package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vuclip.premiumengg.automation.billing_package_service.base.BPSValidationHelper;
import com.vuclip.premiumengg.automation.billing_package_service.base.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.base.BillingResponse;
import com.vuclip.premiumengg.automation.common.DBConnection;
import com.vuclip.premiumengg.automation.helpers.BPSHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

/**
 * Created by Kohitij_Das on 23/03/18.
 */
public class StoreBillingOption {

    private static Connection dbConnection;
    private BPSHelper bpsHelper;
    private BPSValidationHelper validationHelper;
    private DBUtils dbUtils;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        bpsHelper = new BPSHelper();
        validationHelper = new BPSValidationHelper();
        dbUtils = new DBUtils();

        setupDbConnection();
        cleanupBillingOption();
    }

    @Test
    public void verify_store_new_billing_option() throws Exception {
        final String body = new ObjectMapper().writeValueAsString(BillingPackage.PACKAGE2);
        final Response response = bpsHelper.storeBillingOption(body);
        validationHelper.validate_billing_response(response, BillingResponse.SUCCESS);
        validationHelper.validate_billing_package(response, BillingPackage.PACKAGE2);
    }

    @Test(dependsOnMethods = "verify_store_new_billing_option")
    public void verify_newly_created_billing_package() throws Exception {
        final String validBillingCode = BillingPackage.PACKAGE2.getBillingCode();
        validationHelper.validate_billing_packages(bpsHelper
                .getBillingOptionByBillingCode(validBillingCode), BillingPackage.PACKAGE2, "billingPackages"
        );
    }

    private void setupDbConnection() throws Exception {
        dbConnection = DBConnection.getDbConnection();
    }

    private void cleanupBillingOption() throws Exception {
        dbUtils.cleanBillingPackageTable(BillingPackage.PACKAGE2.getBillingCode(), dbConnection);
        dbUtils.cleanBillingPackageMappingTable(BillingPackage.PACKAGE2.getBillingCode(), dbConnection);
        dbUtils.cleanBillingPartnerTable(BillingPackage.PACKAGE2.getPartnerId(), dbConnection);
        dbUtils.cleanBillingProductTable(BillingPackage.PACKAGE2.getProductId(), dbConnection);
    }
}
