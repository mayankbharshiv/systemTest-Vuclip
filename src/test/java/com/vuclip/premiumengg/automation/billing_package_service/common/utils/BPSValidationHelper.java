package com.vuclip.premiumengg.automation.billing_package_service.common.utils;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingResponse;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Kohitij_Das on 22/03/18.
 */
public class BPSValidationHelper {

    private int fieldCount = 24;

    /**
     * @param billingResponse
     * @param responseType
     * @throws Exception
     */
    public void validate_billing_response(Response billingResponse, BillingResponse responseType) throws Exception {
        System.out.println("STATUS CODE: " + billingResponse.statusCode());
        AppAssert.assertEqual(billingResponse.getBody().jsonPath().getInt("responseCode"),
                responseType.getResponseCode());
        AppAssert.assertEqual(billingResponse.getBody().jsonPath().getString("message"), responseType.getMessage());
        AppAssert.assertEqual(billingResponse.getBody().jsonPath().getBoolean("successful"), responseType.isSuccessful());
    }

    /**
     * @param billingResponse
     * @throws Exception
     */
    public void validate_billing_packages(Response billingResponse, BillingPackage billingPackage, String rootNode)
            throws Exception {
        System.out.println("STATUS CODE: " + billingResponse.statusCode());

        JsonPath billingPackages = new JsonPath(billingResponse.asString());
        billingPackages.setRoot(rootNode);
        Map<?, ?> billingPack = billingPackages.get(getFindQuery(billingPackage.getProductId()));
        AppAssert.assertEqual(billingPackage.getProductName(), billingPack.get("productName").toString());
        AppAssert.assertEqual(billingPackage.getPartnerId(), Integer.parseInt(billingPack.get("partnerId").toString()));
        AppAssert.assertEqual(billingPackage.getBillingCode(), billingPack.get("billingCode").toString());
        AppAssert.assertEqual(fieldCount, billingPack.size());
    }

    /**
     * @param billingResponse
     * @param billingPackage
     * @throws Exception
     */
    public void validate_billing_package(Response billingResponse, BillingPackage billingPackage) throws Exception {
        AppAssert.assertEqual(billingResponse.getBody().jsonPath().getInt("billingPackage.productId"),
                billingPackage.getProductId());
        AppAssert.assertEqual(billingResponse.getBody().jsonPath().getString("billingPackage.productName"),
                billingPackage.getProductName());
        AppAssert.assertEqual(billingResponse.getBody().jsonPath().getInt("billingPackage.partnerId"),
                billingPackage.getPartnerId());
        AppAssert.assertEqual(billingResponse.getBody().jsonPath().getString("billingPackage.billingCode"),
                billingPackage.getBillingCode());
    }

    private String getFindQuery(int productId) throws ExecutionException {
        String jsonQuery = "find {e -> e.productId == " + productId + "}";
        return jsonQuery;
    }
}
