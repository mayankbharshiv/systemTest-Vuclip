package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Kohitij_Das on 22/03/18.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BillingPackage {

    PACKAGE1(11, "TestProduct11", 11, "TestPartner11", "India", "IN", "927312121", "TEST_SERVICEID_927311336", 99.15243,
            "INR", 30, 7, 5, 1, "DAYS", 1, 1, 1, "ACTIVE", false, true, false, false),
    PACKAGE2(999, "TestProduct999", 999, "TestPartner999", "India", "IN", "927319999", "TEST_SERVICEID_927319999", 999.99,
            "INR", 30, 7, 5, 1, "DAYS", 1, 1, 1, "ACTIVE", false, true, false, false);

    private final int productId;

    private final String productName;

    private final int partnerId;

    private final String partnerName;

    private final String country;

    private final String countryCode;

    private final String billingCode;

    private final String serviceId;

    private final double price;

    private final String currency;

    private final int validityPeriod;

    private final int parkingPeriod;

    private final int suspendPeriod;

    private final int notificationWaitPeriod;

    private final String timeUnit;

    private final int itemId;

    private final int itemTypeId;

    private final int clientId;

    private final String status;

    private final boolean balanceCheckRequired;

    private final boolean carrier;

    private final boolean freeTrial;

    private final boolean fallback;

    BillingPackage(int productId, String productName, int partnerId, String partnerName, String country, String countryCode, String billingCode,
                   String serviceId, double price, String currency, int validityPeriod, int parkingPeriod, int suspendPeriod,
                   int notificationWaitPeriod, String timeUnit, int itemId, int itemTypeId, int clientId, String status,
                   boolean balanceCheckRequired, boolean carrier, boolean freeTrial, boolean fallback) {
        this.productId = productId;
        this.productName = productName;
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.country = country;
        this.countryCode = countryCode;
        this.billingCode = billingCode;
        this.serviceId = serviceId;
        this.price = price;
        this.currency = currency;
        this.validityPeriod = validityPeriod;
        this.parkingPeriod = parkingPeriod;
        this.suspendPeriod = suspendPeriod;
        this.notificationWaitPeriod = notificationWaitPeriod;
        this.timeUnit = timeUnit;
        this.itemId = itemId;
        this.itemTypeId = itemTypeId;
        this.clientId = clientId;
        this.status = status;
        this.balanceCheckRequired = balanceCheckRequired;
        this.carrier = carrier;
        this.freeTrial = freeTrial;
        this.fallback = fallback;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public int getParkingPeriod() {
        return parkingPeriod;
    }

    public int getSuspendPeriod() {
        return suspendPeriod;
    }

    public int getNotificationWaitPeriod() {
        return notificationWaitPeriod;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public int getClientId() {
        return clientId;
    }

    public String getStatus() {
        return status;
    }

    public boolean isBalanceCheckRequired() {
        return balanceCheckRequired;
    }

    public boolean isCarrier() {
        return carrier;
    }

    public boolean isFreeTrial() {
        return freeTrial;
    }

    public boolean isFallback() {
        return fallback;
    }
}
