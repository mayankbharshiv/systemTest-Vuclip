package com.vuclip.premiumengg.automation.helpers;

import com.vuclip.premiumengg.automation.common.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author Kohitij_Das
 */
public class TestDataHelper {


    RequestSpecification requestSpecification;

    /**
     *
     */
    public TestDataHelper() {
        requestSpecification = with().baseUri(Configuration.testDataServer).contentType(ContentType.JSON);
    }

    /**
     * @param product
     * @param category
     * @return
     */
    public Response getStepVuConnectBillingData(String product, String category) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("product", product);
        params.put("category", category);

        final Response response = given(requestSpecification)
                .queryParams(params)
                .get("/StepVuConnectBilling");

        response.then().assertThat().statusCode(200);
        return response;
    }

    /**
     * @param msisdn
     * @return
     */
    public Response getValidateBillingResponseData(String msisdn) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msisdn", msisdn);

        final Response response = given(requestSpecification)
                .queryParams(params)
                .get("/ValidateBillingResponse");

        response.then().assertThat().statusCode(200);
        return response;
    }

    public Response getStepVuConnectMOReceiverData(String product, String source) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("product", product);
        params.put("source", source);

        final Response response = given(requestSpecification)
                .queryParams(params)
                .get("/StepMOOmyvsReceiver");

        response.then().assertThat().statusCode(200);
        return response;


    }

    public Response getValidateOmyvsMOReceiverResponce(String msisdn) {

        return getValidateBillingResponseData(msisdn);
    }

    public Response getStepXlcomMOReceiver(String product, String source) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("product", product);
        params.put("source", source);

        final Response response = given(requestSpecification)
                .queryParams(params)
                .get("/StepXlcomMOReceiver");

        response.then().assertThat().statusCode(200);
        return response;

    }

    public Response getStepXlcomNotify(String product, String source) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("product", product);
        params.put("source", source);

        final Response response = given(requestSpecification)
                .queryParams(params)
                .get("/StepXlcomNotify");

        response.then().assertThat().statusCode(200);
        return response;
    }

    public Response getStepCreateBillingMappingEntry(String transactionId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("transactionId", transactionId);

        final Response response = given(requestSpecification)
                .queryParams(params)
                .get("/CreateBillingMappingEntry");

        response.then().assertThat().statusCode(200);
        return response;

    }

    public Response getValidateRabbitMQData(
            String msisdn) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msisdn", msisdn);

        final Response response = given(requestSpecification)
                .queryParams(params)
                .get("/ValidateRabbitMQData");

        response.then().assertThat().statusCode(200);
        return response;

    }


}