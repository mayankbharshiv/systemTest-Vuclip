package com.vuclip.premiumengg.automation.vuconnect.tests;

import com.vuclip.premiumengg.automation.helpers.TestDataHelper;
import com.vuclip.premiumengg.automation.helpers.ValidationHelper;
import com.vuclip.premiumengg.automation.helpers.VuConnectHelper;
import com.vuclip.premiumengg.automation.services.vuconnect.common.Product;
import com.vuclip.premiumengg.automation.services.vuconnect.steps.StepVuConnectBilling;
import com.vuclip.premiumengg.automation.services.vuconnect.steps.ValidateBillingResponse;
import com.vuclip.premiumengg.automation.utils.RedisUtil;
import com.vuclip.premiumengg.automation.vuconnect.base.BillingTestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kohitij_Das on 07/06/17.
 */
public class Billing extends BillingTestBase {

    @BeforeClass(alwaysRun = true)
    public void setupBilling() {
        setRedisConnection();
        redisUtil = new RedisUtil();
        testDataHelper = new TestDataHelper();
        vuConnectHelper = new VuConnectHelper();
        validationHelper = new ValidationHelper();
    }

    @DataProvider(name = "successBillingData")
    public Object[][] successBillingData() {
        return new Object[][]{
                {"otvs", "REG_FT_SUCCESS"},
                {"otvs", "REG_SUCCESS_0"},
                {"otvs", "REN_SUCCESS_0"},
                {"otvs", "REN_SUCCESS_108"}
        };
    }

    @Test(dataProvider = "successBillingData")
    public void verify_operator_billing_success(String product, String source) {
        final Response stepVuconnectBillingData = testDataHelper.getStepVuConnectBillingData(product, source);
        StepVuConnectBilling stepVuconnectBillingObj = stepVuconnectBillingData.getBody().as(StepVuConnectBilling.class);

        redisUtil.clearCacheVuconnect(stepVuconnectBillingObj.getMsisdn(), Product.OTVS.getProviderId(), redisConnection);

        Map<String, Boolean> testSteps = new HashMap<String, Boolean>();
        testSteps.put("stepBilling", true);
        testSteps.put("verifyBillingResponse", true);
        testSteps.put("validateNextBillingDate", true);
        testSteps.put("stepVerifyCache", true);
        testSteps.put("verifyDoubleCharging", false);

        Map<String, String> nbdInfo = new HashMap<String, String>();
        nbdInfo.put("type", "success_window_3");

        validateBilling(stepVuconnectBillingObj, testSteps, nbdInfo);
    }

    @DataProvider(name = "doubleChargingData")
    public Object[][] doubleChargingData() {
        return new Object[][]{
                {"otvs", "REG_DOUBLE"}
        };
    }

    @Test(dataProvider = "doubleChargingData")
    public void verify_double_charging(String product, String source) {
        final Response stepVuconnectBillingData = testDataHelper.getStepVuConnectBillingData(product, source);
        StepVuConnectBilling stepVuconnectBillingObj = stepVuconnectBillingData.getBody().as(StepVuConnectBilling.class);

        redisUtil.clearCacheVuconnect(stepVuconnectBillingObj.getMsisdn(), Product.OTVS.getProviderId(), redisConnection);

        Map<String, Boolean> testSteps = new HashMap<String, Boolean>();
        testSteps.put("stepBilling", true);
        testSteps.put("verifyBillingResponse", true);
        testSteps.put("validateNextBillingDate", true);
        testSteps.put("stepVerifyCache", true);
        testSteps.put("verifyDoubleCharging", true);

        Map<String, String> nbdInfo = new HashMap<String, String>();
        nbdInfo.put("type", "success_window_3");

        validateBilling(stepVuconnectBillingObj, testSteps, nbdInfo);
    }

    @DataProvider(name = "lowbalanceBillingData")
    public Object[][] lowbalanceBillingData() {
        return new Object[][]{
                {"otvs", "REG_LB_302"},
                {"otvs", "REN_LB_302"},
        };
    }

    @Test(dataProvider = "lowbalanceBillingData")
    public void verify_operator_billing_lowbalance(String product, String source) {
        final Response stepVuconnectBillingData = testDataHelper.getStepVuConnectBillingData(product, source);
        StepVuConnectBilling stepVuconnectBillingObj = stepVuconnectBillingData.getBody().as(StepVuConnectBilling.class);

        redisUtil.clearCacheVuconnect(stepVuconnectBillingObj.getMsisdn(), Product.OTVS.getProviderId(), redisConnection);

        Map<String, Boolean> testSteps = new HashMap<String, Boolean>();
        testSteps.put("stepBilling", true);
        testSteps.put("verifyBillingResponse", true);
        testSteps.put("validateNextBillingDate", true);
        testSteps.put("stepVerifyCache", false);
        testSteps.put("verifyDoubleCharging", false);

        Map<String, String> nbdInfo = new HashMap<String, String>();
        nbdInfo.put("type", "failure_window_3");

        validateBilling(stepVuconnectBillingObj, testSteps, nbdInfo);
    }

    @DataProvider(name = "failureBillingData")
    public Object[][] failureBillingData() {
        return new Object[][]{
                {"otvs", "REG_FAIL_300"},
                {"otvs", "REN_FAIL_300"}
        };
    }

