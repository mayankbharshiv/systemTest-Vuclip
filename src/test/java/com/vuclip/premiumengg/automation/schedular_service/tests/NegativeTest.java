package com.vuclip.premiumengg.automation.schedular_service.tests;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;

/**
 * @author rahul s
 */

public class NegativeTest {
	private static Logger logger = Log4J.getLogger("GetJobTests");

	/**
	 * if((null==product.getId() || product.getId()<=0) ||
	 * StringUtils.isBlank(retry.getActivityType().toString()) ||
	 * StringUtils.isBlank(retry.getCountry()) ||
	 * (StringUtils.isBlank(retry.getExecutingDays()) ||
	 * retry.getExecutingDays().length()!=7) ||
	 * StringUtils.isBlank(retry.getExecutingTimeWindow()) ||
	 * (null==retry.getSchedulingFrequencyInMinuntes() ||
	 * retry.getSchedulingFrequencyInMinuntes().intValue()<=0) ||
	 * (null==retry.getPartnerId() || retry.getPartnerId().intValue()<=0)){
	 * 
	 * @throws Exception
	 */

	@DataProvider(name = "dp1")
	public Object[][] activationDeactivationPostiveDataProvider() {
		return new Object[][] {

				{ "productid" }, { "productidval" }, { "activitytype" }, { "country" }, { "executingdays" },
				{ "executingdayslength" }, { "executingtimewindow" }, { "FrequencyInMinuntes" },
				{ "FrequencyInMinuntesvalue" }, { "partnerId" }, { "PartnerIdVal" }, };
	}

	@Test(dataProvider = "dp1", groups = { "positive" })
	public void VerifyActivityTypeTests(String field) throws Exception {
		logger.info("=======>VerifyActivityTypeTests Test");
		try {
			int p = 6000;
			String country = "XX";

			SSDBHelper.cleanTestData(p, p, country);
			ConfigurationMessage configurationMessage = SSUtils.getConfigurationMessage("ACTIVATION_RETRY", p, p, 100,
					country);

			switch (field) {
			case "productid":
				configurationMessage.getProduct().setId(null);
				break;
			case "productidval":
				configurationMessage.getProduct().setId(-3);
				break;
			case "activitytype":
				configurationMessage.getRetry().setActivityType("");
				break;
			case "country":
				configurationMessage.getRetry().setCountry(null);
				break;
			case "executingdays":
				configurationMessage.getRetry().setExecutingDays(null);
				break;
			case "executingdayslength":
				configurationMessage.getRetry().setExecutingDays("123");
				break;
			case "executingtimewindow":
				configurationMessage.getRetry().setExecutingTimeWindow(null);
				break;
			case "FrequencyInMinuntes":
				configurationMessage.getRetry().setSchedulingFrequencyInMinuntes(null);
				break;
			case "FrequencyInMinuntesvalue":
				configurationMessage.getRetry().setSchedulingFrequencyInMinuntes(-1);
				break;
			case "partnerId":
				configurationMessage.getRetry().setPartnerId(null);

				break;
			case "PartnerIdVal":
				configurationMessage.getRetry().setPartnerId(-1);

				break;
			}

			QueueMessage queueMessage = SSUtils.getQueueMessage(configurationMessage);
			logger.info("sending this to queue" + queueMessage.toString());
			RabbitMQHelper.sendMessage(queueMessage);

			// validate job_rules and job_rules_window table
			List<Map<String, Object>> jobRuleRecord = DBUtils.getRecord("job_rules",
					" product_id=" + configurationMessage.getProduct().getId() + " and partner_id="
							+ configurationMessage.getRetry().getPartnerId() + " and country='" + country + "'");
		
			AppAssert.assertTrue(jobRuleRecord.size()==0, "verification that no record created");
			// SSValidationHelper.verifyJobRulesRecord(jobRuleRecord, configurationMessage);
			// Map<String, Object> jobRuleTimeWindowRecord =
			// SSDBHelper.getJobRuleTimeWindow((long) jobRuleRecord.get("id"));
			// SSValidationHelper.verifyJobRuleTimeWindowRecord(jobRuleTimeWindowRecord,
			// configurationMessage);
			SSDBHelper.cleanTestData(p, p, country);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
