package com.vuclip.premiumengg.automation.schedular_service.common.models;

public enum ActivityType {

    DEACTIVATION_RETRY("DEACTIVATION_RETRY"), RENEWAL_RETRY("RENEWAL_RETRY"), FREETRIAL_RENEWAL(
            "FREETRIAL_RENEWAL"), OPTOUT_SMS("OPTOUT_SMS"), PRE_RENEWAL_SMS("PRE_RENEWAL_SMS"), RENEWAL(
            "RENEWAL"), ENGAGEMENT_SMS("ENGAGEMENT_SMS"), SYSTEM_CHURN("SYSTEM_CHURN"), ACTIVATION_RETRY(
            "ACTIVATION_RETRY"), CONTENT_SMS("CONTENT_SMS"), WINBACK("WINBACK");

    private String activity;

    ActivityType(String activity) {
        this.activity = activity;
    }

    public String toactivity() {
        return activity;
    }

}
