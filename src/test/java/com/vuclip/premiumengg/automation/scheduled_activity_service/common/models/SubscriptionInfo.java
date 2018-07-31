package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "subscriptionId", "subscriptionStatus", "startDate", "endDate", "activationDate",
		"deactivationDate", "subscriptionBillingCode", "chargedBillingCode", "fallbackBillingCode", "mode",
		"circleCode", "customerTransactionId", "chargedPrice", "partnerId", "productId", "itemId", "itemTypeId",
		"nextBillingDate", "serviceId", "isDelayed", "paid", "country" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubscriptionInfo {

	@JsonProperty("subscriptionId")
	private Integer subscriptionId;
	@JsonProperty("subscriptionStatus")
	private String subscriptionStatus;
	@JsonProperty("startDate")
	private long startDate;
	@JsonProperty("endDate")
	private BigInteger endDate;
	@JsonProperty("activationDate")
	private long activationDate;
	@JsonProperty("deactivationDate")
	private long deactivationDate;
	@JsonProperty("subscriptionBillingCode")
	private String subscriptionBillingCode;
	@JsonProperty("chargedBillingCode")
	private String chargedBillingCode;
	@JsonProperty("fallbackBillingCode")
	private Object fallbackBillingCode;
	@JsonProperty("mode")
	private String mode;
	@JsonProperty("circleCode")
	private Object circleCode;
	@JsonProperty("customerTransactionId")
	private String customerTransactionId;
	@JsonProperty("chargedPrice")
	private Double chargedPrice;
	@JsonProperty("partnerId")
	private Integer partnerId;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("itemId")
	private Integer itemId;
	@JsonProperty("itemTypeId")
	private Integer itemTypeId;
	@JsonProperty("nextBillingDate")
	private BigInteger nextBillingDate;
	@JsonProperty("serviceId")
	private String serviceId;
	@JsonProperty("isDelayed")
	private Boolean isDelayed;
	@JsonProperty("paid")
	private Boolean paid;
	@JsonProperty("country")
	private String country;

}
