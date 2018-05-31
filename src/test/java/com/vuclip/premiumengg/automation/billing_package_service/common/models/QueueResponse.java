package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "partnerId", "countryCode", "activitType", "subscriptionId", "attemptNumber" })
public class QueueResponse {

	@JsonProperty("productId")
	private Object productId;
	@JsonProperty("partnerId")
	private Object partnerId;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("activitType")
	private String activitType;
	@JsonProperty("subscriptionId")
	private Object subscriptionId;
	@JsonProperty("attemptNumber")
	private Object attemptNumber;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public QueueResponse() {
	}

	/**
	 * 
	 * @param countryCode
	 * @param partnerId
	 * @param activitType
	 * @param attemptNumber
	 * @param subscriptionId
	 * @param productId
	 */
	public QueueResponse(Object productId, Object partnerId, String countryCode, String activitType,
			Object subscriptionId, Object attemptNumber) {
		super();
		this.productId = productId;
		this.partnerId = partnerId;
		this.countryCode = countryCode;
		this.activitType = activitType;
		this.subscriptionId = subscriptionId;
		this.attemptNumber = attemptNumber;
	}

	@JsonProperty("productId")
	public Object getProductId() {
		return productId;
	}

	@JsonProperty("productId")
	public void setProductId(Object productId) {
		this.productId = productId;
	}

	@JsonProperty("partnerId")
	public Object getPartnerId() {
		return partnerId;
	}

	@JsonProperty("partnerId")
	public void setPartnerId(Object partnerId) {
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

	@JsonProperty("activitType")
	public String getActivitType() {
		return activitType;
	}

	@JsonProperty("activitType")
	public void setActivitType(String activitType) {
		this.activitType = activitType;
	}

	@JsonProperty("subscriptionId")
	public Object getSubscriptionId() {
		return subscriptionId;
	}

	@JsonProperty("subscriptionId")
	public void setSubscriptionId(Object subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@JsonProperty("attemptNumber")
	public Object getAttemptNumber() {
		return attemptNumber;
	}

	@JsonProperty("attemptNumber")
	public void setAttemptNumber(Object attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	@Override
	public String toString() {
		return "QueueResponse [productId=" + productId + ", partnerId=" + partnerId + ", countryCode=" + countryCode
				+ ", activitType=" + activitType + ", subscriptionId=" + subscriptionId + ", attemptNumber="
				+ attemptNumber + "]";
	}

}