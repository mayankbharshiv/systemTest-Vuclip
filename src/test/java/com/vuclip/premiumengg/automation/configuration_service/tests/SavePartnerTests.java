package com.vuclip.premiumengg.automation.configuration_service.tests;

import static org.hamcrest.CoreMatchers.is;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerResponseVO;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CSDBHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CSUtils;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.CSValidationHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.PartnerDBHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.PartnerHelper;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.PartnerUtil;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.PartnerValidationHelper;
import com.vuclip.premiumengg.automation.utils.DBUtils;

import io.restassured.response.Response;

public class SavePartnerTests {

	private static Logger logger = Log4J.getLogger("SavePartnerTests");
	private static PartnerRequestVO request;
	private static PartnerResponseVO expectedResponse;
	public static String partneName = "Etisalat UAE";
	private static int partnerId;

	

	  @BeforeClass(alwaysRun = true) public void cleanUp() {
	  DBUtils.cleanTable("partner", "partner_name = " + CSDBHelper.dbReadableFormat(partneName));
	  
	  }
	
	@Test(groups = { "positive" }, priority = 1, enabled = true)
	public void savePartnerTestInDatabase() throws Exception {
		logger.info("Starting ======> Verify Partner saved in Database");

		try {
			request = PartnerUtil.createMockRequestVO(partneName, partneName, "ACTIVE", false, "telco",
					true, "user1", true, "Vuclip", "Vuclip", "Vuclip", true, true, "Vuclip URL", "Vuclip URL",
					"Vuclip URL", "Vuclip URL");

			expectedResponse = PartnerUtil.getMockPartnerVO(request, "Create", true, 200,
					CSUtils.uBSMockURL + " : Successful\n");
			logger.info(expectedResponse.toString());

			Response response = PartnerHelper.savePartner(request);
			CSValidationHelper.validate_cs_api_response(response);

			PartnerResponseVO actualResponseVO = response.getBody().as(PartnerResponseVO.class);

			partnerId = actualResponseVO.getPartner().getPartnerId();
			partneName = actualResponseVO.getPartner().getPartnerName();
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			PartnerValidationHelper.validateResponse(actualResponseVO, expectedResponse);

			logger.info("DB Validation");
			PartnerValidationHelper.validatePartnerInDB(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "savePartnerTestInDatabase" }, priority = 2)
	public void updatePartnerTestInDatabase() throws Exception {
		logger.info("Starting ======> Verify Partner updated in Database");

		try {

			// PartnerDBHelper.insertRecordIntoPartner("Etisalat USA", 110);

			request = PartnerUtil.createMockRequestVO(partneName, partneName, "ACTIVE", false, "non telco",
					false, "user4", true, "AFOUR", "AFOUR", "AFOUR", false, false, "AFOUR URL", "AFOUR URL",
					"AFOUR URL", "AFOUR URL");

			expectedResponse = PartnerUtil.getMockPartnerVO(request, "Update", true, 200,
					CSUtils.uBSMockURL + " : Successful\n");
			logger.info(expectedResponse.toString());

			Response response = PartnerHelper.updatePartner(request);
			CSValidationHelper.validate_cs_api_response(response);

			PartnerResponseVO actualResponseVO = response.getBody().as(PartnerResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			PartnerValidationHelper.validateResponse(actualResponseVO, expectedResponse);

			logger.info("DB Validation");
			PartnerValidationHelper.validatePartnerInDB(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "savePartnerTestInDatabase" }, priority = 3)
	public void getPartnerByName() throws Exception {
		logger.info("Starting ======> Verify Get Partner By Name");

		try {
			// PartnerDBHelper.insertRecordIntoPartner("get partner by name", 111);

			expectedResponse = PartnerUtil.getMockPartnerVO(request, "Update", true, 200, "Success");
			logger.info(expectedResponse.toString());

			Response response = PartnerHelper.getPartnerByName(partneName);
			CSValidationHelper.validate_cs_api_response(response);

			PartnerResponseVO actualResponseVO = response.getBody().as(PartnerResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			PartnerValidationHelper.validateResponse(actualResponseVO, expectedResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "savePartnerTestInDatabase" })
	public void getPartnerById() throws Exception {
		logger.info("Starting ======> Verify Get Partner By ID");

		try {
			// PartnerDBHelper.insertRecordIntoPartner("get partner by id", 112);

			expectedResponse = PartnerUtil.getMockPartnerVO(request, "Update", true, 200, "Success");
			logger.info(expectedResponse.toString());

			Response response = PartnerHelper.getPartnerById(partnerId);
			CSValidationHelper.validate_cs_api_response(response);

			PartnerResponseVO actualResponseVO = response.getBody().as(PartnerResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			PartnerValidationHelper.validateResponse(actualResponseVO, expectedResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "savePartnerTestInDatabase" }, priority = 5)
	public void getAllPartner() throws Exception {
		logger.info("Starting ======> Verify Get All Partners");

		try {
			// PartnerDBHelper.insertRecordIntoPartner("get all", 113);
			Response response = PartnerHelper.getAllPartner();
			CSValidationHelper.validate_cs_api_response(response);

			logger.info("Verify size of partner in database and response");
			int sizeOfPartnerinDB = CSDBHelper.getNumberOfRecordsInTable("partner", null);
			response.then().assertThat().body("size()", is(sizeOfPartnerinDB));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(groups = { "positive" }, dependsOnMethods = { "savePartnerTestInDatabase" }, priority = 6)
	public void deletePartnerByName() throws Exception {
		logger.info("Starting ======> Verify Delete Partner By Name");

		try {
			// PartnerDBHelper.insertRecordIntoPartner("delete partner", 114);
			
			expectedResponse = PartnerUtil.getMockPartnerVO(request, "Delete", true, 200, CSUtils.uBSMockURL + " : Successful\n");
			logger.info(expectedResponse.toString());

			Response response = PartnerHelper.deletePartnerByName(partneName);
			CSValidationHelper.validate_cs_api_response(response);
			PartnerDBHelper.verifyNoActivityRecordPresent("partner", CSDBHelper.dbReadableFormat(partneName));

			PartnerResponseVO actualResponseVO = response.getBody().as(PartnerResponseVO.class);
			logger.info(actualResponseVO.toString());

			logger.info("Response Validation");
			PartnerValidationHelper.validateResponse(actualResponseVO, expectedResponse);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
