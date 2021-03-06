package com.vuclip.premiumengg.automation.schedular_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.schedular_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.schedular_service.common.models.SchedulerSaveProductRequest;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSDBHelper;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSHelper;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSUtils;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @author rahul s
 */

public class VerifyActivityTypeTest {
    private static Logger logger = Log4J.getLogger("GetJobTests");

    @DataProvider(name = "dp")
    public Object[][] activationDeactivationPostiveDataProvider() {
        return new Object[][]{{ActivityType.ACTIVATION_RETRY.name()}, {ActivityType.CONTENT_SMS.name()},
                {ActivityType.DEACTIVATION_RETRY.name()}, {ActivityType.ENGAGEMENT_SMS.name()},
                {ActivityType.FREETRIAL_RENEWAL.name()}, {ActivityType.OPTOUT_SMS.name()},
                {ActivityType.PRE_RENEWAL_SMS.name()}, {ActivityType.RENEWAL.name()},
                {ActivityType.RENEWAL_RETRY.name()}, {ActivityType.SYSTEM_CHURN.name()},
                {ActivityType.WINBACK.name()}

        };
    }

    @Test(dataProvider = "dp", groups = {"positive"})
    public void VerifyActivityTypeTests(String activityType) {
        try {
            logger.info("=======>VerifyActivityTypeTests Test");

            int p = 6000;
            String country = "XX";

            SSDBHelper.cleanTestData(p, p, country);
            SchedulerSaveProductRequest configurationMessage = SSUtils.getConfigurationMessage(activityType, p, p, 100,
                    country);

            // QueueMessage queueMessage = SSUtils.getQueueMessage(configurationMessage);
            logger.info("sending this to queue" + configurationMessage.toString());
            SSHelper.sendMessage(configurationMessage);

            // validate job_rules and job_rules_window table
            // adding this as on CI it is failing due to fastness of getting data from Db
            // after performing operation
            Map<String, Object> jobRuleRecord = SSDBHelper.getJobRules(p, p, country);
            SSValidationHelper.verifyJobRulesRecord(jobRuleRecord, configurationMessage);
            Map<String, Object> jobRuleTimeWindowRecord = SSDBHelper
                    .getJobRuleTimeWindow((long) jobRuleRecord.get("id"));
            SSValidationHelper.verifyJobRuleTimeWindowRecord(jobRuleTimeWindowRecord, configurationMessage);
            SSDBHelper.cleanTestData(p, p, country);

        } catch (Exception e) {
            e.printStackTrace();
            AppAssert.assertTrue(false, "Test case failed due to exception " + e.getMessage());
        }
    }

}
