package com.vuclip.premiumengg.automation.scheduled_activity_service.tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASHelper;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils.SASValidationHelper;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

/**
 * 
 * @author mayank.bharshiv
 *
 */

public class ScheduleActivityTests {

	private SASHelper sasHelper;
	private SASValidationHelper sasValidationHelper;
	private Random rand;
	int productId;
	int partnerId;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		sasHelper = new SASHelper();
		sasValidationHelper = new SASValidationHelper();
		rand = new Random();
		productId = rand.nextInt(10);
		partnerId = productId;
		// TODO: test data cleanup if needed
		
	}

	@DataProvider(name = "activityType")
	public Object[][] activityType() {
		return new Object[][] { { ActivityType.ACTIVATION_TYPE }, { ActivityType.ACTIVATION_RETRY_TYPE },
				{ ActivityType.CONTENT_SMS_TYPE }, { ActivityType.DEACTIVATION },
				{ ActivityType.DEACTIVATION_RETRY_TYPE }, { ActivityType.ENGAGEMENT_SMS_TYPE },
				{ ActivityType.FREETRIAL_RENEWAL_TYPE }, { ActivityType.OPTOUT_SMS_TYPE },
				{ ActivityType.PRE_RENEWAL_SMS_TYPE }, { ActivityType.RENEWAL_TYPE },
				{ ActivityType.SYSTEM_CHURN_TYPE }, { ActivityType.WINBACK_TYPE }, };

	}

	@Test(dataProvider="activityType")
	public void setupConfigJob(String activityType) throws Exception {
		
		//  create job config for activity types( ex- Activation, deactivation)
		PublishConfigRequest publishConfigRequest = ObjectMapperUtils.readValue(
						"src/test/resources/configurations/scheduled-activity-service/request/publishConfig.json",
						PublishConfigRequest.class);
		
		Log4J.getLogger().info("Config JSON Body changed");
		publishConfigRequest.getProduct().setProductId(productId);
		publishConfigRequest.getProductPartnerMappings().get(0).setProductId(productId);
		publishConfigRequest.getProductPartnerMappings().get(0).setPartnerId(partnerId);
		publishConfigRequest.getProductCountryMapping().setProductId(productId);
		publishConfigRequest.getAdNetworkNotifications().get(0).setPartnerId(partnerId);
		publishConfigRequest.getAdNetworkNotifications().get(0).setProductId(productId);
		publishConfigRequest.getActivityFlows().get(0).setPartnerId(partnerId);
		publishConfigRequest.getActivityFlows().get(1).setPartnerId(partnerId);
		publishConfigRequest.getActivityFlows().get(2).setPartnerId(partnerId);
		publishConfigRequest.getPricePoints().get(0).setProductId(productId);
		publishConfigRequest.getPricePoints().get(0).setPartnerId(partnerId);
		publishConfigRequest.getRetry().get(0).setProductId(productId);
		publishConfigRequest.getRetry().get(0).setPartnerId(partnerId);
		publishConfigRequest.getBlackouts().get(0).setPartnerId(partnerId);
		publishConfigRequest.getBlackouts().get(0).setProductId(productId);
		publishConfigRequest.getRetry().get(0).setActivityType(activityType);
		Log4J.getLogger().info("Config API Called");	
		sasValidationHelper.validate_sms_api_response(sasHelper.saveProduct(publishConfigRequest));

	}
	
	
}
