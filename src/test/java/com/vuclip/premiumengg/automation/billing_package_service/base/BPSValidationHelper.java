package com.vuclip.premiumengg.automation.billing_package_service.base;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

/**
 * Created by Kohitij_Das on 22/03/18.
 */
public class BPSValidationHelper {

    private String jsonQuery = "find {e -> e.productId == " + BillingPackage.PACKAGE1.getProductId() + "}";
    private int fieldCount = 23;

    /**
     * @param billingResponse
     * @param responseType
     * @throws Exception
     */
    public void validate_billing_response(Response billingResponse, BillingResponse responseType) throws Exception {
        Assert.assertEquals(billingResponse.getBody().jsonPath().getInt("responseCode"), responseType.getResponseCode());
        Assert.assertEquals(billingResponse.getBody().jsonPath().getString("message"), responseType.getMessage());
        Assert.assertEquals(billingResponse.getBody().jsonPath().getBoolean("successful"), responseType.isSuccessful());
    }

    /**
     * @param billingResponse
     * @throws Exception
     */
    public void validate_billing_package(Response billingResponse) throws Exception {
        JsonPath billingPackages = new JsonPath(billingResponse.asString());
        billingPackages.setRoot("billingPackages");
        Map billingPack = billingPackages.get(jsonQuery);
        Assert.assertEquals(BillingPackage.PACKAGE1.getProductName(), billingPack.get("productName"));
        Assert.assertEquals(BillingPackage.PACKAGE1.getPartnerId(), billingPack.get("partnerId"));
        Assert.assertEquals(BillingPackage.PACKAGE1.getBillingCode(), billingPack.get("billingCode"));
        Assert.assertEquals(fieldCount, billingPack.size());
    }
}
