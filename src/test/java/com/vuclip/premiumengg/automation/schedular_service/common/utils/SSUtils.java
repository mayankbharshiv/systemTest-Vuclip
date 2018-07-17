package com.vuclip.premiumengg.automation.schedular_service.common.utils;

import com.vuclip.premiumengg.automation.schedular_service.common.models.ConfigurationMessage;
import com.vuclip.premiumengg.automation.schedular_service.common.models.QueueMessage;
import com.vuclip.premiumengg.automation.scheduled_activity_service.common.models.PublishConfigRequest;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class SSUtils {
	public static String jobName = "job";
	public static String groupName = "auto-job";
	public static PublishConfigRequest productConfig = null;

	/**
	 * @param JsonFileName
	 * @param type
	 * @return
	 */
	public static <T> T loadJson(String JsonFileName, Class<T> type) {
		return ObjectMapperUtils
				.readValue("src/test/resources/configurations/schedular-service/request/" + JsonFileName, type);
	}

	public static ConfigurationMessage getConfigurationMessage(String activityType, int productId, int partnerId,
			int freInMinute) {
		ConfigurationMessage configurationMessage = loadJson("configurationMessage.json", ConfigurationMessage.class);
		configurationMessage.getRetry().setActivityType(activityType);
		configurationMessage.getProduct().setId(productId);
		configurationMessage.getRetry().setPartnerId(partnerId);
		configurationMessage.getRetry().setSchedulingFrequencyInMinuntes(freInMinute);
		return configurationMessage;
	}
	public static ConfigurationMessage getConfigurationMessage(String activityType, int productId, int partnerId,
			int freInMinute,String country) {
		ConfigurationMessage configurationMessage = loadJson("configurationMessage.json", ConfigurationMessage.class);
		configurationMessage.getRetry().setActivityType(activityType);
		configurationMessage.getProduct().setId(productId);
		configurationMessage.getRetry().setPartnerId(partnerId);
		configurationMessage.getRetry().setSchedulingFrequencyInMinuntes(freInMinute);
		configurationMessage.getRetry().setCountry(country);
		return configurationMessage;
	}

	public static ConfigurationMessage getConfigurationMessage(String activityType) {
		ConfigurationMessage configurationMessage = loadJson("configurationMessage.json", ConfigurationMessage.class);
		configurationMessage.getRetry().setActivityType(activityType);
		return configurationMessage;
	}

	public static QueueMessage getQueueMessage(ConfigurationMessage configurationMessage) {
		String jobMessage = (ObjectMapperUtils.writeValueAsString(configurationMessage));
		QueueMessage queueMessage = loadJson("queueMessage.json", QueueMessage.class);
		queueMessage.setPayload(jobMessage);
		return queueMessage;
	}

}
