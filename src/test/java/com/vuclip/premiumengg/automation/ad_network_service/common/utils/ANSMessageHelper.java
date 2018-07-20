package com.vuclip.premiumengg.automation.ad_network_service.common.utils;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.Message;
import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;

public class ANSMessageHelper {

	public static void addMessageToQueue(Message message) throws Exception {

		Log4J.getLogger().info(message.toString());

		RabbitMQConnection.getRabbitTemplate().convertAndSend("core_adnetwork", message);
		Log4J.getLogger().info("Message sent");
	}

	/*public static void addMessageToQueue(Integer productId, String productName, Integer partnerId, String partnerName,
			String userId, String attemptedBillingCode, String attemptedPrice, String chargedBillingCode,
			String chargedPrice, String requestedBillingCode, String requestedPrice, String action, String activity,
			String transactionState, String actionResult, long subscriptionId, String eventType,
			BigInteger nextBillingDate, String adNetworkParams, Object churnNotificationParam, Object userSource)
			throws Exception {

		Message message = new Message();
		message.setEventType(eventType);
		message.setProductId(productId);
		message.setProductName(productName);
		message.setPartnerId(partnerId);
		message.setPartnerName(partnerName);
		message.setUserId(userId);
		message.setMsisdn(userId);
		message.setAttemptedBillingCode(attemptedBillingCode);
		message.setAttemptedPrice(attemptedPrice);
		message.setChargedBillingCode(chargedBillingCode);
		message.setChargedPrice(chargedPrice);
		message.setRequestedBillingCode(requestedBillingCode);
		message.setRequestedPrice(requestedPrice);
		message.setAction(action);
		message.setActivity(activity);
		message.setTransactionState(transactionState);
		message.setActionResult(actionResult);
		message.setSubscriptionId(subscriptionId);
		message.setUserSource(userSource);
		message.setNextBillingDate(nextBillingDate);
		message.setAdNetworkParams(adNetworkParams);
		message.setMode("WAP");
		message.setChurnNotificationParam(churnNotificationParam);

		addMessageToQueue(message);
	}*/

	

}
