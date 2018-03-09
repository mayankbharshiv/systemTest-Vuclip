package com.vuclip.premiumengg.automation.services.vuconnect.steps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Kohitij_Das
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepVuConnectBilling {

    private String msisdn;

    private String transactionId;

    private String billingCode;

    private String parentBillingCode;

    private String activityType;

    private String callbackURL;

    private String authToken;

    private String activityResult;

    private String requestTypeId;

    private String chargingMode;

    private String itemId;

    private String itemTypeId;

    private String languageId;

    private String subscriptionStatusId;

    private String product;

    private String category;

    private String providerParam;


    public String getProviderParam() {
        return providerParam;
    }

    public void setProviderParam(String providerParam) {
        this.providerParam = providerParam;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public String getParentBillingCode() {
        return parentBillingCode;
    }

    public void setParentBillingCode(String parentBillingCode) {
        this.parentBillingCode = parentBillingCode;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getActivityResult() {
        return activityResult;
    }

    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult;
    }

    public String getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(String requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getChargingMode() {
        return chargingMode;
    }

    public void setChargingMode(String chargingMode) {
        this.chargingMode = chargingMode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(String itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getSubscriptionStatusId() {
        return subscriptionStatusId;
    }

    public void setSubscriptionStatusId(String subscriptionStatusId) {
        this.subscriptionStatusId = subscriptionStatusId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}