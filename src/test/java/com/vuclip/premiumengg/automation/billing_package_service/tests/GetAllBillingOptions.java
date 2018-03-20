package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.vuclip.premiumengg.automation.helpers.BillingPackageServiceHelper;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Kohitij_Das on 20/03/18.
 */
public class GetAllBillingOptions {

    private BillingPackageServiceHelper bpsHelper;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        bpsHelper = new BillingPackageServiceHelper();
    }

    @Test
    public void verify_get_all_billing_options_response_code() throws Exception {
        Assert.assertEquals(bpsHelper.performGetAllBillingOptions()
                .getBody().jsonPath().getInt("responseCode"), 1);
    }

    @Test
    public void verify_get_all_billing_options_message() throws Exception {
        Assert.assertEquals(bpsHelper.performGetAllBillingOptions()
                .getBody().jsonPath().getString("message"), "Success");
    }

    @Test
    public void verify_get_all_billing_options_billingPackages() throws Exception {
        String json = bpsHelper.performGetAllBillingOptions().asString();
        JsonPath billingPackages = new JsonPath(json);
        billingPackages.setRoot("billingPackages");
        Map billingPack = billingPackages.get("find {e -> e.productId == 11}");
        Assert.assertEquals("TestProduct11", billingPack.get("productName"));
        Assert.assertEquals(11, billingPack.get("partnerId"));
        Assert.assertEquals("927312121", billingPack.get("billingCode"));
    }
}
