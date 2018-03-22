package com.vuclip.premiumengg.automation.helpers;

import com.vuclip.premiumengg.automation.common.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * Created by Kohitij_Das on 20/03/18.
 */
public class BPSHelper {

    RequestSpecification requestSpecification;

    /**
     * ax Default constructor. Initialises the request template.
     */
    public BPSHelper() {
        requestSpecification = with().baseUri(Configuration.billingPackageServer);
    }

    /**
     * @return
     * @throws Exception
     */
    public Response getAllBillingOptions() throws Exception {
        final Response response = given(requestSpecification)
                .contentType(ContentType.JSON)
                .log().all()
                .get("/bps/api/getAllBillingOptions");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        return response;
    }

    /**
     * @param params
     * @return
     * @throws Exception
     */
    public Response getBillingOptionWithFilters(Map<String, Object> params) throws Exception {
        final Response response = given(requestSpecification)
                .contentType(ContentType.JSON)
                .log().all()
                .queryParams(params)
                .get("/bps/api/getBillingOption");
        response.prettyPrint();
        return response;
    }
}
