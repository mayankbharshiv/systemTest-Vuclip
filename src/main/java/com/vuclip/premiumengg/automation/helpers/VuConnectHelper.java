package com.vuclip.premiumengg.automation.helpers;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.services.vuconnect.steps.StepVuConnectBilling;
import com.vuclip.premiumengg.automation.services.vuconnect.utils.VuConnectUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class VuConnectHelper {

    RequestSpecification requestSpecification;
    VuConnectUtils utils;

    /**
     * ax Default constructor. Initialises the request template.
     */
    public VuConnectHelper() {
        requestSpecification = with().baseUri(Configuration.vuConnectServer);
        utils = new VuConnectUtils();
    }

    /**
     * @param stepVuConnectBilling
     * @return
     */
    public Response performStepVuConnectBilling(StepVuConnectBilling stepVuConnectBilling) {
        Map<String, Object> urlParams = utils.getVuconnectBillingParameters(stepVuConnectBilling);

        final Response response = given(requestSpecification)
                .contentType(ContentType.XML)
                .queryParams(urlParams)
                .log().all()
                .get("/vuconnect/billing");

        return response;
    }
}