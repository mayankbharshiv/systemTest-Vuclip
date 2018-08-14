
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "eventTimeStamp",
    "userId",
    "msisdn",
    "productId",
    "productName",
    "partnerId",
    "partnerName",
    "requestedBillingCode",
    "requestedPrice",
    "attemptedBillingCode",
    "attemptedPrice",
    "chargedBillingCode",
    "chargedPrice",
    "currency",
    "mode",
    "action",
    "activity",
    "transactionId",
    "transactionState",
    "partnerTransactionId",
    "itemId",
    "itemTypeId",
    "actionResult",
    "serviceId",
    "subscriptionId",
    "circleCode",
    "countryCode",
    "errorCode",
    "errorDesc",
    "delayed",
    "closed",
    "customerTransactionId",
    "userPreferredLanguage",
    "userSource",
    "nextBillingDate",
    "adNetworkParams",
    "churnNotificationParam",
    "activationDate"
})
@Getter
@Setter
@ToString
public class RabbitMessage {


    @JsonProperty("userId")
    private String userId;
    @JsonProperty("msisdn")
    private String msisdn;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("partnerName")
    private String partnerName;
    @JsonProperty("requestedBillingCode")
    private String requestedBillingCode;
    @JsonProperty("requestedPrice")
    private String requestedPrice;
    @JsonProperty("attemptedBillingCode")
    private String attemptedBillingCode;
    @JsonProperty("attemptedPrice")
    private String attemptedPrice;
    @JsonProperty("chargedBillingCode")
    private Object chargedBillingCode;
    @JsonProperty("chargedPrice")
    private Double chargedPrice;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("action")
    private String action;
    @JsonProperty("activity")
    private String activity;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("transactionState")
    private String transactionState;
    @JsonProperty("partnerTransactionId")
    private Object partnerTransactionId;
    @JsonProperty("itemId")
    private Integer itemId;
    @JsonProperty("itemTypeId")
    private Integer itemTypeId;
    @JsonProperty("actionResult")
    private Object actionResult;
    @JsonProperty("serviceId")
    private Object serviceId;
    @JsonProperty("subscriptionId")
    private Long subscriptionId;
    @JsonProperty("circleCode")
    private String circleCode;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("errorCode")
    private Object errorCode;
    @JsonProperty("errorDesc")
    private Object errorDesc;
    @JsonProperty("delayed")
    private Boolean delayed;
    @JsonProperty("closed")
    private Boolean closed;
    @JsonProperty("customerTransactionId")
    private String customerTransactionId;
    @JsonProperty("userPreferredLanguage")
    private String userPreferredLanguage;
    @JsonProperty("userSource")
    private Object userSource;
    @JsonProperty("nextBillingDate")
    private Long nextBillingDate;
    @JsonProperty("adNetworkParams")
    private String adNetworkParams;
    @JsonProperty("churnNotificationParam")
    private Object churnNotificationParam;
    @JsonProperty("activationDate")
    private Long activationDate;
   

}
