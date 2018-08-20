
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "billingCode",
    "price",
    "description",
    "validity",
    "noOfCredits",
    "serviceId",
    "appId",
    "ujId",
    "itemId",
    "itemTypeId",
    "balanceCheckRequired",
    "fallbackApplicable",
    "freeTrialApplicable",
    "fallbackPpBillingCode",
    "freeTrialBillingCode",
    "freeTrialDays",
    "exclusionPeriod",
    "autoRenewalApplicable",
    "status",
    "contentAccessPostDeactivation",
    "noOfDaysContentAccessAllowInParking",
    "noOfDaysContentAccessAllowInSuspend",
    "parkingPeriod",
    "suspendPeriod",
    "activationCoolDownPeriod",
    "productId",
    "partnerId",
    "countryCode",
    "operationType",
    "actInitValidity",
    "deactivationRetryValidity",
    "notificationWaitPeriod",
    "timeUnit"
})
public class PricePoint {

    @JsonProperty("billingCode")
    private String billingCode;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("validity")
    private Integer validity;
    @JsonProperty("noOfCredits")
    private Integer noOfCredits;
    @JsonProperty("serviceId")
    private String serviceId;
    @JsonProperty("appId")
    private Integer appId;
    @JsonProperty("ujId")
    private Integer ujId;
    @JsonProperty("itemId")
    private Integer itemId;
    @JsonProperty("itemTypeId")
    private Integer itemTypeId;
    @JsonProperty("balanceCheckRequired")
    private Boolean balanceCheckRequired;
    @JsonProperty("fallbackApplicable")
    private Boolean fallbackApplicable;
    @JsonProperty("freeTrialApplicable")
    private Boolean freeTrialApplicable;
    @JsonProperty("fallbackPpBillingCode")
    private String fallbackPpBillingCode;
    @JsonProperty("freeTrialBillingCode")
    private String freeTrialBillingCode;
    @JsonProperty("freeTrialDays")
    private Integer freeTrialDays;
    @JsonProperty("exclusionPeriod")
    private Integer exclusionPeriod;
    @JsonProperty("autoRenewalApplicable")
    private Boolean autoRenewalApplicable;
    @JsonProperty("status")
    private String status;
    @JsonProperty("contentAccessPostDeactivation")
    private Boolean contentAccessPostDeactivation;
    @JsonProperty("noOfDaysContentAccessAllowInParking")
    private Integer noOfDaysContentAccessAllowInParking;
    @JsonProperty("noOfDaysContentAccessAllowInSuspend")
    private Integer noOfDaysContentAccessAllowInSuspend;
    @JsonProperty("parkingPeriod")
    private Integer parkingPeriod;
    @JsonProperty("suspendPeriod")
    private Integer suspendPeriod;
    @JsonProperty("activationCoolDownPeriod")
    private Integer activationCoolDownPeriod;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("actInitValidity")
    private Integer actInitValidity;
    @JsonProperty("deactivationRetryValidity")
    private Integer deactivationRetryValidity;
    @JsonProperty("notificationWaitPeriod")
    private Integer notificationWaitPeriod;
    @JsonProperty("timeUnit")
    private String timeUnit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("billingCode")
    public String getBillingCode() {
        return billingCode;
    }

    @JsonProperty("billingCode")
    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("validity")
    public Integer getValidity() {
        return validity;
    }

    @JsonProperty("validity")
    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    @JsonProperty("noOfCredits")
    public Integer getNoOfCredits() {
        return noOfCredits;
    }

    @JsonProperty("noOfCredits")
    public void setNoOfCredits(Integer noOfCredits) {
        this.noOfCredits = noOfCredits;
    }

    @JsonProperty("serviceId")
    public String getServiceId() {
        return serviceId;
    }

    @JsonProperty("serviceId")
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @JsonProperty("appId")
    public Integer getAppId() {
        return appId;
    }

    @JsonProperty("appId")
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @JsonProperty("ujId")
    public Integer getUjId() {
        return ujId;
    }

    @JsonProperty("ujId")
    public void setUjId(Integer ujId) {
        this.ujId = ujId;
    }

    @JsonProperty("itemId")
    public Integer getItemId() {
        return itemId;
    }

    @JsonProperty("itemId")
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("itemTypeId")
    public Integer getItemTypeId() {
        return itemTypeId;
    }

    @JsonProperty("itemTypeId")
    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    @JsonProperty("balanceCheckRequired")
    public Boolean getBalanceCheckRequired() {
        return balanceCheckRequired;
    }

    @JsonProperty("balanceCheckRequired")
    public void setBalanceCheckRequired(Boolean balanceCheckRequired) {
        this.balanceCheckRequired = balanceCheckRequired;
    }

    @JsonProperty("fallbackApplicable")
    public Boolean getFallbackApplicable() {
        return fallbackApplicable;
    }

    @JsonProperty("fallbackApplicable")
    public void setFallbackApplicable(Boolean fallbackApplicable) {
        this.fallbackApplicable = fallbackApplicable;
    }

    @JsonProperty("freeTrialApplicable")
    public Boolean getFreeTrialApplicable() {
        return freeTrialApplicable;
    }

    @JsonProperty("freeTrialApplicable")
    public void setFreeTrialApplicable(Boolean freeTrialApplicable) {
        this.freeTrialApplicable = freeTrialApplicable;
    }

