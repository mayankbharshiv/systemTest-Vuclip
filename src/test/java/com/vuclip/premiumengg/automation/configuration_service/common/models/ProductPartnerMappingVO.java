package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"operationType"})
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "productName",
    "partnerName",
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
public class ProductPartnerMappingVO {

    @JsonProperty("productName")
    private String productName;
    @JsonProperty("partnerName")
    private String partnerName;
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
    private TimeUnit timeUnit;
    @JsonProperty("stepUpChargingApplicable")
    private Boolean stepUpChargingApplicable;
    @JsonProperty("allowedFreeTrialCount")
    private Integer allowedFreeTrialCount;

}