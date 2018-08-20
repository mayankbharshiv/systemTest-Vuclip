
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
    "productId",
    "partnerId",
    "chargingDependOnSmsDelivery",
    "optOutSmsEnabled",
    "preRenewalSmsEnabled",
    "partnerConsentParserEndpoint",
    "partnerConsentUrlGenerationEndpoint",
    "dateFormat",
    "operationType",
    "blacklistApplicable",
    "validityFromPartner",
    "blacklistValidity",
    "cooldownApplicable",
    "cooldownValidity",
    "timeUnit",
    "stepUpChargingApplicable",
    "allowedFreeTrialCount"
})
public class ProductPartnerMapping {

    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("chargingDependOnSmsDelivery")
    private Boolean chargingDependOnSmsDelivery;
    @JsonProperty("optOutSmsEnabled")
    private Boolean optOutSmsEnabled;
    @JsonProperty("preRenewalSmsEnabled")
    private Boolean preRenewalSmsEnabled;
    @JsonProperty("partnerConsentParserEndpoint")
    private String partnerConsentParserEndpoint;
    @JsonProperty("partnerConsentUrlGenerationEndpoint")
    private String partnerConsentUrlGenerationEndpoint;
    @JsonProperty("dateFormat")
    private String dateFormat;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("blacklistApplicable")
    private Boolean blacklistApplicable;
    @JsonProperty("validityFromPartner")
    private Boolean validityFromPartner;
    @JsonProperty("blacklistValidity")
    private Integer blacklistValidity;
    @JsonProperty("cooldownApplicable")
    private Boolean cooldownApplicable;
    @JsonProperty("cooldownValidity")
    private Integer cooldownValidity;
    @JsonProperty("timeUnit")
    private String timeUnit;
    @JsonProperty("stepUpChargingApplicable")
    private Boolean stepUpChargingApplicable;
    @JsonProperty("allowedFreeTrialCount")
    private Integer allowedFreeTrialCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("chargingDependOnSmsDelivery")
    public Boolean getChargingDependOnSmsDelivery() {
        return chargingDependOnSmsDelivery;
    }

    @JsonProperty("chargingDependOnSmsDelivery")
    public void setChargingDependOnSmsDelivery(Boolean chargingDependOnSmsDelivery) {
        this.chargingDependOnSmsDelivery = chargingDependOnSmsDelivery;
    }

    @JsonProperty("optOutSmsEnabled")
    public Boolean getOptOutSmsEnabled() {
        return optOutSmsEnabled;
    }

    @JsonProperty("optOutSmsEnabled")
    public void setOptOutSmsEnabled(Boolean optOutSmsEnabled) {
        this.optOutSmsEnabled = optOutSmsEnabled;
    }

    @JsonProperty("preRenewalSmsEnabled")
    public Boolean getPreRenewalSmsEnabled() {
        return preRenewalSmsEnabled;
    }

    @JsonProperty("preRenewalSmsEnabled")
    public void setPreRenewalSmsEnabled(Boolean preRenewalSmsEnabled) {
        this.preRenewalSmsEnabled = preRenewalSmsEnabled;
    }

    @JsonProperty("partnerConsentParserEndpoint")
    public String getPartnerConsentParserEndpoint() {
        return partnerConsentParserEndpoint;
    }

    @JsonProperty("partnerConsentParserEndpoint")
    public void setPartnerConsentParserEndpoint(String partnerConsentParserEndpoint) {
        this.partnerConsentParserEndpoint = partnerConsentParserEndpoint;
    }

    @JsonProperty("partnerConsentUrlGenerationEndpoint")
    public String getPartnerConsentUrlGenerationEndpoint() {
        return partnerConsentUrlGenerationEndpoint;
    }

    @JsonProperty("partnerConsentUrlGenerationEndpoint")
    public void setPartnerConsentUrlGenerationEndpoint(String partnerConsentUrlGenerationEndpoint) {
        this.partnerConsentUrlGenerationEndpoint = partnerConsentUrlGenerationEndpoint;
    }

    @JsonProperty("dateFormat")
    public String getDateFormat() {
        return dateFormat;
    }

    @JsonProperty("dateFormat")
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @JsonProperty("operationType")
    public String getOperationType() {
        return operationType;
    }

    @JsonProperty("operationType")
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @JsonProperty("blacklistApplicable")
    public Boolean getBlacklistApplicable() {
        return blacklistApplicable;
    }

    @JsonProperty("blacklistApplicable")
    public void setBlacklistApplicable(Boolean blacklistApplicable) {
        this.blacklistApplicable = blacklistApplicable;
    }

    @JsonProperty("validityFromPartner")
    public Boolean getValidityFromPartner() {
        return validityFromPartner;
    }

    @JsonProperty("validityFromPartner")
    public void setValidityFromPartner(Boolean validityFromPartner) {
        this.validityFromPartner = validityFromPartner;
    }

    @JsonProperty("blacklistValidity")
    public Integer getBlacklistValidity() {
        return blacklistValidity;
    }

    @JsonProperty("blacklistValidity")
    public void setBlacklistValidity(Integer blacklistValidity) {
        this.blacklistValidity = blacklistValidity;
    }

    @JsonProperty("cooldownApplicable")
    public Boolean getCooldownApplicable() {
        return cooldownApplicable;
    }

    @JsonProperty("cooldownApplicable")
    public void setCooldownApplicable(Boolean cooldownApplicable) {
        this.cooldownApplicable = cooldownApplicable;
    }

    @JsonProperty("cooldownValidity")
    public Integer getCooldownValidity() {
        return cooldownValidity;
    }

    @JsonProperty("cooldownValidity")
    public void setCooldownValidity(Integer cooldownValidity) {
        this.cooldownValidity = cooldownValidity;
    }

    @JsonProperty("timeUnit")
    public String getTimeUnit() {
        return timeUnit;
    }

    @JsonProperty("timeUnit")
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    @JsonProperty("stepUpChargingApplicable")
    public Boolean getStepUpChargingApplicable() {
        return stepUpChargingApplicable;
    }

    @JsonProperty("stepUpChargingApplicable")
    public void setStepUpChargingApplicable(Boolean stepUpChargingApplicable) {
        this.stepUpChargingApplicable = stepUpChargingApplicable;
    }

    @JsonProperty("allowedFreeTrialCount")
    public Integer getAllowedFreeTrialCount() {
        return allowedFreeTrialCount;
    }

    @JsonProperty("allowedFreeTrialCount")
    public void setAllowedFreeTrialCount(Integer allowedFreeTrialCount) {
        this.allowedFreeTrialCount = allowedFreeTrialCount;
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
