
package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "retryId", "productId", "partnerId", "countryCode", "operationType", "activityType",
		"maxRetryCount", "retryIntervalInMinutes", "attemptWindow", "typeOfCycle", "batchSize",
		"schedulingFrequencyInMinutes", "schedulingDays", "executingTimeWindow", "executingDays", "status",
		"actionDefaultEiligible", "retryable" })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Retry {

	@JsonProperty("retryId")
	private Integer retryId;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("partnerId")
	private Integer partnerId;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("operationType")
	private String operationType;
	@JsonProperty("activityType")
	private String activityType;
	@JsonProperty("maxRetryCount")
	private Integer maxRetryCount;
	@JsonProperty("retryIntervalInMinutes")
	private Integer retryIntervalInMinutes;
	@JsonProperty("attemptWindow")
	private String attemptWindow;
	@JsonProperty("typeOfCycle")
	private String typeOfCycle;
	@JsonProperty("batchSize")
	private Integer batchSize;
	@JsonProperty("schedulingFrequencyInMinutes")
	private Integer schedulingFrequencyInMinutes;
	@JsonProperty("schedulingDays")
	private String schedulingDays;
	@JsonProperty("executingTimeWindow")
	private String executingTimeWindow;
	@JsonProperty("executingDays")
	private String executingDays;
	@JsonProperty("status")
	private String status;
	@JsonProperty("actionDefaultEiligible")
	private Boolean actionDefaultEiligible;
	@JsonProperty("retryable")
	private Boolean retryable;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}