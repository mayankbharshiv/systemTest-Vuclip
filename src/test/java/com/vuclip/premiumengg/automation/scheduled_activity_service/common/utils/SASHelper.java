package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author mayank.bharshiv
 */
public class SASHelper {
    final String saveProductResource = "/saveProduct";
    final String userSubscriptionResource = "/event/userSubcription";
    final String schedulerResource = "/schedular";
    RequestSpecification requestSpecification;

    /**
     * Default constructor. Initializes the request template.
     */
    public SASHelper() {

        this.requestSpecification = with().baseUri(Configuration.sasServer);
    }

    /**
     * @param publishConfigRequest
     * @return response
     * @throws Exception
     */
    public Response saveProduct(PublishConfigRequest publishConfigRequest) throws Exception {

        final Response response = given(requestSpecification).contentType(ContentType.JSON).body(publishConfigRequest)
                .log().all().post(saveProductResource);
        response.prettyPrint();
        return response;
    }

    /**
     * @param publishConfigRequest
     * @return response
     * @throws Exception
     */
    public Response saveProduct(String publishConfigRequest) throws Exception {

        final Response response = given(requestSpecification).contentType(ContentType.JSON).body(publishConfigRequest)
                .post(saveProductResource);
        response.prettyPrint();
        return response;
    }

    /**
     * @param userSubscriptionRequest
     * @return
     * @throws Exception
     */
    public Response userSubscription(UserSubscriptionRequest userSubscriptionRequest) throws Exception {
        final Response response = given(requestSpecification).contentType(ContentType.JSON)
                .body(userSubscriptionRequest).log().all().post(userSubscriptionResource);
        response.prettyPrint();
        return response;
    }

    /**
     * @param schedulerRequest
     * @return
     * @throws Exception
     */
    public Response scheduler(SchedulerRequest schedulerRequest) throws Exception {
        if (schedulerRequest.getActivityType() != null) {
            if (schedulerRequest.getActivityType().equalsIgnoreCase("ACTIVATION"))
                schedulerRequest.setActivityType("ACTIVATION_RETRY");
            if (schedulerRequest.getActivityType().equalsIgnoreCase("DEACTIVATION"))
                schedulerRequest.setActivityType("DEACTIVATION_RETRY");
        }
        final Response response = given(requestSpecification).contentType(ContentType.JSON).body(schedulerRequest).log()
                .all().post(schedulerResource);
        response.prettyPrint();
        return response;
    }

    public Response scheduler(String jsonstring) throws Exception {

        final Response response = given(requestSpecification).contentType(ContentType.JSON).body(jsonstring).log().all()
                .post(schedulerResource);
        response.prettyPrint();
        return response;
    }

}
