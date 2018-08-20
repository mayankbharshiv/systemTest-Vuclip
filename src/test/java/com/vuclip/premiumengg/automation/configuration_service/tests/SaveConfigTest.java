package com.vuclip.premiumengg.automation.configuration_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.*;
import com.vuclip.premiumengg.automation.configuration_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SaveConfigTest {

    private static Logger logger = Log4J.getLogger("SaveConfigTest");
    private static ConfigRequestVO request;
    private static ConfigResponseVO expectedResponse;
    private static ConfigResponseVO updateConfig;
    private static ConfigVO config;
    private static String productName;
    private static int productId;
    private static String adNetworkName = "Ad Network 111";

    @BeforeClass(alwaysRun = true)
    public void setUP() throws Exception {

        logger.info("cleaning record for SaveCountryTest");
        int updateCountCountry = DBUtils.cleanTable("country",
                "country_name = " + CSDBHelper.dbReadableFormat(SaveCountryTest.countryName));
        logger.info("cleaned " + updateCountCountry + " record");

        logger.info("cleaning record for AdNetwork");
        int updateCountAdNetwork = DBUtils.cleanTable("ad_network",
                "name = " + CSDBHelper.dbReadableFormat(adNetworkName));
        logger.info("cleaned " + updateCountAdNetwork + " record");

        logger.info("cleaning record for Partner");
        int updateCountPartner = DBUtils.cleanTable("partner",
                "partner_name = " + CSDBHelper.dbReadableFormat(SavePartnerTests.partneName));
        logger.info("cleaned " + updateCountPartner + " record");

        logger.info("cleaning record for Conifg Tables ");
        List<String> tables = Stream.of(DeleteConfigTables.values()).map(Enum::name).collect(Collectors.toList());
        for (String tableName : tables) {
            DBUtils.cleanTable(tableName, null);
        }

        logger.info("Save Partner");
        PartnerRequestVO request = PartnerUtil.createMockRequestVO(SavePartnerTests.partneName,
                SavePartnerTests.partneName, "ACTIVE", false, "telco", true, "user1", true, "Vuclip", "Vuclip",
                "Vuclip", true, true, "Vuclip URL", "Vuclip URL", "Vuclip URL", "Vuclip URL");
        Response response = PartnerHelper.savePartner(request);
        CSValidationHelper.validate_cs_api_response(response);

        logger.info("Save Country");
        CountryRequestVO requestCountry = CountryUtil.createMockRequestVO(SaveCountryTest.countryName, "IN", "GMT",
                "INR");
        Response responseCountry = CountryHelper.saveCountry(requestCountry);
        CSValidationHelper.validate_cs_api_response(responseCountry);

        logger.info("Save Ad Network");
        AdNetworkRequestVO requestAdNetwork = AdNetworkUtil.createMockRequestVO(adNetworkName, 1,
                "voluum_tid", "notification url", "GET", "ALL", "active", "churn notification url", "dummy");
        Response responseAdNetwork = AdNetworkHelper.saveAdNetwork(requestAdNetwork);
        CSValidationHelper.validate_cs_api_response(responseAdNetwork);
    }

    @Test(groups = {"positive"})
    public void saveConifgTests() throws Exception {
        logger.info("Starting ======> Verify config saved in Database");

        try {
            request = ConfigUtil.createMockRequestVO("VIU", "OTT", "SUBSCRIPTION_STORE", "www.viu.com", "contextName",
                    123L, true, 30, "www.viu.com", "www.viu.com", "www.viu.com", "VIU Product", "active",
                    "Etisalat UAE", true, true, true, "Parser Endpoint", "Url Generation Endpoint", "dd-mm-yyyy",
                    "India", "BC01", 100.0, 30, 99999, "1234", 1, 1, 1, ItemTypeId.PRODUCT, true, "Price Point 1",
                    false, "BC01", false, "BC01", false, 60, true, "active", true, 10, 0, 10, 10, 10, adNetworkName,
                    10, 20, 30, "7:30-10:30,23:00-5:00", "ALL", "24", "ACTIVATION_RETRY", 10, 120, "7:00AM-2:00PM",
                    "CALANDER_DAY", 100, 200, 100, 30, 10, 12, 10, 10, "CONTENT_SMS", "Redirection Context", 1, 100,
                    100, true, "active",
                    Arrays.asList(CriteriaRequestVO.builder().smsText("SMS Text")
                            .criterions(Arrays.asList(CriterionRequestVO.builder()
                                    .name(CriterionName.fromValue("activity")).operator(CriterionOperator.EQUAL)
                                    .value("Activation").groupingOperator(GroupingOperator.fromValue("NONE")).build()))
                            .dateCoumputationCriterion(DateCoumputationCriterionRequestVO.builder()
                                    .name(DateCoumputationCriterionName.fromValue("next_billing_date"))
                                    .operator(DateCoumputationCriterionOperator.PLUS).value("24")
                                    .unit(TimeUnit.fromValue("HOUR")).build())
                            .build()));

            config = ConfigUtil.getMockConfigVO(request);

            expectedResponse = new ConfigResponseVO(true, 200, CSUtils.uBSMockURL + " : Successful\n", config);
            logger.info("Expected Response:" + expectedResponse.toString());

            Response response = ConfigHelper.saveConfig(request);
            CSValidationHelper.validate_cs_api_response(response);

            logger.info("Validate Resposne Body");
            updateConfig = response.as(ConfigResponseVO.class);
            productId = updateConfig.getConfig().getProduct().getProductId();
            productName = updateConfig.getConfig().getProduct().getProductName();
            logger.info("Actual Resposne:" + updateConfig.toString());

            ConfigValidationHelper.validateResponse(updateConfig, expectedResponse);

            logger.info("Validate Database");
            ConfigValidationHelper.validateDataInDB(updateConfig, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveConifgTests"}, priority = 2)
    public void updateConfigTests() throws Exception {
        logger.info("Starting ======> Verify config updated in Database");

        try {

            ConfigUtil.createUpdateConfigData(updateConfig);

            expectedResponse = new ConfigResponseVO(true, 200, CSUtils.uBSMockURL + " : Successful\n",
                    updateConfig.getConfig());
            logger.info("Expected Response:" + expectedResponse.toString());

            Response response = ConfigHelper.updateConfig(updateConfig.getConfig());
            CSValidationHelper.validate_cs_api_response(response);

            logger.info("Validate Resposne Body");
            ConfigResponseVO actualResposne = response.as(ConfigResponseVO.class);
            productId = actualResposne.getConfig().getProduct().getProductId();
            productName = actualResposne.getConfig().getProduct().getProductName();
            logger.info("Actual Resposne:" + actualResposne.toString());

            ConfigValidationHelper.validateUpdateResponse(actualResposne, expectedResponse);

            logger.info("Validate Database");
            ConfigValidationHelper.validateDataInDB(updateConfig, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveConifgTests"}, priority = 3)
    public void getConfigByProductName() throws Exception {
        logger.info("Starting ======>  Get Config By productName");

        try {

            expectedResponse = new ConfigResponseVO(true, 200, "Success", updateConfig.getConfig());
            logger.info("Expected Response:" + expectedResponse.toString());

            Response response = ConfigHelper.getConfigByProductName(productName);
            CSValidationHelper.validate_cs_api_response(response);

            logger.info("Validate Resposne Body");
            ConfigResponseVO actualResposne = response.as(ConfigResponseVO.class);
            logger.info("Actual Resposne:" + actualResposne.toString());
            ConfigValidationHelper.validateResponse(actualResposne, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveConifgTests"}, priority = 4)
    public void getConfigByProductId() throws Exception {
        logger.info("Starting ======>  Get Config By productName");

        try {

            expectedResponse = new ConfigResponseVO(true, 200, "Success", updateConfig.getConfig());
            logger.info("Expected Response:" + expectedResponse.toString());

            Response response = ConfigHelper.getConfigByProductId(productId);
            CSValidationHelper.validate_cs_api_response(response);

            logger.info("Validate Resposne Body");
            ConfigResponseVO actualResposne = response.as(ConfigResponseVO.class);
            logger.info("Actual Resposne:" + actualResposne.toString());
            ConfigValidationHelper.validateResponse(actualResposne, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"saveConifgTests"}, priority = 5)
    public void deleteByProductName() throws Exception {
        logger.info("Starting ======> Delete By productName");

        try {

            expectedResponse = new ConfigResponseVO(true, 200, CSUtils.uBSMockURL + " : Successful\n",
                    updateConfig.getConfig());
            logger.info("Expected Response:" + expectedResponse.toString());

            Response response = ConfigHelper.deleteConfigByName(productName);
            CSValidationHelper.validate_cs_api_response(response);

            logger.info("Validate Database for Deletion");
            List<String> tables = Stream.of(ConfigTables.values()).map(Enum::name).collect(Collectors.toList());
            for (String tableName : tables) {
                CSDBHelper.verifyNoActivityRecordPresent(tableName, null);
            }

            logger.info("Validate Database");
            AppAssert.assertEqual(
                    DBUtils.getRecord("config_details", "request_data= " + CSDBHelper.dbReadableFormat(productName))
                            .get(0).get("request_type").toString(),
                    "Delete", "Verify Databse is updated for Delete Request");

            logger.info("Validate Resposne Body");
            ConfigResponseVO actualResposne = response.as(ConfigResponseVO.class);
            logger.info("Actual Resposne:" + actualResposne.toString());
            ConfigValidationHelper.validateResponse(actualResposne, expectedResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
