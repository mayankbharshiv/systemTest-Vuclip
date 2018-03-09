package com.vuclip.premiumengg.automation.services.vuconnect.common;

/**
 * ProductName(customerId,providerId,timeZone,window1,window2,window3,timeUnit)
 */
public enum Product {

    OTVS("134", "134", "GMT+01:00", "08:00:00.0", "12:00:00.0", "16:00:00.0", "DAY");

    private final String customerId;

    private final String providerId;

    private final String timeZone;

    private final String window1;

    private final String window2;

    private final String window3;

    private final String timeUnit;

    Product(String customerId, String providerId, String timeZone, String window1, String window2, String window3, String timeUnit) {
        this.customerId = customerId;
        this.providerId = providerId;
        this.timeZone = timeZone;
        this.window1 = window1;
        this.window2 = window2;
        this.window3 = window3;
        this.timeUnit = timeUnit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getWindow1() {
        return window1;
    }

    public String getWindow2() {
        return window2;
    }

    public String getWindow3() {
        return window3;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}