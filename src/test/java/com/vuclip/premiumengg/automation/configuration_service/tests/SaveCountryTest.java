package com.vuclip.premiumengg.automation.configuration_service.tests;

import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CountryResponseVO;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CSDBHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CSUtils;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CSValidationHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CountryDBHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CountryHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CountryUtil;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CountryValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.response.Response;

public class SaveCountryTest {

	private static Logger logger = Log4J.getLogger("SaveCountryTest");
	private static CountryRequestVO request;
	private static CountryResponseVO expectedResponse;
	public static String countryName = "India";
	private static int countryId;

	@BeforeClass(alwaysRun = true)
	public void clean() {
		logger.info("cleaning record for SaveCountryTest");
		int updateCount = DBUtils.cleanTable("country", "country_name = " + CSDBHelper.dbReadableFormat(countryName));
		logger.info("cleaned" + updateCount + " record");

	}

	@Test(groups = { "positive" })
	public void saveCountryTests() throws Exception {
		logger.info("Starting ======> Verify country saved in Database");

		try {
			request = CountryUtil.createMockRequestVO(countryName, "IN", "GMT", "INR");
			CountryResponseVO expectedResponse = CountryUtil.getMockCountryVO(request, "Create", true, 200,
					CSUtils.uBSMockURL + " : Successful\n");

			Response response = CountryHelper.saveCountry(request);
			CSValidationHelper.validate_cs_api_response(response);

			CountryResponseVO countryResponseVO = response.as(CountryResponseVO.class);
			countryId = countryResponseVO.getCountry().getCountryId();

			CountryValidationHelper.validateResponse(countryResponseVO, expectedResponse);

			logger.info(countryResponseVO.toString());
			logger.info(expectedResponse.toString());

			Map<String, Object> dbRecord = DBUtils.getRecord("country", " country_name='" + countryName + "'").get(0);
			CountryValidationHelper.validateTableRecord(dbRecord, expectedResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "saveCountryTests" }, priority = 2)
	public void updateCountryTestInDatabase() throws Exception {
		logger.info("Starting ======> Verify Country updated in Database");

		try {

			// CountryDBHelper.insertRecordIntoCountry("Etisalat USA", 110);

			request = CountryUtil.createMockRequestVO(countryName, "IN", "GMT", "INR");

			expectedResponse = CountryUtil.getMockCountryVO(request, "Update", true, 200,
					CSUtils.uBSMockURL + " : Successful\n");
			logger.info(expectedResponse.toString());

			Response response = CountryHelper.updateCountry(request);
			CSValidationHelper.validate_cs_api_response(response);

			CountryResponseVO actualResponseVO = response.getBody().as(CountryResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			CountryValidationHelper.validateResponse(actualResponseVO, expectedResponse);

			logger.info("DB Validation");

			Map<String, Object> dbRecord = DBUtils.getRecord("country", " country_name='" + countryName + "'").get(0);
			CountryValidationHelper.validateTableRecord(dbRecord, expectedResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "saveCountryTests" }, priority = 3)
	public void getCountryByName() throws Exception {
		logger.info("Starting ======> Verify Get Country By Name");

		try {
			// CountryDBHelper.insertRecordIntoCountry("get Country by name", 111);

			expectedResponse = CountryUtil.getMockCountryVO(request, "Update", true, 200, "Success");
			logger.info(expectedResponse.toString());

			Response response = CountryHelper.getCountryByName(countryName);
			CSValidationHelper.validate_cs_api_response(response);

			CountryResponseVO actualResponseVO = response.getBody().as(CountryResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			CountryValidationHelper.validateResponse(actualResponseVO, expectedResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "saveCountryTests" }, priority = 4)
	public void getCountryById() throws Exception {
		logger.info("Starting ======> Verify Get Country By ID");

		try {
			// CountryDBHelper.insertRecordIntoCountry("get Country by id", 112);

			expectedResponse = CountryUtil.getMockCountryVO(request, "Update", true, 200, "Success");
			logger.info(expectedResponse.toString());

			Response response = CountryHelper.getCountryById(countryId);
			CSValidationHelper.validate_cs_api_response(response);

			CountryResponseVO actualResponseVO = response.getBody().as(CountryResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			CountryValidationHelper.validateResponse(actualResponseVO, expectedResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "saveCountryTests" }, priority = 5)
	public void getAllCountry() throws Exception {
		logger.info("Starting ======> Verify Get All Countrys");

		try {
			// CountryDBHelper.insertRecordIntoCountry("get all", 113);

			Response response = CountryHelper.getAllCountry();
			CSValidationHelper.validate_cs_api_response(response);

			logger.info("Verify size of country in database and response");
			int sizeOfCountryinDB = CSDBHelper.getNumberOfRecordsInTable("country", null);
			response.then().assertThat().body("size()", is(sizeOfCountryinDB));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "saveCountryTests" }, priority = 6)
	public void deleteCountryByName() throws Exception {
		logger.info("Starting ======> Verify Delete Country By Name");

		try {
			// CountryDBHelper.insertRecordIntoCountry("delete Country", 114);

			expectedResponse = CountryUtil.getMockCountryVO(request, "Delete", true, 200, CSUtils.uBSMockURL + " : Successful\n");
			logger.info(expectedResponse.toString());

			Response response = CountryHelper.deleteCountryByName(countryName);
			CSValidationHelper.validate_cs_api_response(response);
			
			logger.info("DB Validation");
			CountryDBHelper.verifyNoActivityRecordPresent("country", CSDBHelper.dbReadableFormat(countryName));

			CountryResponseVO actualResponseVO = response.getBody().as(CountryResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			CountryValidationHelper.validateResponse(actualResponseVO, expectedResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
