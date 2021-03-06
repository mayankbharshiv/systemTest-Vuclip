package com.vuclip.premiumengg.automation.subscription_service.common.utils;

public enum TimeUnitEnum {

    SECOND(1L), MINUTE(60L), HOUR(3600L), DAY(86400L), WEEK(604800L), MONTH(2592000L);
    private long seconds;

    TimeUnitEnum(long seconds) {
        this.seconds = seconds;
    }

    public long toSeconds() {
        return seconds;
    }
}