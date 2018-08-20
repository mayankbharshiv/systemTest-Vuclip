package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"partnerId", "partnerName", "description", "status", "autoRenewalApplicable", "type",
        "stepUpCharging", "userIdentifier", "balanceCheckRequired", "activationManagedBy", "renewalManagedBy",
        "deactivationManagedBy", "hasMoActivations", "hasMoDeactivations", "operationType",
        "partnerActivationConsentParserUrl", "partnerDeactivationConsentParserUrl",
        "partnerActivationConsentInitiationUrl", "partnerDeactivationConsentInitiationUrl"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BPSSavePartnerRequest {

    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("partnerName")
    private String partnerName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("autoRenewalApplicable")
    private Boolean autoRenewalApplicable;
    @JsonProperty("type")
    private String type;
    @JsonProperty("stepUpCharging")
    private Boolean stepUpCharging;
    @JsonProperty("userIdentifier")
    private String userIdentifier;
    @JsonProperty("balanceCheckRequired")
    private Boolean balanceCheckRequired;
    @JsonProperty("activationManagedBy")
    private String activationManagedBy;
    @JsonProperty("renewalManagedBy")
    private String renewalManagedBy;
    @JsonProperty("deactivationManagedBy")
    private String deactivationManagedBy;
    @JsonProperty("hasMoActivations")
    private Boolean hasMoActivations;
    @JsonProperty("hasMoDeactivations")
    private Boolean hasMoDeactivations;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("partnerActivationConsentParserUrl")
    private String partnerActivationConsentParserUrl;
    @JsonProperty("partnerDeactivationConsentParserUrl")
    private String partnerDeactivationConsentParserUrl;
    @JsonProperty("partnerActivationConsentInitiationUrl")
    private String partnerActivationConsentInitiationUrl;
    @JsonProperty("partnerDeactivationConsentInitiationUrl")
    private String partnerDeactivationConsentInitiationUrl;

}