    @JsonProperty("fallbackPpBillingCode")
    public String getFallbackPpBillingCode() {
        return fallbackPpBillingCode;
    }

    @JsonProperty("fallbackPpBillingCode")
    public void setFallbackPpBillingCode(String fallbackPpBillingCode) {
        this.fallbackPpBillingCode = fallbackPpBillingCode;
    }

    @JsonProperty("freeTrialBillingCode")
    public String getFreeTrialBillingCode() {
        return freeTrialBillingCode;
    }

    @JsonProperty("freeTrialBillingCode")
    public void setFreeTrialBillingCode(String freeTrialBillingCode) {
        this.freeTrialBillingCode = freeTrialBillingCode;
    }

    @JsonProperty("freeTrialDays")
    public Integer getFreeTrialDays() {
        return freeTrialDays;
    }

    @JsonProperty("freeTrialDays")
    public void setFreeTrialDays(Integer freeTrialDays) {
        this.freeTrialDays = freeTrialDays;
    }

    @JsonProperty("exclusionPeriod")
    public Integer getExclusionPeriod() {
        return exclusionPeriod;
    }

    @JsonProperty("exclusionPeriod")
    public void setExclusionPeriod(Integer exclusionPeriod) {
        this.exclusionPeriod = exclusionPeriod;
    }

    @JsonProperty("autoRenewalApplicable")
    public Boolean getAutoRenewalApplicable() {
        return autoRenewalApplicable;
    }

    @JsonProperty("autoRenewalApplicable")
    public void setAutoRenewalApplicable(Boolean autoRenewalApplicable) {
        this.autoRenewalApplicable = autoRenewalApplicable;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("contentAccessPostDeactivation")
    public Boolean getContentAccessPostDeactivation() {
        return contentAccessPostDeactivation;
    }

    @JsonProperty("contentAccessPostDeactivation")
    public void setContentAccessPostDeactivation(Boolean contentAccessPostDeactivation) {
        this.contentAccessPostDeactivation = contentAccessPostDeactivation;
    }

    @JsonProperty("noOfDaysContentAccessAllowInParking")
    public Integer getNoOfDaysContentAccessAllowInParking() {
        return noOfDaysContentAccessAllowInParking;
    }

    @JsonProperty("noOfDaysContentAccessAllowInParking")
    public void setNoOfDaysContentAccessAllowInParking(Integer noOfDaysContentAccessAllowInParking) {
        this.noOfDaysContentAccessAllowInParking = noOfDaysContentAccessAllowInParking;
    }

    @JsonProperty("noOfDaysContentAccessAllowInSuspend")
    public Integer getNoOfDaysContentAccessAllowInSuspend() {
        return noOfDaysContentAccessAllowInSuspend;
    }

    @JsonProperty("noOfDaysContentAccessAllowInSuspend")
    public void setNoOfDaysContentAccessAllowInSuspend(Integer noOfDaysContentAccessAllowInSuspend) {
        this.noOfDaysContentAccessAllowInSuspend = noOfDaysContentAccessAllowInSuspend;
    }

    @JsonProperty("parkingPeriod")
    public Integer getParkingPeriod() {
        return parkingPeriod;
    }

    @JsonProperty("parkingPeriod")
    public void setParkingPeriod(Integer parkingPeriod) {
        this.parkingPeriod = parkingPeriod;
    }

    @JsonProperty("suspendPeriod")
    public Integer getSuspendPeriod() {
        return suspendPeriod;
    }

    @JsonProperty("suspendPeriod")
    public void setSuspendPeriod(Integer suspendPeriod) {
        this.suspendPeriod = suspendPeriod;
    }

    @JsonProperty("activationCoolDownPeriod")
    public Integer getActivationCoolDownPeriod() {
        return activationCoolDownPeriod;
    }

    @JsonProperty("activationCoolDownPeriod")
    public void setActivationCoolDownPeriod(Integer activationCoolDownPeriod) {
        this.activationCoolDownPeriod = activationCoolDownPeriod;
    }

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("partnerId")
    public Integer getPartnerId() {
        return partnerId;
    }

    @JsonProperty("partnerId")
    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("operationType")
    public String getOperationType() {
        return operationType;
    }

    @JsonProperty("operationType")
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @JsonProperty("actInitValidity")
    public Integer getActInitValidity() {
        return actInitValidity;
    }

    @JsonProperty("actInitValidity")
    public void setActInitValidity(Integer actInitValidity) {
        this.actInitValidity = actInitValidity;
    }

    @JsonProperty("deactivationRetryValidity")
    public Integer getDeactivationRetryValidity() {
        return deactivationRetryValidity;
    }

    @JsonProperty("deactivationRetryValidity")
    public void setDeactivationRetryValidity(Integer deactivationRetryValidity) {
        this.deactivationRetryValidity = deactivationRetryValidity;
    }

    @JsonProperty("notificationWaitPeriod")
    public Integer getNotificationWaitPeriod() {
        return notificationWaitPeriod;
    }

    @JsonProperty("notificationWaitPeriod")
    public void setNotificationWaitPeriod(Integer notificationWaitPeriod) {
        this.notificationWaitPeriod = notificationWaitPeriod;
    }

    @JsonProperty("timeUnit")
    public String getTimeUnit() {
        return timeUnit;
    }

    @JsonProperty("timeUnit")
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
