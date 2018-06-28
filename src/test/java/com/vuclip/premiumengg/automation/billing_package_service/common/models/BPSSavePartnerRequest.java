package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "partnerId", "partnerName", "description", "status", "autoRenewalApplicable", "type",
		"stepUpCharging", "userIdentifier", "balanceCheckRequired", "activationManagedBy", "renewalManagedBy",
		"deactivationManagedBy", "hasMoActivations", "hasMoDeactivations", "operationType",
		"partnerActivationConsentParserUrl", "partnerDeactivationConsentParserUrl",
		"partnerActivationConsentInitiationUrl", "partnerDeactivationConsentInitiationUrl" })
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

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public BPSSavePartnerRequest() {
	}

	/**
	 * 
	 * @param partnerName
	 * @param autoRenewalApplicable
	 * @param balanceCheckRequired
	 * @param partnerActivationConsentInitiationUrl
	 * @param status
	 * @param userIdentifier
	 * @param partnerActivationConsentParserUrl
	 * @param partnerId
	 * @param renewalManagedBy
	 * @param partnerDeactivationConsentInitiationUrl
	 * @param stepUpCharging
	 * @param type
	 * @param hasMoActivations
	 * @param activationManagedBy
	 * @param hasMoDeactivations
	 * @param operationType
	 * @param description
	 * @param partnerDeactivationConsentParserUrl
	 * @param deactivationManagedBy
	 */
	public BPSSavePartnerRequest(Integer partnerId, String partnerName, String description, String status,
			Boolean autoRenewalApplicable, String type, Boolean stepUpCharging, String userIdentifier,
			Boolean balanceCheckRequired, String activationManagedBy, String renewalManagedBy,
			String deactivationManagedBy, Boolean hasMoActivations, Boolean hasMoDeactivations, String operationType,
			String partnerActivationConsentParserUrl, String partnerDeactivationConsentParserUrl,
			String partnerActivationConsentInitiationUrl, String partnerDeactivationConsentInitiationUrl) {
		super();
		this.partnerId = partnerId;
		this.partnerName = partnerName;
		this.description = description;
		this.status = status;
		this.autoRenewalApplicable = autoRenewalApplicable;
		this.type = type;
		this.stepUpCharging = stepUpCharging;
		this.userIdentifier = userIdentifier;
		this.balanceCheckRequired = balanceCheckRequired;
		this.activationManagedBy = activationManagedBy;
		this.renewalManagedBy = renewalManagedBy;
		this.deactivationManagedBy = deactivationManagedBy;
		this.hasMoActivations = hasMoActivations;
		this.hasMoDeactivations = hasMoDeactivations;
		this.operationType = operationType;
		this.partnerActivationConsentParserUrl = partnerActivationConsentParserUrl;
		this.partnerDeactivationConsentParserUrl = partnerDeactivationConsentParserUrl;
		this.partnerActivationConsentInitiationUrl = partnerActivationConsentInitiationUrl;
		this.partnerDeactivationConsentInitiationUrl = partnerDeactivationConsentInitiationUrl;
	}

	@JsonProperty("partnerId")
	public Integer getPartnerId() {
		return partnerId;
	}

	@JsonProperty("partnerId")
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	@JsonProperty("partnerName")
	public String getPartnerName() {
		return partnerName;
	}

	@JsonProperty("partnerName")
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("autoRenewalApplicable")
	public Boolean getAutoRenewalApplicable() {
		return autoRenewalApplicable;
	}

	@JsonProperty("autoRenewalApplicable")
	public void setAutoRenewalApplicable(Boolean autoRenewalApplicable) {
		this.autoRenewalApplicable = autoRenewalApplicable;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("stepUpCharging")
	public Boolean getStepUpCharging() {
		return stepUpCharging;
	}

	@JsonProperty("stepUpCharging")
	public void setStepUpCharging(Boolean stepUpCharging) {
		this.stepUpCharging = stepUpCharging;
	}

	@JsonProperty("userIdentifier")
	public String getUserIdentifier() {
		return userIdentifier;
	}

	@JsonProperty("userIdentifier")
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	@JsonProperty("balanceCheckRequired")
	public Boolean getBalanceCheckRequired() {
		return balanceCheckRequired;
	}

	@JsonProperty("balanceCheckRequired")
	public void setBalanceCheckRequired(Boolean balanceCheckRequired) {
		this.balanceCheckRequired = balanceCheckRequired;
	}

	@JsonProperty("activationManagedBy")
	public String getActivationManagedBy() {
		return activationManagedBy;
	}

	@JsonProperty("activationManagedBy")
	public void setActivationManagedBy(String activationManagedBy) {
		this.activationManagedBy = activationManagedBy;
	}

	@JsonProperty("renewalManagedBy")
	public String getRenewalManagedBy() {
		return renewalManagedBy;
	}

	@JsonProperty("renewalManagedBy")
	public void setRenewalManagedBy(String renewalManagedBy) {
		this.renewalManagedBy = renewalManagedBy;
	}

	@JsonProperty("deactivationManagedBy")
	public String getDeactivationManagedBy() {
		return deactivationManagedBy;
	}

	@JsonProperty("deactivationManagedBy")
	public void setDeactivationManagedBy(String deactivationManagedBy) {
		this.deactivationManagedBy = deactivationManagedBy;
	}

	@JsonProperty("hasMoActivations")
	public Boolean getHasMoActivations() {
		return hasMoActivations;
	}

	@JsonProperty("hasMoActivations")
	public void setHasMoActivations(Boolean hasMoActivations) {
		this.hasMoActivations = hasMoActivations;
	}

	@JsonProperty("hasMoDeactivations")
	public Boolean getHasMoDeactivations() {
		return hasMoDeactivations;
	}

	@JsonProperty("hasMoDeactivations")
	public void setHasMoDeactivations(Boolean hasMoDeactivations) {
		this.hasMoDeactivations = hasMoDeactivations;
	}

	@JsonProperty("operationType")
	public String getOperationType() {
		return operationType;
	}

	@JsonProperty("operationType")
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	@JsonProperty("partnerActivationConsentParserUrl")
	public String getPartnerActivationConsentParserUrl() {
		return partnerActivationConsentParserUrl;
	}

	@JsonProperty("partnerActivationConsentParserUrl")
	public void setPartnerActivationConsentParserUrl(String partnerActivationConsentParserUrl) {
		this.partnerActivationConsentParserUrl = partnerActivationConsentParserUrl;
	}

	@JsonProperty("partnerDeactivationConsentParserUrl")
	public String getPartnerDeactivationConsentParserUrl() {
		return partnerDeactivationConsentParserUrl;
	}

	@JsonProperty("partnerDeactivationConsentParserUrl")
	public void setPartnerDeactivationConsentParserUrl(String partnerDeactivationConsentParserUrl) {
		this.partnerDeactivationConsentParserUrl = partnerDeactivationConsentParserUrl;
	}

	@JsonProperty("partnerActivationConsentInitiationUrl")
	public String getPartnerActivationConsentInitiationUrl() {
		return partnerActivationConsentInitiationUrl;
	}

	@JsonProperty("partnerActivationConsentInitiationUrl")
	public void setPartnerActivationConsentInitiationUrl(String partnerActivationConsentInitiationUrl) {
		this.partnerActivationConsentInitiationUrl = partnerActivationConsentInitiationUrl;
	}

	@JsonProperty("partnerDeactivationConsentInitiationUrl")
	public String getPartnerDeactivationConsentInitiationUrl() {
		return partnerDeactivationConsentInitiationUrl;
	}

	@JsonProperty("partnerDeactivationConsentInitiationUrl")
	public void setPartnerDeactivationConsentInitiationUrl(String partnerDeactivationConsentInitiationUrl) {
		this.partnerDeactivationConsentInitiationUrl = partnerDeactivationConsentInitiationUrl;
	}

}