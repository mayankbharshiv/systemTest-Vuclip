package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.vuclip.premiumengg.automation.helpers.BPSHelper;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Kohitij_Das on 20/03/18.
 */
public class GetAllBillingOptions {

    private BPSHelper bpsHelper;
    private String jsonQuery = "find {e -> e.productId == 11}";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        bpsHelper = new BPSHelper();
    }

    @Test
    public void verify_get_all_billing_options_response_code() throws Exception {
        Assert.assertEquals(bpsHelper.getAllBillingOptions()
                .getBody().jsonPath().getInt("responseCode"), 1);
    }

    @Test
    public void verify_get_all_billing_options_message() throws Exception {
        Assert.assertEquals(bpsHelper.getAllBillingOptions()
                .getBody().jsonPath().getString("message"), "Success");
    }

    @Test
    public void verify_get_all_billing_options_billingPackages() throws Exception {
        JsonPath billingPackages = new JsonPath(bpsHelper.getAllBillingOptions().asString());
        billingPackages.setRoot("billingPackages");
        Map billingPack = billingPackages.get(jsonQuery);
        Assert.assertEquals("TestProduct11", billingPack.get("productName"));
        Assert.assertEquals(11, billingPack.get("partnerId"));
        Assert.assertEquals("927312121", billingPack.get("billingCode"));
    }
}
