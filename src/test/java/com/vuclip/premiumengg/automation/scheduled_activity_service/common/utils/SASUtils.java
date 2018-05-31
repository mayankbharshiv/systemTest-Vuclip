package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.SchedulerRequest;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.UserSubscriptionRequest;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class SASUtils {
	/**
	 * 
	 * @param JsonFileName
	 * @param type
	 * @return
	 */
	public static <T> T loadJson(String JsonFileName, Class<T> type) {
		return ObjectMapperUtils.readValue(
				"src/test/resources/configurations/scheduled-activity-service/request/" + JsonFileName, type);
	}

	public static PublishConfigRequest generateSaveProductConfig(Integer productId, Integer partnerId,
			String activityType) {

		PublishConfigRequest publishConfigRequest = loadJson("publishConfig.json", PublishConfigRequest.class);

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
		return publishConfigRequest;
	}

	public static SchedulerRequest generateSchedulerRequest(Integer productId, Integer partnerId, String actionTable) {
		SchedulerRequest schedulerRequest = loadJson("scheduler.json", SchedulerRequest.class);
		schedulerRequest.setActivityType(actionTable.toUpperCase());
		schedulerRequest.setProductId(productId);
		schedulerRequest.setPartnerId(partnerId);
		return schedulerRequest;
	}

	public static UserSubscriptionRequest generateUserSubscriptionRequest(Integer productId, Integer partnerId, String activityType,
			String previousSubscriptionState, String transactionState, String actionType, int subscriptionId) {
		UserSubscriptionRequest userSubscriptionRequest = loadJson("userSubscription.json",
				UserSubscriptionRequest.class);

		userSubscriptionRequest.getActivityInfo().setActivityType(activityType);
		userSubscriptionRequest.getActivityInfo().setPreviousSubscriptionState(previousSubscriptionState);
		userSubscriptionRequest.getActivityInfo().setCurrentSubscriptionState(previousSubscriptionState);
		userSubscriptionRequest.getActivityEvent().setTransactionState(transactionState);
		userSubscriptionRequest.getActivityInfo().setActionType(actionType);
		userSubscriptionRequest.getSubscriptionInfo().setSubscriptionId(subscriptionId);
		userSubscriptionRequest.getSubscriptionInfo().setProductId(productId);
		userSubscriptionRequest.getSubscriptionInfo().setPartnerId(partnerId);
		userSubscriptionRequest.getActivityEvent().setPartnerId(partnerId);
		userSubscriptionRequest.getActivityEvent().setProductId(productId);
		return userSubscriptionRequest;
	}
}
