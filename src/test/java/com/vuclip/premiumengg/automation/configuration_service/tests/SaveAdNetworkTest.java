package com.vuclip.premiumengg.automation.configuration_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkResponseVO;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

public class SaveAdNetworkTest {

    public static String adNetworkName = "Ad Network 1";
    private static Logger logger = Log4J.getLogger("SaveCountryTest");
    private static AdNetworkRequestVO request;
    private static AdNetworkResponseVO expectedResponse;
    private static int adNetworkId;

    @BeforeClass(alwaysRun = true)
    public void clean() {
        logger.info("cleaning record for AdNetwork");
        int updateCount = DBUtils.cleanTable("ad_network", "name = " + CSDBHelper.dbReadableFormat(adNetworkName));
        logger.info("cleaned" + updateCount + " record");

    }

    @Test(groups = {"positive"})
    public void saveAdNetworkTests() throws Exception {
        logger.info("Starting ======> Verify ad network saved in Database");

        try {
            request = AdNetworkUtil.createMockRequestVO(adNetworkName, 1, "voluum_tid", "notification url", "GET",
                    "ALL", "active", "churn notification url", "dummy");
            expectedResponse = AdNetworkUtil.getMockAdNetworkResponseVO(request, "Create", true, 200,
                    CSUtils.uBSMockURL + " : Successful\n");
            logger.info(expectedResponse.toString());

            Response response = AdNetworkHelper.saveAdNetwork(request);
            CSValidationHelper.validate_cs_api_response(response);

            logger.info("Validate Resposne Body");
            AdNetworkResponseVO adNetworkResponseVO = response.as(AdNetworkResponseVO.class);
            logger.info(adNetworkResponseVO.toString());
            adNetworkId = adNetworkResponseVO.getAdNetwork().getAdNetworkId();
            AdNetworkValidationHelper.validateResponse(adNetworkResponseVO, expectedResponse);

            logger.info("Validate Database");
            Map<String, Object> dbRecord = DBUtils
                    .getRecord("ad_network", "name = " + CSDBHelper.dbReadableFormat(adNetworkName)).get(0);
            AdNetworkValidationHelper.validateTableRecord(dbRecord, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveAdNetworkTests"}, priority = 2)
    public void updatePartnerTestInDatabase() throws Exception {
        logger.info("Starting ======> Verify AD Network updated in Database");

        try {

            // PartnerDBHelper.insertRecordIntoPartner("Etisalat USA", 110);
            request = AdNetworkUtil.createMockRequestVO(adNetworkName, 1, "voluum_tid", "notification url", "GET",
                    "ALL", "active", "churn notification url", "dummy");

            expectedResponse = AdNetworkUtil.getMockAdNetworkResponseVO(request, "Update", true, 200,
                    CSUtils.uBSMockURL + " : Successful\n");
            logger.info(expectedResponse.toString());

            Response response = AdNetworkHelper.updateAdNetwork(request);
            CSValidationHelper.validate_cs_api_response(response);

            AdNetworkResponseVO actualResponseVO = response.getBody().as(AdNetworkResponseVO.class);
            logger.info(actualResponseVO.toString());

            logger.info("Response Validation");
            AdNetworkValidationHelper.validateResponse(actualResponseVO, expectedResponse);

            logger.info("Validate Database");
            Map<String, Object> dbRecord = DBUtils
                    .getRecord("ad_network", "name = " + CSDBHelper.dbReadableFormat(adNetworkName)).get(0);
            AdNetworkValidationHelper.validateTableRecord(dbRecord, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveAdNetworkTests"}, priority = 3)
    public void getPartnerByName() throws Exception {
        logger.info("Starting ======> Verify Get AD Network By Name");

        try {
            // PartnerDBHelper.insertRecordIntoPartner("get partner by name", 111);

            expectedResponse = AdNetworkUtil.getMockAdNetworkResponseVO(request, "Update", true, 200, "Success");
            logger.info(expectedResponse.toString());

            Response response = AdNetworkHelper.getAdNetworkByName(adNetworkName);
            CSValidationHelper.validate_cs_api_response(response);

            AdNetworkResponseVO actualResponseVO = response.getBody().as(AdNetworkResponseVO.class);
            logger.info(actualResponseVO.toString());

            logger.info("Response Validation");
            AdNetworkValidationHelper.validateResponse(actualResponseVO, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveAdNetworkTests"})
    public void getPartnerById() throws Exception {
        logger.info("Starting ======> Verify Get Ad Network By ID");

        try {
            // PartnerDBHelper.insertRecordIntoPartner("get partner by id", 112);

            expectedResponse = AdNetworkUtil.getMockAdNetworkResponseVO(request, "Update", true, 200, "Success");
            logger.info(expectedResponse.toString());

            Response response = AdNetworkHelper.getAdNetworkById(adNetworkId);
            CSValidationHelper.validate_cs_api_response(response);

            AdNetworkResponseVO actualResponseVO = response.getBody().as(AdNetworkResponseVO.class);
            logger.info(actualResponseVO.toString());

            logger.info("Response Validation");
            AdNetworkValidationHelper.validateResponse(actualResponseVO, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveAdNetworkTests"}, priority = 5)
    public void getAllPartner() throws Exception {
        logger.info("Starting ======> Verify Get All AD Network");

        try {
            // PartnerDBHelper.insertRecordIntoPartner("get all", 113);
            Response response = AdNetworkHelper.getAllAdNetwork();
            CSValidationHelper.validate_cs_api_response(response);

            logger.info("Verif size of partner in database and response");
            int sizeOfAdNetworkInDB = CSDBHelper.getNumberOfRecordsInTable("ad_network", null);
            response.then().assertThat().body("size()", is(sizeOfAdNetworkInDB));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveAdNetworkTests"}, priority = 6)
    public void deleteAdNetworkByName() throws Exception {
        logger.info("Starting ======> Verify Delete AdNetwork By Name");

        try {
            // AdNetworkDBHelper.insertRecordIntoAdNetwork("delete AdNetwork", 114);

            expectedResponse = AdNetworkUtil.getMockAdNetworkResponseVO(request, "Delete", true, 200,
                    CSUtils.uBSMockURL + " : Successful\n");
            logger.info(expectedResponse.toString());

            Response response = AdNetworkHelper.deleteAdNetworkByName(adNetworkName);
            CSValidationHelper.validate_cs_api_response(response);
            CSDBHelper.verifyNoActivityRecordPresent("ad_network",
                    "name = " + CSDBHelper.dbReadableFormat(adNetworkName));

            AdNetworkResponseVO actualResponseVO = response.getBody().as(AdNetworkResponseVO.class);
            logger.info(actualResponseVO.toString());

            logger.info("Response Validation");
            AdNetworkValidationHelper.validateResponse(actualResponseVO, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
