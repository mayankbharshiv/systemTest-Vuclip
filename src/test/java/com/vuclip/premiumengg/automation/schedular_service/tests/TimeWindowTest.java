package com.vuclip.premiumengg.automation.schedular_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitAdminConnection;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.schedular_service.common.models.ConfigurationMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.models.QueueMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @author rahul s
 */

public class TimeWindowTest {
    private static Logger logger = Log4J.getLogger("GetJobTests");

    @Test(groups = {"positive"})
    public void ExecutionFrequencyTests() throws Exception {

        logger.info("========> FrequencyAndQueueValidationTests Test");

        int p = 4000;
        String country = "AA";

        SSDBHelper.cleanTestData(p, p, country);
        ConfigurationMessage configurationMessage = SSUtils.getConfigurationMessage("ACTIVATION_RETRY", p, p, 1,
                country);
        configurationMessage.getRetry().setExecutingTimeWindow("00:00:00-00:00:00");

        QueueMessage queueMessage = SSUtils.getQueueMessage(configurationMessage);
        logger.info("sending this to queue" + queueMessage.toString());
        RabbitMQHelper.sendMessage(queueMessage);

        // validate DB
        Map<String, Object> jobRuleRecord = SSDBHelper.getJobRules(p, p, country);
        System.out.println(jobRuleRecord.toString());
        SSValidationHelper.verifyJobRulesRecord(jobRuleRecord, configurationMessage);
        Map<String, Object> jobRuleTimeWindowRecord = SSDBHelper.getJobRuleTimeWindow((long) jobRuleRecord.get("id"));
        SSValidationHelper.verifyJobRuleTimeWindowRecord(jobRuleTimeWindowRecord, configurationMessage);

        // verify it should not process job rule
        RabbitAdminConnection.getRabbitAdminConnection().purgeQueue("UBS-JOB-Q-PUSH", false);
        SSHelper.runJob(SSUtils.jobName, SSUtils.groupName);
        Message message = RabbitMQConnection.getRabbitTemplate().receive("UBS-JOB-Q-PUSH", 2000);
        AppAssert.assertTrue(message == null, "Verify Job not executed for this rule as time window is not valid");
        SSDBHelper.cleanTestData(p, p, country);

    }

}
