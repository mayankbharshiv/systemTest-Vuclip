package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASUtils;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.JsonHelper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SchedulerAPINegativeTests {
    private static Logger logger = Log4J.getLogger("SchedulerAPINegativeTests");
    int productId;
    int partnerId;
    String activityType = "WINBACK";
    String previousSubscriptionState = "PARKING";
    String currentSubscriptionState = "ACTIVATED";
    String transactionState = "SUCCESS";
    String actionType = "CHARGING";
    Integer subscriptionId;
    String actionTable = "RENEWAL";
    private SASHelper sasHelper;
    private String countryCode = "IN";

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        sasHelper = new SASHelper();
        productId = RandomUtils.nextInt(77000, 77900);
        partnerId = productId;
    }

    @DataProvider(name = "schedulerApiInvalidFieldValuesDataProvider")
    public Object[][] schedulerApiInvalidFieldValuesDataProvider() {
        return new Object[][]{
                {0, 0, null, null},
                {partnerId, productId, countryCode, null},
                /*bug{ 0, 0, countryCode,"RENEWAL" },
                bug{ productId, 0, countryCode,"RENEWAL" },
                bug { 0, partnerId, countryCode,"RENEWAL" },
                bug { partnerId, productId, null,"RENEWAL" },*/

        };
    }

    @Test(dataProvider = "schedulerApiInvalidFieldValuesDataProvider", groups = {"positive"})
    public void schedulerApiInvalidFieldValuesTest(Integer sproductId, Integer spartnerId, String scountryCode,
                                                   String sactivityType) throws Exception {
        subscriptionId = RandomUtils.nextInt(100, 200);
        String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
                + currentSubscriptionState + " " + transactionState + " " + actionType;
        logger.info("==================>Starting scheduler api invalid fields values test  [ " + testMessage + " ]");

        try {

            SASValidationHelper.validate_sas_api_response(
                    sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
                            activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
                            actionType, subscriptionId)));

            SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(sproductId, spartnerId,
                    actionTable);
            generateSchedulerRequest.setCountry(scountryCode);
            generateSchedulerRequest.setActivityType(sactivityType);
            SASValidationHelper.validate_schedular_invalid_api_response(sasHelper.scheduler(generateSchedulerRequest));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }

    @DataProvider(name = "schedulerApiMissingFieldsDataProvider")
    public Object[][] schedulerApiMissingFieldsDataProvider() {
        return new Object[][]{
                /*bug{ "productId" },
                bug{ "partnerId" },
                bug{ "country" }*/
                {"activityType"}
        };
    }

    @Test(dataProvider = "schedulerApiMissingFieldsDataProvider", groups = {"positive"})
    public void schedulerApiMissingFieldsValidation(String jsonElement) throws Exception {
        subscriptionId = RandomUtils.nextInt(100, 200);
        String testMessage = subscriptionId + " " + activityType + " " + previousSubscriptionState + " "
                + currentSubscriptionState + " " + transactionState + " " + actionType;
        logger.info("==================>Starting scheduler api missing fields test  [ " + testMessage + " ]");

        try {

            SASValidationHelper.validate_sas_api_response(
                    sasHelper.userSubscription(SASUtils.generateUserSubscriptionRequest(productId, partnerId,
                            activityType, previousSubscriptionState, currentSubscriptionState, transactionState,
                            actionType, subscriptionId)));
            SchedulerRequest generateSchedulerRequest = SASUtils.generateSchedulerRequest(productId, partnerId,
                    actionTable);
            String jsonString = JsonHelper.remove(SchedulerRequest.class, generateSchedulerRequest, jsonElement);
            System.out.println(jsonString);
            /*generateSchedulerRequest = ObjectMapperUtils.readValueFromString(jsonString, SchedulerRequest.class);
            System.out.println(generateSchedulerRequest.toString());*/

            SASValidationHelper.validate_schedular_invalid_api_response(sasHelper.scheduler(jsonString));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }
}
