package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author mayank.bharshiv
 *
 */
public class SASHelper {
	RequestSpecification requestSpecification;

	final String saveProductResource = "/saveProduct";
	final String userSubscriptionResource = "/event/userSubcription";
	final String schedulerResource = "/scheduler";

	/**
	 * Default constructor. Initializes the request template.
	 */
	public SASHelper() {

		this.requestSpecification = with().baseUri(Configuration.sasServer);
	}

	/**
	 * 
	 * @param publishConfigRequest
	 * @return response
	 * @throws Exception
	 */
	public Response saveProduct(
			PublishConfigRequest publishConfigRequest) throws Exception {

		final Response response = given(requestSpecification).contentType(ContentType.JSON)
				.body(publishConfigRequest).log().all().post(saveProductResource);
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
		final Response response = given(requestSpecification).contentType(ContentType.JSON)
				.body(schedulerRequest).log().all().post(schedulerResource);
		response.prettyPrint();
		return response;
	}

	public void cleanTestData(String product_id) {
		DBUtils.cleanTable("job_config", product_id);
		DBUtils.cleanTable("activation", product_id);
		DBUtils.cleanTable("churn", product_id);
		DBUtils.cleanTable("content_sms_user_info", product_id);
		DBUtils.cleanTable("deactivation", product_id);
		DBUtils.cleanTable("engagement_sms_user_info", product_id);
		DBUtils.cleanTable("free_trail", product_id);
		DBUtils.cleanTable("optout_sms_user_info", product_id);
		DBUtils.cleanTable("prerenewal_sms_user_info", product_id);
		DBUtils.cleanTable("product_partner_country_config", product_id);
		DBUtils.cleanTable("renewal", product_id);
		DBUtils.cleanTable("renewal_retry", product_id);
		DBUtils.cleanTable("winback", product_id);
	}
}
