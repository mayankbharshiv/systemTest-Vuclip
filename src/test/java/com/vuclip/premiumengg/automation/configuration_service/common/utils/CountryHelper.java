package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryRequestVO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

/**
 * @author mayank.bharshiv
 */
public class CountryHelper {
    static RequestSpecification requestSpecification;
    private static Logger logger = Log4J.getLogger("CountryHelper");

    /**
     * Default constructor. Initializes the request template.
     */
    public static RequestSpecification getRequestSpecification() {
        if (requestSpecification == null) {
            requestSpecification = with().baseUri(Configuration.csServer);
        }
        return requestSpecification;
    }

    public static Response saveCountry(CountryRequestVO countryRequestVO) throws Exception {
        logger.info("Save Country APi Called");
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(countryRequestVO)
                .log().all().post("/country");
        response.prettyPrint();
        return response;
    }

    public static Response updateCountry(CountryRequestVO saveCountry) throws Exception {
        logger.info("Update Country Api Called");
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).body(saveCountry).log()
                .all().put("/country");
        response.prettyPrint();
        return response;
    }

    public static Response getCountryByName(String countryName) throws Exception {
        logger.info("Get Country By Name Api Called");
        final Response response = given(getRequestSpecification()).pathParam("CountryName", countryName)
                .contentType(ContentType.JSON).log().all().get("/country/{CountryName}");
        response.prettyPrint();
        return response;
    }

    public static Response getCountryById(int countryId) throws Exception {
        logger.info("Get Country By ID Api Called");
        final Response response = given(getRequestSpecification()).queryParam("countryId", countryId)
                .contentType(ContentType.JSON).log().all().get("/country");
        response.prettyPrint();
        return response;
    }

    public static Response getAllCountry() throws Exception {
        logger.info("Get ALL Countrys Api Called");
        final Response response = given(getRequestSpecification()).contentType(ContentType.JSON).log().all().get("/country/countries");
        response.prettyPrint();
        return response;
    }

    public static Response deleteCountryByName(String countryName) throws Exception {
        logger.info("Delete Country By Name Api Called");
        final Response response = given(getRequestSpecification()).queryParam("countryName", countryName)
                .contentType(ContentType.JSON).log().all().delete("/country");
        response.prettyPrint();
        return response;
    }


}
