package com.vuclip.premiumengg.automation.schedular_service.tests;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.schedular_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.schedular_service.common.models.ConfigurationMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.models.QueueMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.RabbitMQHelper;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSDBHelper;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSUtils;
import com.vuclip.premiumengg.automation.schedular_service.common.utils.SSValidationHelper;

/**
 * @author rahul s
 */

public class VerifyActivityTypeTest {
	private static Logger logger = Log4J.getLogger("GetJobTests");

	@DataProvider(name = "dp")
	public Object[][] activationDeactivationPostiveDataProvider() {
		return new Object[][] { { ActivityType.ACTIVATION_RETRY.name() }, { ActivityType.CONTENT_SMS.name() },
				{ ActivityType.DEACTIVATION_RETRY.name() }, { ActivityType.ENGAGEMENT_SMS.name() },
				{ ActivityType.FREETRIAL_RENEWAL.name() }, { ActivityType.OPTOUT_SMS.name() },
				{ ActivityType.PRE_RENEWAL_SMS.name() }, { ActivityType.RENEWAL.name() },
				{ ActivityType.RENEWAL_RETRY.name() }, { ActivityType.SYSTEM_CHURN.name() },
				{ ActivityType.WINBACK.name() }

		};
	}

	@Test(dataProvider = "dp", groups = { "positive" })
	public void VerifyActivityTypeTests(String activityType) throws Exception {
		logger.info("=======>VerifyActivityTypeTests Test");

		int p = 6000;
		String country = "XX";

		SSDBHelper.cleanTestData(p, p, country);
		ConfigurationMessage configurationMessage = SSUtils.getConfigurationMessage(activityType, p, p, 100,country);

		QueueMessage queueMessage = SSUtils.getQueueMessage(configurationMessage);
		logger.info("sending this to queue" + queueMessage.toString());
		RabbitMQHelper.sendMessage(queueMessage);

		// validate job_rules and job_rules_window table
		Map<String, Object> jobRuleRecord = SSDBHelper.getJobRules(p, p, country);
		SSValidationHelper.verifyJobRulesRecord(jobRuleRecord, configurationMessage);
		Map<String, Object> jobRuleTimeWindowRecord = SSDBHelper.getJobRuleTimeWindow((long) jobRuleRecord.get("id"));
		SSValidationHelper.verifyJobRuleTimeWindowRecord(jobRuleTimeWindowRecord, configurationMessage);
		SSDBHelper.cleanTestData(p, p, country);

	}

}
