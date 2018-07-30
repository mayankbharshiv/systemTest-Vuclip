package com.vuclip.premiumengg.automation.schedular_service.common.utils;

import com.vuclip.premiumengg.automation.schedular_service.common.models.SchedulerSaveProductRequest;
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

    public static SchedulerSaveProductRequest getConfigurationMessage(String activityType, int productId, int partnerId,
                                                                      int freInMinute) {
        SchedulerSaveProductRequest configurationMessage = loadJson("configurationMessage.json", SchedulerSaveProductRequest.class);
        configurationMessage.getRetry().get(0).setActivityType(activityType);
        configurationMessage.getRetry().get(0).setProductId(productId);
        configurationMessage.getRetry().get(0).setPartnerId(partnerId);
        configurationMessage.getRetry().get(0).setSchedulingFrequencyInMinutes(freInMinute);
        return configurationMessage;
    }

    public static SchedulerSaveProductRequest getConfigurationMessage(String activityType, int productId, int partnerId,
                                                                      int freInMinute, String country) {
        SchedulerSaveProductRequest configurationMessage = loadJson("configurationMessage.json", SchedulerSaveProductRequest.class);
        configurationMessage.getRetry().get(0).setActivityType(activityType);
        configurationMessage.getRetry().get(0).setProductId(productId);
        configurationMessage.getRetry().get(0).setPartnerId(partnerId);
        configurationMessage.getRetry().get(0).setSchedulingFrequencyInMinutes(freInMinute);
        configurationMessage.getRetry().get(0).setCountryCode(country);
        return configurationMessage;
    }

    public static SchedulerSaveProductRequest getConfigurationMessage(String activityType) {
        SchedulerSaveProductRequest configurationMessage = loadJson("configurationMessage.json", SchedulerSaveProductRequest.class);
        configurationMessage.getRetry().get(0).setActivityType(activityType);
        return configurationMessage;
    }

}
