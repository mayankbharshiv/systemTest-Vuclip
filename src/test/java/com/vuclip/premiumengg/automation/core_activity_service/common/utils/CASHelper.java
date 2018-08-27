package com.vuclip.premiumengg.automation.core_activity_service.common.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ChargedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.DeactivateUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.SavePartnerRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.SaveProductRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnBlockedUserRequestVO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CASHelper {

	static RequestSpecification requestSpecification;
	private static Logger logger = Log4J.getLogger("CASHelper");

	/**
	 * Default constructor. Initializes the request template.
	 */
	public static RequestSpecification getRequestSpecification() {
		if (requestSpecification == null) {
			requestSpecification = with().baseUri(Configuration.casServer);
		}
		return requestSpecification;
	}

	public static Response chargedUser(ChargedUserRequestVO chargedUserRequestVO) throws Exception {
		logger.info("Charged User APi Called");
		Response response = postCall(chargedUserRequestVO, "/rest/billing/chargeUser");
		return response;
	}

	public static Response savePartner(SavePartnerRequestVO savePartner) throws Exception {
		logger.info("Save Partner APi Called");
		Response response = postCall(savePartner, "/savePartner");
		return response;
	}

	public static Response saveProduct(SaveProductRequestVO saveProduct) throws Exception {
		logger.info("Save Product APi Called");
		Response response = postCall(saveProduct, "/saveProduct");
		return response;
	}

	public static Response blocked(BlockedUserRequestVO blockedUserRequestVO) throws Exception {
		logger.info("Block APi Called");
		Response response = postCall(blockedUserRequestVO, "/rest/billing/block");
		return response;

	}

	public static Response unblocked(UnBlockedUserRequestVO unBlockedUserRequestVO) throws Exception {
		logger.info("UnBlock APi Called");
		Response response = postCall(unBlockedUserRequestVO, "/rest/billing/unblock");
		return response;
	}

	public static Response deactivate(DeactivateUserRequestVO deactivateUserRequestVO) throws Exception {
		logger.info("Deactivate APi Called");
		Response response = postCall(deactivateUserRequestVO, "/rest/billing/deactivateUser");
		return response;
	}

	private static Response postCall(Object body, String endpoint) {
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(body).log().all()
				.post(endpoint);
		response.prettyPrint();
		return response;

	}
}
