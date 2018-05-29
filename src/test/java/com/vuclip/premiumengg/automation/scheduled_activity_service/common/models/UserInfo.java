
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "billingUserId",
    "msisdn",
    "clientUserId",
    "userSource",
    "userPreferredLanguage",
    "freeTrialUser"
})
public class UserInfo {

    @JsonProperty("billingUserId")
    private String billingUserId;
    @JsonProperty("msisdn")
    private Object msisdn;
    @JsonProperty("clientUserId")
    private String clientUserId;
    @JsonProperty("userSource")
    private String userSource;
    @JsonProperty("userPreferredLanguage")
    private String userPreferredLanguage;
    @JsonProperty("freeTrialUser")
    private Boolean freeTrialUser;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserInfo() {
    }

    /**
     * 
     * @param freeTrialUser
     * @param userPreferredLanguage
     * @param billingUserId
     * @param msisdn
     * @param userSource
     * @param clientUserId
     */
    public UserInfo(String billingUserId, Object msisdn, String clientUserId, String userSource, String userPreferredLanguage, Boolean freeTrialUser) {
        super();
        this.billingUserId = billingUserId;
        this.msisdn = msisdn;
        this.clientUserId = clientUserId;
        this.userSource = userSource;
        this.userPreferredLanguage = userPreferredLanguage;
        this.freeTrialUser = freeTrialUser;
    }

    @JsonProperty("billingUserId")
    public String getBillingUserId() {
        return billingUserId;
    }

    @JsonProperty("billingUserId")
    public void setBillingUserId(String billingUserId) {
        this.billingUserId = billingUserId;
    }

    @JsonProperty("msisdn")
    public Object getMsisdn() {
        return msisdn;
    }

    @JsonProperty("msisdn")
    public void setMsisdn(Object msisdn) {
        this.msisdn = msisdn;
    }

    @JsonProperty("clientUserId")
    public String getClientUserId() {
        return clientUserId;
    }

    @JsonProperty("clientUserId")
    public void setClientUserId(String clientUserId) {
        this.clientUserId = clientUserId;
    }

    @JsonProperty("userSource")
    public String getUserSource() {
        return userSource;
    }

    @JsonProperty("userSource")
    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    @JsonProperty("userPreferredLanguage")
    public String getUserPreferredLanguage() {
        return userPreferredLanguage;
    }

    @JsonProperty("userPreferredLanguage")
    public void setUserPreferredLanguage(String userPreferredLanguage) {
        this.userPreferredLanguage = userPreferredLanguage;
    }

    @JsonProperty("freeTrialUser")
    public Boolean getFreeTrialUser() {
        return freeTrialUser;
    }

    @JsonProperty("freeTrialUser")
    public void setFreeTrialUser(Boolean freeTrialUser) {
        this.freeTrialUser = freeTrialUser;
    }

}