    @Test(dataProvider = "failureBillingData")
    public void verify_operator_billing_failure(String product, String source) {
        final Response stepVuconnectBillingData = testDataHelper.getStepVuConnectBillingData(product, source);
        StepVuConnectBilling stepVuconnectBillingObj = stepVuconnectBillingData.getBody().as(StepVuConnectBilling.class);

        redisUtil.clearCacheVuconnect(stepVuconnectBillingObj.getMsisdn(), Product.OTVS.getProviderId(), redisConnection);

        Map<String, Boolean> testSteps = new HashMap<String, Boolean>();
        testSteps.put("stepBilling", true);
        testSteps.put("verifyBillingResponse", true);
        testSteps.put("validateNextBillingDate", true);
        testSteps.put("stepVerifyCache", false);
        testSteps.put("verifyDoubleCharging", false);

        Map<String, String> nbdInfo = new HashMap<String, String>();
        nbdInfo.put("type", "failure_window_3");

        validateBilling(stepVuconnectBillingObj, testSteps, nbdInfo);
    }

    @DataProvider(name = "errorBillingData")
    public Object[][] errorBillingData() {
        return new Object[][]{
                {"otvs", "REG_ERROR_900"},
                {"otvs", "REN_ERROR_900"},
        };
    }

    @Test(dataProvider = "errorBillingData")
    public void verify_operator_billing_error(String product, String source) {
        final Response stepVuconnectBillingData = testDataHelper.getStepVuConnectBillingData(product, source);
        StepVuConnectBilling stepVuconnectBillingObj = stepVuconnectBillingData.getBody().as(StepVuConnectBilling.class);

        redisUtil.clearCacheVuconnect(stepVuconnectBillingObj.getMsisdn(), Product.OTVS.getProviderId(), redisConnection);

        Map<String, Boolean> testSteps = new HashMap<String, Boolean>();
        testSteps.put("stepBilling", true);
        testSteps.put("verifyBillingResponse", true);
        testSteps.put("validateNextBillingDate", true);
        testSteps.put("stepVerifyCache", false);
        testSteps.put("verifyDoubleCharging", false);

        Map<String, String> nbdInfo = new HashMap<String, String>();
        nbdInfo.put("type", "failure_window_3");

        validateBilling(stepVuconnectBillingObj, testSteps, nbdInfo);
    }

    /**
     * @param stepVuconnectBillingObj
     * @param testSteps
     */
    private void validateBilling(StepVuConnectBilling stepVuconnectBillingObj,
                                 Map<String, Boolean> testSteps, Map<String, String> nbdInfo) {
        Response billingResponse = null;
        Response doubleChargingResponse;
        try {
            if (testSteps.get("stepBilling")) {
                billingResponse = vuConnectHelper.performStepVuConnectBilling(stepVuconnectBillingObj);
                billingResponse.then().assertThat().statusCode(200);
                billingResponse.print();
            }
            if (testSteps.get("verifyBillingResponse")) {
                final Response validateBillingResponse = testDataHelper.getValidateBillingResponseData(stepVuconnectBillingObj.getMsisdn());
                ValidateBillingResponse validateBillingResponseObj = validateBillingResponse.getBody().as(ValidateBillingResponse.class);

                validationHelper.validate_vuconnect_billing_response(billingResponse, validateBillingResponseObj);

                if (testSteps.get("validateNextBillingDate") != null && testSteps.get("validateNextBillingDate")) {
                    nbdInfo.put("timeZone", Product.OTVS.getTimeZone());
                    nbdInfo.put("timeUnit", Product.OTVS.getTimeUnit());
                    nbdInfo.put("window1", Product.OTVS.getWindow1());
                    nbdInfo.put("window2", Product.OTVS.getWindow2());
                    nbdInfo.put("window3", Product.OTVS.getWindow3());

                    validationHelper.validateNBD(
                            validateBillingResponseObj.getValidity(),
                            billingResponse.getBody().xmlPath().getString("serviceResponse.nextBillingDate"),
                            nbdInfo
                    );
                }
            }
            if (testSteps.get("stepVerifyCache")) {
                Assert.assertTrue(redisUtil.checkCacheVuconnect(stepVuconnectBillingObj.getMsisdn(),
                        Product.OTVS.getProviderId(), redisConnection));
            } else {
                Assert.assertFalse(redisUtil.checkCacheVuconnect(stepVuconnectBillingObj.getMsisdn(),
                        Product.OTVS.getProviderId(), redisConnection));
            }
            if (testSteps.get("verifyDoubleCharging")) {
                doubleChargingResponse = vuConnectHelper.performStepVuConnectBilling(stepVuconnectBillingObj);
                doubleChargingResponse.then().assertThat().statusCode(200);
                doubleChargingResponse.print();

                final Response validateBillingResponse = testDataHelper.getValidateBillingResponseData(stepVuconnectBillingObj.getMsisdn());
                ValidateBillingResponse validateBillingResponseObj = validateBillingResponse.getBody().as(ValidateBillingResponse.class);
                validationHelper.validate_vuconnect_billing_response(billingResponse, validateBillingResponseObj);

                Assert.assertTrue(redisUtil.checkCacheVuconnect(stepVuconnectBillingObj.getMsisdn(),
                        Product.OTVS.getProviderId(), redisConnection));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}