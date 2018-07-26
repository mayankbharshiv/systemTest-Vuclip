
package com.vuclip.premiumengg.automation.schedular_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "retryId", "productId", "partnerId", "countryCode", "activityType", "schedulingFrequencyInMinutes",
		"executingTimeWindow", "executingDays", "status" })
public class Retry {

	@JsonProperty("retryId")
	private Integer retryId;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("partnerId")
	private Integer partnerId;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("activityType")
	private String activityType;
	@JsonProperty("schedulingFrequencyInMinutes")
	private Integer schedulingFrequencyInMinutes;
	@JsonProperty("executingTimeWindow")
	private String executingTimeWindow;
	@JsonProperty("executingDays")
	private String executingDays;
	@JsonProperty("status")
	private String status;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Retry() {
	}

	/**
	 * 
	 * @param executingTimeWindow
	 * @param status
	 * @param schedulingFrequencyInMinutes
	 * @param retryId
	 * @param countryCode
	 * @param partnerId
	 * @param executingDays
	 * @param activityType
	 * @param productId
	 */
	public Retry(Integer retryId, Integer productId, Integer partnerId, String countryCode, String activityType,
			Integer schedulingFrequencyInMinutes, String executingTimeWindow, String executingDays, String status) {
		super();
		this.retryId = retryId;
		this.productId = productId;
		this.partnerId = partnerId;
		this.countryCode = countryCode;
		this.activityType = activityType;
		this.schedulingFrequencyInMinutes = schedulingFrequencyInMinutes;
		this.executingTimeWindow = executingTimeWindow;
		this.executingDays = executingDays;
		this.status = status;
	}

	@JsonProperty("retryId")
	public Integer getRetryId() {
		return retryId;
	}

	@JsonProperty("retryId")
	public void setRetryId(Integer retryId) {
		this.retryId = retryId;
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

	@JsonProperty("activityType")
	public String getActivityType() {
		return activityType;
	}

	@JsonProperty("activityType")
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@JsonProperty("schedulingFrequencyInMinutes")
	public Integer getSchedulingFrequencyInMinutes() {
		return schedulingFrequencyInMinutes;
	}

	@JsonProperty("schedulingFrequencyInMinutes")
	public void setSchedulingFrequencyInMinutes(Integer schedulingFrequencyInMinutes) {
		this.schedulingFrequencyInMinutes = schedulingFrequencyInMinutes;
	}

	@JsonProperty("executingTimeWindow")
	public String getExecutingTimeWindow() {
		return executingTimeWindow;
	}

	@JsonProperty("executingTimeWindow")
	public void setExecutingTimeWindow(String executingTimeWindow) {
		this.executingTimeWindow = executingTimeWindow;
	}

	@JsonProperty("executingDays")
	public String getExecutingDays() {
		return executingDays;
	}

	@JsonProperty("executingDays")
	public void setExecutingDays(String executingDays) {
		this.executingDays = executingDays;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

}
