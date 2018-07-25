package com.vuclip.premiumengg.automation.ad_network_service.common.utils;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveAdnetwork;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveCountry;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveProduct;
import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.Log4J;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class ANSHelper {

    private static RequestSpecification requestSpecification;

    public static RequestSpecification getRequestSpecification() {
        if (requestSpecification == null) {
            requestSpecification = with().baseUri(Configuration.adNetworkServiceServer);
        }
        return requestSpecification;
    }

    /**
     * @param productId
     * @param productName
     * @param partnerId
     * @param adNetworkId
     * @param billingCode
     * @param countryCode
     * @param paidPercentage
     * @param freePercentage
     * @param winbackPercentage
     * @param freeTrialApplicable
     * @param parkingPeriod
     * @return
     * @throws Exception
     */
/*	public static Response saveProduct(Integer productId, String productName, Integer partnerId, Integer adNetworkId,
			String billingCode, String countryCode, Integer paidPercentage, Integer freePercentage,
			Integer winbackPercentage, Boolean freeTrialApplicable, Integer parkingPeriod) throws Exception {
		Log4J.getLogger().info("Save Product API Called");
		SaveProduct saveProductRequest = ANSUtils.generateSaveProductConfig(productId, productName, partnerId,
				adNetworkId, billingCode, countryCode, paidPercentage, freePercentage, winbackPercentage,
				freeTrialApplicable, parkingPeriod);

		return adNetworkRestcall(saveProductRequest, "/saveProduct");
	}

	public static Response saveProduct(Integer productId, String productName, Integer partnerId, Integer adNetworkId,
			String billingCode) throws Exception {

		return saveProduct(productId, productName, partnerId, adNetworkId, billingCode, ANSTestContext.countryCode,
				ANSTestContext.paidPercentage, ANSTestContext.freePercentage, ANSTestContext.winbackPercentage,
				ANSTestContext.freeTrialApplicable, ANSTestContext.parkingPeriod);
	}*/
    public static Response saveProduct(SaveProduct saveProductRequest) throws Exception {

        return adNetworkRestcall(saveProductRequest, "/saveProduct");

    }

    /**
     * @param countryCode
     * @param timezone
     * @return
     * @throws Exception
     */
    public static Response saveCountry(String countryCode, String timezone) throws Exception {
        Log4J.getLogger().info("Save Country API Called");
        SaveCountry saveCountryRequest = ANSUtils.generateSaveCountryrRequest(countryCode, timezone);
        return adNetworkRestcall(saveCountryRequest, "/saveCountry");
    }

    /**
     * @param jsonBody AdNetwork request JSON
     * @return
     * @throws Exception
     */
    public static Response saveAdNetwork(Integer adNetworkId, String requestParamName, String sourceIdentifer) throws Exception {
        Log4J.getLogger().info("Save AD Network API Called");
        SaveAdnetwork saveAdNetworkRequest = ANSUtils.generateSaveAdnetworkRequest(adNetworkId, requestParamName, sourceIdentifer);
        return adNetworkRestcall(saveAdNetworkRequest, "/saveAdNetwork");
    }

    private static Response adNetworkRestcall(Object obj, String endpoint) {
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(obj).log().all()
                .post(endpoint);
        response.prettyPrint();
        return response;
    }

}
