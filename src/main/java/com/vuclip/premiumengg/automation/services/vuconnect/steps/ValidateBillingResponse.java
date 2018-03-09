package com.vuclip.premiumengg.automation.services.vuconnect.steps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Kohitij_Das
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidateBillingResponse {

    private String msisdn;

    private String responseCode;

    private String billingActionId;

    private String billingStatusId;

    private String nextBillingDate;

    private String smsType;

    private String validity;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getBillingActionId() {
        return billingActionId;
    }

    public void setBillingActionId(String billingActionId) {
        this.billingActionId = billingActionId;
    }

    public String getBillingStatusId() {
        return billingStatusId;
    }

    public void setBillingStatusId(String billingStatusId) {
        this.billingStatusId = billingStatusId;
    }

    public String getNextBillingDate() {
        return nextBillingDate;
    }

    public void setNextBillingDate(String nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

}