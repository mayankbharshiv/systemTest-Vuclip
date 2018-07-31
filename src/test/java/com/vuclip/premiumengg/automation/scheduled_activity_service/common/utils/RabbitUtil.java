package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitAdminConnection;

public class RabbitUtil {

	public static void deleteQueue(RabbitAdmin rabbitAdminConnection, String queueName) {
		rabbitAdminConnection.deleteQueue(queueName);
	}

	public static void purgeQueue(RabbitAdmin rabbitAdminConnection, String queueName) {
		rabbitAdminConnection.purgeQueue(queueName, false);
	}

	public static void deleteAllActivityQueue(Integer productId, Integer partnerId, String country) {
		try {
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "ACTIVATION"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "ACTIVATION_RETRY"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "DEACTIVATION"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "DEACTIVATION_RETRY"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "RENEWAL"));

			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "SYSTEM_CHURN"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "WINBACK"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "FREETRIAL_RENEWAL"));
		} catch (Exception e) {
			Log4J.getLogger().info("Some error deleting queue before suite ");
		}
	}

	public static void purgeAllActivityQueue(Integer productId, Integer partnerId, String country) {
		try {
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "ACTIVATION"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "ACTIVATION_RETRY"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "DEACTIVATION"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "DEACTIVATION_RETRY"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "RENEWAL"));

			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "SYSTEM_CHURN"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "WINBACK"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueName(productId, partnerId, country, "FREETRIAL_RENEWAL"));
		} catch (Exception e) {
			Log4J.getLogger().info("Some error deleting queue before suite ");
		}
	}

	public static String getQueueName(Integer productId, Integer partnerId, String country, String activity) {
		return "" + productId + "_" + partnerId + "_" + country + "_" + activity.toUpperCase()
				+ "_SCHEDULEDACTIVITY_COREACTIVITY";
	}

	public static Message receive(RabbitTemplate rabbitTemplate, String queueName, long timeInMilli) {
		// if (queueName.contains("ACTIVATION_SCHE"))
		// queueName = queueName.replaceAll("ACTIVATION", "ACTIVATION_RETRY");
		// if (queueName.contains("DEACTIVATION_SCHE"))
		// queueName = queueName.replaceAll("DEACTIVATION", "DEACTIVATION_RETRY");
		Log4J.getLogger().info("QUEUE NAME TO FETCH " + queueName);

		Message message = rabbitTemplate.receive(queueName, timeInMilli);
		if (message == null) {
			Log4J.getLogger().info("RabbitMQ: Message is null not able to fetch  ");
		}
		return message;
	}
}
