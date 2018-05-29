
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

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
    "validityFromPartner",
    "blacklistValidity",
    "cooldownApplicable",
    "cooldownValidity",
    "timeUnit",
    "stepUpChargingApplicable",
    "allowedFreeTrialCount"
})
public class PublishProductPartnerMapping {

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

    /**
     * No args constructor for use in serialization
     * 
     */
    public PublishProductPartnerMapping() {
    }

    /**
     * 
     * @param partnerConsentParserEndpoint
     * @param partnerId
     * @param chargingDependOnSmsDelivery
     * @param cooldownValidity
     * @param preRenewalSmsEnabled
     * @param productId
     * @param optOutSmsEnabled
     * @param partnerConsentUrlGenerationEndpoint
     * @param cooldownApplicable
     * @param blacklistValidity
     * @param validityFromPartner
     * @param stepUpChargingApplicable
     * @param timeUnit
     * @param allowedFreeTrialCount
     * @param dateFormat
     */
    public PublishProductPartnerMapping(Integer productId, Integer partnerId, Boolean chargingDependOnSmsDelivery, Boolean optOutSmsEnabled, Boolean preRenewalSmsEnabled, String partnerConsentParserEndpoint, String partnerConsentUrlGenerationEndpoint, String dateFormat, Boolean validityFromPartner, Integer blacklistValidity, Boolean cooldownApplicable, Integer cooldownValidity, String timeUnit, Boolean stepUpChargingApplicable, Integer allowedFreeTrialCount) {
        super();
        this.productId = productId;
        this.partnerId = partnerId;
        this.chargingDependOnSmsDelivery = chargingDependOnSmsDelivery;
        this.optOutSmsEnabled = optOutSmsEnabled;
        this.preRenewalSmsEnabled = preRenewalSmsEnabled;
        this.partnerConsentParserEndpoint = partnerConsentParserEndpoint;
        this.partnerConsentUrlGenerationEndpoint = partnerConsentUrlGenerationEndpoint;
        this.dateFormat = dateFormat;
        this.validityFromPartner = validityFromPartner;
        this.blacklistValidity = blacklistValidity;
        this.cooldownApplicable = cooldownApplicable;
        this.cooldownValidity = cooldownValidity;
        this.timeUnit = timeUnit;
        this.stepUpChargingApplicable = stepUpChargingApplicable;
        this.allowedFreeTrialCount = allowedFreeTrialCount;
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

}
