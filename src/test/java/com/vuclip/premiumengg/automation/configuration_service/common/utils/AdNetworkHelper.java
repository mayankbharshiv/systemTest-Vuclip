package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkRequestVO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author mayank.bharshiv
 */
public class AdNetworkHelper {
	private static Logger logger = Log4J.getLogger("CountryHelper");
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

	public static Response saveAdNetwork(AdNetworkRequestVO adNetworkRequestVO) throws Exception {
		logger.info("Save AdNetwork APi Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON)
				.body(adNetworkRequestVO).log().all().post("/adNetwork");
		response.prettyPrint();
		return response;
	}

	public static Response updateAdNetwork(AdNetworkRequestVO saveAdNetwork) throws Exception {
		logger.info("Update AdNetwork Api Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(saveAdNetwork)
				.log().all().put("/adNetwork");
		response.prettyPrint();
		return response;
	}

	public static Response getAdNetworkByName(String AdNetworkName) throws Exception {
		logger.info("Get AdNetwork By Name Api Called");
		final Response response = given(getRequestSpecification()).pathParam("adNetworkName", AdNetworkName)
				.contentType(ContentType.JSON).log().all().get("/adNetwork/{adNetworkName}");
		response.prettyPrint();
		return response;
	}

	public static Response getAdNetworkById(int AdNetworkId) throws Exception {
		logger.info("Get AdNetwork By ID Api Called");
		final Response response = given(getRequestSpecification()).queryParam("adNetworkId", AdNetworkId)
				.contentType(ContentType.JSON).log().all().get("/adNetwork");
		response.prettyPrint();
		return response;
	}

	public static Response getAllAdNetwork() throws Exception {
		logger.info("Get ALL AdNetworks Api Called");
		final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all()
				.get("/adNetwork/adNetworks");
		response.prettyPrint();
		return response;
	}

	public static Response deleteAdNetworkByName(String AdNetworkName) throws Exception {
		logger.info("Delete AdNetwork By Name Api Called");
		final Response response = given(getRequestSpecification()).queryParam("adNetworkName", AdNetworkName)
				.contentType(ContentType.JSON).log().all().delete("/adNetwork");
		response.prettyPrint();
		return response;
	}

}
