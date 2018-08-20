package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSyncQueue;
import com.vuclip.premiumengg.automation.subscription_service.common.models.RabbitMessage;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class SRabitMessageHelper {

	public static void addMessageToQueue(RabbitMessage message) throws Exception {
		try {
			Log4J.getLogger().info("SENDING MESSAGE " + ObjectMapperUtils.writeValueAsString(message));

			RabbitMQConnection.getRabbitTemplate().convertAndSend("core_subscription", message);
			Log4J.getLogger().info("MESSAGE SENT");
		} catch (Exception e) {
			AppAssert.assertTrue(false, "not able to send message to queue");
		}
	}

	public static void addMessageToQueue(ConfirmSyncQueue message) throws Exception {

		Log4J.getLogger().info(message.toString());

		RabbitMQConnection.getRabbitTemplate().convertAndSend("core_subscription", message);
		Log4J.getLogger().info("Message sent");
	}

}
