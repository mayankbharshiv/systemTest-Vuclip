
package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "partnerId", "partnerName", "description", "status", "autoRenewalApplicable", "type",
		"stepUpCharging", "userIdentifier", "balanceCheckRequired", "activationManagedBy", "renewalManagedBy",
		"deactivationManagedBy", "hasMoActivations", "hasMoDeactivations", "operationType",
		"partnerActivationConsentParserUrl", "partnerDeactivationConsentParserUrl",
		"partnerActivationConsentInitiationUrl", "partnerDeactivationConsentInitiationUrl" })

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartnerVO {

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
	
//
//	public PartnerVO() {
//		
//	}
//	
//	public PartnerVO(Integer partnerId, String partnerName, String description, String status,
//			Boolean autoRenewalApplicable, String type, Boolean stepUpCharging, String userIdentifier,
//			Boolean balanceCheckRequired, String activationManagedBy, String renewalManagedBy,
//			String deactivationManagedBy, Boolean hasMoActivations, Boolean hasMoDeactivations, String operationType,
//			String partnerActivationConsentParserUrl, String partnerDeactivationConsentParserUrl,
//			String partnerActivationConsentInitiationUrl, String partnerDeactivationConsentInitiationUrl) {
//		super();
//		this.partnerId = partnerId;
//		this.partnerName = partnerName;
//		this.description = description;
//		this.status = status;
//		this.autoRenewalApplicable = autoRenewalApplicable;
//		this.type = type;
//		this.stepUpCharging = stepUpCharging;
//		this.userIdentifier = userIdentifier;
//		this.balanceCheckRequired = balanceCheckRequired;
//		this.activationManagedBy = activationManagedBy;
//		this.renewalManagedBy = renewalManagedBy;
//		this.deactivationManagedBy = deactivationManagedBy;
//		this.hasMoActivations = hasMoActivations;
//		this.hasMoDeactivations = hasMoDeactivations;
//		this.operationType = operationType;
//		this.partnerActivationConsentParserUrl = partnerActivationConsentParserUrl;
//		this.partnerDeactivationConsentParserUrl = partnerDeactivationConsentParserUrl;
//		this.partnerActivationConsentInitiationUrl = partnerActivationConsentInitiationUrl;
//		this.partnerDeactivationConsentInitiationUrl = partnerDeactivationConsentInitiationUrl;
//	}
//	
//	
}

