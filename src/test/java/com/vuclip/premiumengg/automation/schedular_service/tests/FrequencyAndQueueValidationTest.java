package com.vuclip.premiumengg.automation.schedular_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitAdminConnection;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.schedular_service.common.models.JobMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.models.SchedulerSaveProductRequest;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSDBHelper;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSHelper;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSUtils;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSValidationHelper;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @author rahul s
 */

public class FrequencyAndQueueValidationTest {
    private static Logger logger = Log4J.getLogger("GetJobTests");

    @Test(groups = {"positive"})
    public void FrequencyAndQueueValidationTests() {
        try {
            logger.info("========> FrequencyAndQueueValidationTests Test");

            int p = 1001;
            String country = "MM";

            SSDBHelper.cleanTestData(p, p, country);
            SchedulerSaveProductRequest configurationMessage = SSUtils.getConfigurationMessage("ACTIVATION_RETRY", p, p, 10,
                    country);

            logger.info("sending this to queue" + configurationMessage.toString());
            SSHelper.sendMessage(configurationMessage);

            // validate DB
            Thread.sleep(3000);
            Map<String, Object> jobRuleRecord = SSDBHelper.getJobRules(p, p, country);
            System.out.println(jobRuleRecord.toString());
            SSValidationHelper.verifyJobRulesRecord(jobRuleRecord, configurationMessage);
            Map<String, Object> jobRuleTimeWindowRecord = SSDBHelper
                    .getJobRuleTimeWindow((long) jobRuleRecord.get("id"));
            SSValidationHelper.verifyJobRuleTimeWindowRecord(jobRuleTimeWindowRecord, configurationMessage);

            // validate with 404 it will come into queue and verify content
            RabbitAdminConnection.getRabbitAdminConnection().purgeQueue("UBS-JOB-Q-PUSH", false);
            SSHelper.runJob(SSUtils.jobName, SSUtils.groupName);

            Message message = RabbitMQConnection.getRabbitTemplate().receive("UBS-JOB-Q-PUSH", 15000);
            String jsonMessage = new String(message.getBody());
            System.out.println(jsonMessage);
            JobMessage responseJobMessage = ObjectMapperUtils.readValueFromString(jsonMessage, JobMessage.class);

            SSValidationHelper.verifyQueueMEssage(responseJobMessage, configurationMessage);

            // verify it should not process job rule
            RabbitAdminConnection.getRabbitAdminConnection().purgeQueue("UBS-JOB-Q-PUSH", false);
            SSHelper.runJob(SSUtils.jobName, SSUtils.groupName);
            message = null;
            message = RabbitMQConnection.getRabbitTemplate().receive("UBS-JOB-Q-PUSH", 15000);
            AppAssert.assertTrue(message == null, "Verify Job not executed for this rule");
//			SSDBHelper.cleanTestData(p, p, country);

        } catch (Exception e) {
            e.printStackTrace();
            AppAssert.assertTrue(false, "Test case failed due to exception " + e.getMessage());

        }
    }

}
