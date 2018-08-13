package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ConfigRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ConfigVO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author mayank.bharshiv
 */
public class ConfigHelper {
	private static Logger logger = Log4J.getLogger("ConfigHelper");
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
	public static Response saveConfig(ConfigRequestVO saveConfig) throws Exception {
		logger.info("Save Config Api Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(saveConfig).log()
				.all().post("/config");
		response.prettyPrint();
		return response;
	}

	public static Response updateConfig(ConfigVO configVO) throws Exception {
		logger.info("Update Config Api Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(configVO).log()
				.all().put("/config");
		response.prettyPrint();
		return response;
	}

	public static Response getConfigByProductName(String productName) throws Exception {
		logger.info("Get Config By Name Api Called");
		final Response response = given(getRequestSpecification()).pathParam("productName", productName)
				.contentType(ContentType.JSON).log().all().get("/config/{productName}");
		response.prettyPrint();
		return response;
	}

	public static Response getConfigByProductId(int ConfigId) throws Exception {
		logger.info("Get Config By ID Api Called");
		final Response response = given(getRequestSpecification()).queryParam("productId", ConfigId)
				.contentType(ContentType.JSON).log().all().get("/config");
		response.prettyPrint();
		return response;
	}

	public static Response deleteConfigByName(String productName) throws Exception {
		logger.info("Delete Config By Name Api Called");
		final Response response = given(getRequestSpecification()).queryParam("productName", productName)
				.contentType(ContentType.JSON).log().all().delete("/config");
		response.prettyPrint();
		return response;
	}
}
