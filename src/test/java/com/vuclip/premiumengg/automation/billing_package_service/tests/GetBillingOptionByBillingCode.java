package com.vuclip.premiumengg.automation.billing_package_service.tests;

import com.vuclip.premiumengg.automation.helpers.BPSHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Kohitij_Das on 22/03/18.
 */
public class GetBillingOptionByBillingCode {

    private BPSHelper bpsHelper;
    private String jsonQuery = "find {e -> e.productId == 11}";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        bpsHelper = new BPSHelper();
    }

    @Test
    public void verify_get_billing_options_with_valid_billingCode() throws Exception {
        final String validBillingCode = "927312121";
        JsonPath billingPackages = new JsonPath(bpsHelper
                .getBillingOptionByBillingCode(validBillingCode).asString());
        billingPackages.setRoot("billingPackages");
        Map billingPackage = billingPackages.get(jsonQuery);
        Assert.assertEquals(billingPackage.get("serviceId"), "TEST_SERVICEID_927311336");
        Assert.assertEquals(billingPackage.size(), 23);
    }

    @Test
    public void verify_get_billing_options_with_invalid_billingCode() throws Exception {
        final String invalidBillingCode = "999999";
        final Response response = bpsHelper.getBillingOptionByBillingCode(invalidBillingCode);
        Assert.assertEquals(response.getBody().jsonPath().getString("message"),
                "No billing packages found");
        Assert.assertEquals(response.getBody().jsonPath().getBoolean("successful"),
                false);
        Assert.assertEquals(response.getBody().jsonPath().getInt("responseCode"),
                404);
    }
}
