package com.vuclip.premiumengg.automation.billing_package_service.common.utils;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.testng.Assert;

import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingPackage;
import com.vuclip.premiumengg.automation.billing_package_service.common.models.BillingResponse;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Created by Kohitij_Das on 22/03/18.
 */
public class BPSValidationHelper {

	private int fieldCount = 23;

	/**
	 * @param billingResponse
	 * @param responseType
	 * @throws Exception
	 */
	public void validate_billing_response(Response billingResponse, BillingResponse responseType) throws Exception {
		Assert.assertEquals(billingResponse.getBody().jsonPath().getInt("responseCode"),
				responseType.getResponseCode());
		Assert.assertEquals(billingResponse.getBody().jsonPath().getString("message"), responseType.getMessage());
		Assert.assertEquals(billingResponse.getBody().jsonPath().getBoolean("successful"), responseType.isSuccessful());
	}

	/**
	 * @param billingResponse
	 * @throws Exception
	 */
	public void validate_billing_packages(Response billingResponse, BillingPackage billingPackage, String rootNode)
			throws Exception {
		JsonPath billingPackages = new JsonPath(billingResponse.asString());
		billingPackages.setRoot(rootNode);
		Map<?, ?> billingPack = billingPackages.get(getFindQuery(billingPackage.getProductId()));
		Assert.assertEquals(billingPackage.getProductName(), billingPack.get("productName"));
		Assert.assertEquals(billingPackage.getPartnerId(), billingPack.get("partnerId"));
		Assert.assertEquals(billingPackage.getBillingCode(), billingPack.get("billingCode"));
		Assert.assertEquals(fieldCount, billingPack.size());
	}

	/**
	 * @param billingResponse
	 * @param billingPackage
	 * @throws Exception
	 */
	public void validate_billing_package(Response billingResponse, BillingPackage billingPackage) throws Exception {
		Assert.assertEquals(billingResponse.getBody().jsonPath().getInt("billingPackage.productId"),
				billingPackage.getProductId());
		Assert.assertEquals(billingResponse.getBody().jsonPath().getString("billingPackage.productName"),
				billingPackage.getProductName());
		Assert.assertEquals(billingResponse.getBody().jsonPath().getInt("billingPackage.partnerId"),
				billingPackage.getPartnerId());
		Assert.assertEquals(billingResponse.getBody().jsonPath().getString("billingPackage.billingCode"),
				billingPackage.getBillingCode());
	}

	private String getFindQuery(int productId) throws ExecutionException {
		String jsonQuery = "find {e -> e.productId == " + productId + "}";
		return jsonQuery;
	}
}
