package com.vuclip.premiumengg.automation.helpers;

import com.vuclip.premiumengg.automation.common.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * Created by Kohitij_Das on 20/03/18.
 */
public class BillingPackageServiceHelper {

    RequestSpecification requestSpecification;

    /**
     * ax Default constructor. Initialises the request template.
     */
    public BillingPackageServiceHelper() {
        requestSpecification = with().baseUri(Configuration.billingPackageServer);
    }

    /**
     * @return
     * @throws Exception
     */
    public Response performGetAllBillingOptions() throws Exception {
        final Response response = given(requestSpecification)
                .contentType(ContentType.JSON)
                .log().all()
                .get("/bps/api/getAllBillingOptions");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        return response;
    }
}
