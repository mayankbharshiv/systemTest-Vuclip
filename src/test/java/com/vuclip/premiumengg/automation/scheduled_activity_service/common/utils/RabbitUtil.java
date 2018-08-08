package com.vuclip.premiumengg.automation.scheduled_activity_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitAdminConnection;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

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
					getQueueNames(productId, partnerId, country, "ACTIVATION"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "ACTIVATION_RETRY"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "DEACTIVATION"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "DEACTIVATION_RETRY"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "RENEWAL"));

			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "SYSTEM_CHURN"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "WINBACK"));
			deleteQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "FREETRIAL_RENEWAL"));
		} catch (Exception e) {
			Log4J.getLogger().info("Some error deleting queue before suite ");
		}
	}

	public static void purgeAllActivityQueue(Integer productId, Integer partnerId, String country) {
		try {
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "ACTIVATION"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "ACTIVATION_RETRY"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "DEACTIVATION"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "DEACTIVATION_RETRY"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "RENEWAL"));

			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "SYSTEM_CHURN"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "WINBACK"));
			purgeQueue(RabbitAdminConnection.getRabbitAdminConnection(),
					getQueueNames(productId, partnerId, country, "FREETRIAL_RENEWAL"));
		} catch (Exception e) {
			Log4J.getLogger().info("Some error deleting queue before suite ");
		}
	}

	public static String getQueueName(Integer productId, Integer partnerId, String country, String activity) {
		String activityType = "";
		switch (activity) {
		case "ACTIVATION":
			activityType = "ACTIVATION";
		case "ACTIVATION_RETRY":
		case "WINBACK":
			activityType = "ACTIVATION";
		case "DEACTIVATION":
			activityType = "DEACTIVATION";
		case "DEACTIVATION_RETRY":
			activityType = "DEACTIVATION";
		case "FREETRIAL_RENEWAL":
		case "RENEWAL":
			activityType = "RENEWAL";
		case "SYSTEM_CHURN":
			activityType = "DEACTIVATION";
		}
		return "" + productId + "_" + partnerId + "_" + country + "_" + activityType
				+ "_SCHEDULEDACTIVITY_COREACTIVITY";
	}

	public static String getQueueNames(Integer productId, Integer partnerId, String country, String activity) {

		return "" + productId + "_" + partnerId + "_" + country + "_" + activity + "_SCHEDULEDACTIVITY_COREACTIVITY";
	}

	public static Message receive(RabbitTemplate rabbitTemplate, String queueName, long timeInMilli) {
		timeInMilli = 2000;
		// if (queueName.contains("ACTIVATION_SCHE"))
		// queueName = queueName.replaceAll("ACTIVATION", "ACTIVATION_RETRY");
		// if (queueName.contains("DEACTIVATION_SCHE"))
		// queueName = queueName.replaceAll("DEACTIVATION", "DEACTIVATION_RETRY");

		Log4J.getLogger().info("QUEUE NAME TO FETCH " + queueName);

		Message message = rabbitTemplate.receive(queueName, timeInMilli);
		if (message == null) {
			Log4J.getLogger().info("RabbitMQ: Message is null not able to fetch  ");
		} else
			return message;
		String[] qs = queueName.split("_");

		Integer productId = Integer.parseInt(qs[0]);
		Integer partnerId = Integer.parseInt(qs[1]);
		String country = qs[2];
		
		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "ACTIVATION");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "ACTIVATION"), 10);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));

		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "ACTIVATION_RETRY");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "ACTIVATION_RETRY"), 10);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));
		
		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "DEACTIVATION");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "DEACTIVATION"), 10);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));
		
		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "DEACTIVATION_RETRY");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "DEACTIVATION_RETRY"), 10);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));
		
		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "RENEWAL");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "RENEWAL"), 10);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));
		
		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "SYSTEM_CHURN");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "SYSTEM_CHURN"), 10);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));
		
		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "WINBACK");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "WINBACK"), 10);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));
		
		Log4J.getLogger().info(productId + "_" + partnerId + "_" + country + "ACTIVATION");
		message = rabbitTemplate.receive(getQueueNames(productId, partnerId, country, "FREETRIAL_RENEWAL"), 5000);
		if (message != null)
			Log4J.getLogger().info(new String(message.getBody()));
		return message;
	}
}
