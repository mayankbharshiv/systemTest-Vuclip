
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
@JsonPropertyOrder({
    "productId",
    "partnerId",
    "subscriptionBillingCode",
    "chargedBillingCode",
    "fallbackBillingCode",
    "mode",
    "actionId",
    "activityId",	
    "transactionId",
    "transactionStateId",
    "chargedPrice",
    "partnerTransactionId",
    "actionResult",
    "serviceId",
    "subscriptionId",
    "closed",
    "circleCode",
    "userSource",
    "userId",
    "clientUserId",
    "itemId",
    "errorCode",
    "errorDesc",
    "delayed",
    "itemTypeId",
    "nextBillingDate",
    "countryCode"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfirmSync {

	 @JsonProperty("countryCode")
	    private String countryCode;
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
    @JsonProperty("actionId")
    private Integer actionId;
    @JsonProperty("activityId")
    private Integer activityId;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("transactionStateId")
    private Integer transactionStateId;
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
    @JsonProperty("clientUserId")
    private String clientUserId;
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

//	@Override
//	public String toString() {
//		return "ConfirmSync [productId=" + productId + ", partnerId=" + partnerId + ", subscriptionBillingCode="
//				+ subscriptionBillingCode + ", chargedBillingCode=" + chargedBillingCode + ", fallbackBillingCode="
//				+ fallbackBillingCode + ", mode=" + mode + ", actionId=" + actionId + ", activityId=" + activityId
//				+ ", transactionId=" + transactionId + ", transactionStateId=" + transactionStateId + ", chargedPrice="
//				+ chargedPrice + ", partnerTransactionId=" + partnerTransactionId + ", actionResult=" + actionResult
//				+ ", serviceId=" + serviceId + ", subscriptionId=" + subscriptionId + ", closed=" + closed
//				+ ", circleCode=" + circleCode + ", userSource=" + userSource + ", userId=" + userId + ", clientUserId="
//				+ clientUserId + ", itemId=" + itemId + ", errorCode=" + errorCode + ", errorDesc=" + errorDesc
//				+ ", delayed=" + delayed + ", itemTypeId=" + itemTypeId + ", nextBillingDate=" + nextBillingDate
//				+ ", additionalProperties=" + additionalProperties + "]";
//	}

    
}
