package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerRequestVO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author mayank.bharshiv
 */
public class CSHelper {
	private static Logger logger = Log4J.getLogger("SavePartnerTests");
	static RequestSpecification requestSpecification;

	/**
	 * Default constructor. Initializes the request template.
	 */
	public static RequestSpecification getRequestSpecification() {
		if (requestSpecification == null) {
			requestSpecification = with().baseUri(Configuration.csServer);
		}
		return requestSpecification;
	}

	/**
	 * @param publishConfigRequest
	 * @return response
	 * @throws Exception
	 */
	public static Response savePartner(PartnerRequestVO savePartner) throws Exception {
		logger.info("Save Partner Api Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(savePartner).log()
				.all().post("/partner");
		response.prettyPrint();
		return response;
	}

	public static Response updatePartner(PartnerRequestVO savePartner) throws Exception {
		logger.info("Update Partner Api Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(savePartner).log()
				.all().put("/partner");
		response.prettyPrint();
		return response;
	}
	
	public static Response getPartnerByName(String partnerName) throws Exception {
		logger.info("Get Partner By Name Api Called");
		final Response response = given(getRequestSpecification()).pathParam("partnerName", partnerName).contentType(ContentType.JSON).log()
				.all().get("/partner/{partnerName}");
		response.prettyPrint();
		return response;
	}
}
