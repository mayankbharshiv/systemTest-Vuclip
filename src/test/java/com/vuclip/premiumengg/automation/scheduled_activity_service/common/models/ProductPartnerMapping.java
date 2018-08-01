package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"productId", "partnerId", "chargingDependOnSmsDelivery", "optOutSmsEnabled",
        "preRenewalSmsEnabled", "partnerConsentParserEndpoint", "partnerConsentUrlGenerationEndpoint", "dateFormat",
        "operationType", "blacklistApplicable", "validityFromPartner", "blacklistValidity", "cooldownApplicable",
        "cooldownValidity", "timeUnit", "stepUpChargingApplicable", "allowedFreeTrialCount"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
