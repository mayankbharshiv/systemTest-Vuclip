
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "partnerId", "requestedBillingCode", "chargedBillingCode", "attemptedBillingCode",
		"mode", "action", "activity", "transactionId", "transactionState", "chargedPrice", "partnerTransactionId",
		"actionResult", "serviceId", "subscriptionId", "closed", "circleCode", "userSource", "userId", "itemId",
		"errorCode", "errorDesc", "delayed", "itemTypeId", "nextBillingDate", "countryCode" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfirmSyncQueue {

	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("partnerId")
	private Integer partnerId;
	@JsonProperty("requestedBillingCode")
	private String requestedBillingCode;
	@JsonProperty("chargedBillingCode")
	private String chargedBillingCode;
	@JsonProperty("attemptedBillingCode")
	private String attemptedBillingCode;
	@JsonProperty("mode")
	private String mode;
	@JsonProperty("action")
	private Integer action;
	@JsonProperty("activity")
	private Integer activity;
	@JsonProperty("transactionId")
	private String transactionId;
	@JsonProperty("transactionState")
	private Integer transactionState;
	@JsonProperty("chargedPrice")
	private Double chargedPrice;
	@JsonProperty("partnerTransactionId")
	private String partnerTransactionId;
	@JsonProperty("actionResult")
	private String actionResult;
	@JsonProperty("serviceId")
	private String serviceId;
	@JsonProperty("subscriptionId")
	private Long subscriptionId;
	@JsonProperty("closed")
	private Boolean closed;
	@JsonProperty("circleCode")
	private String circleCode;
	@JsonProperty("userSource")
	private String userSource;
	@JsonProperty("userId")
	private String userId;

	@JsonProperty("itemId")
	private String itemId;
	@JsonProperty("errorCode")
	private String errorCode;
	@JsonProperty("errorDesc")
	private String errorDesc;
	@JsonProperty("delayed")
	private boolean delayed;
	@JsonProperty("itemTypeId")
	private String itemTypeId;
	@JsonProperty("nextBillingDate")
	private Long nextBillingDate;

}
