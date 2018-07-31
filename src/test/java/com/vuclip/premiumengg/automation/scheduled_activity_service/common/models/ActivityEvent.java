package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "eventType", "userId", "productId", "partnerId", "subscriptionBillingCode", "chargedBillingCode",
		"fallbackBillingCode", "mode", "action", "activity", "transactionId", "transactionState", "chargedPrice",
		"partnerTransactionId", "itemId", "itemTypeId", "actionResult", "serviceId", "subscriptionId", "circleCode",
		"errorCode", "errorDesc", "delayed", "closed", "customerTransactionId", "userPreferredLanguage", "userSource",
		"nextBillingDate" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityEvent {

	@JsonProperty("eventType")
	private String eventType;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("partnerId")
	private Integer partnerId;
	@JsonProperty("subscriptionBillingCode")
	private String subscriptionBillingCode;
	@JsonProperty("chargedBillingCode")
	private String chargedBillingCode;
	@JsonProperty("fallbackBillingCode")
	private String fallbackBillingCode;
	@JsonProperty("mode")
	private String mode;
	@JsonProperty("action")
	private String action;
	@JsonProperty("activity")
	private Object activity;
	@JsonProperty("transactionId")
	private String transactionId;
	@JsonProperty("transactionState")
	private String transactionState;
	@JsonProperty("chargedPrice")
	private Double chargedPrice;
	@JsonProperty("partnerTransactionId")
	private Object partnerTransactionId;
	@JsonProperty("itemId")
	private Integer itemId;
	@JsonProperty("itemTypeId")
	private Integer itemTypeId;
	@JsonProperty("actionResult")
	private String actionResult;
	@JsonProperty("serviceId")
	private String serviceId;
	@JsonProperty("subscriptionId")
	private Integer subscriptionId;
	@JsonProperty("circleCode")
	private Object circleCode;
	@JsonProperty("errorCode")
	private String errorCode;
	@JsonProperty("errorDesc")
	private String errorDesc;
	@JsonProperty("delayed")
	private Boolean delayed;
	@JsonProperty("closed")
	private Boolean closed;
	@JsonProperty("customerTransactionId")
	private String customerTransactionId;
	@JsonProperty("userPreferredLanguage")
	private String userPreferredLanguage;
	@JsonProperty("userSource")
	private String userSource;
	@JsonProperty("nextBillingDate")
	private BigInteger nextBillingDate;

}
