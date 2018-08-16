package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "userId", "msisdn", "productId", "productName", "partnerId", "partnerName",
        "requestedBillingCode", "requestedPrice", "attemptedBillingCode", "attemptedPrice", "chargedBillingCode",
        "chargedPrice", "currency", "mode", "action", "activity", "transactionId", "transactionState",
        "partnerTransactionId", "itemId", "itemTypeId", "actionResult", "serviceId", "subscriptionId", "circleCode",
        "countryCode", "errorCode", "errorDesc", "delayed", "closed", "customerTransactionId", "userPreferredLanguage",
        "userSource", "nextBillingDate", "adNetworkParams", "churnNotificationParam"})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Message {


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
    private String chargedBillingCode;
    @JsonProperty("chargedPrice")
    private String chargedPrice;
    @JsonProperty("currency")
    private Object currency;
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
    private Object itemId;
    @JsonProperty("itemTypeId")
    private Object itemTypeId;
    @JsonProperty("actionResult")
    private String actionResult;
    @JsonProperty("serviceId")
    private Object serviceId;
    @JsonProperty("subscriptionId")
    private long subscriptionId;
    @JsonProperty("circleCode")
    private Object circleCode;
    @JsonProperty("countryCode")
    private Object countryCode;
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
    private Object userPreferredLanguage;
    @JsonProperty("userSource")
    private Object userSource;
    @JsonProperty("nextBillingDate")
    private BigInteger nextBillingDate;
    @JsonProperty("adNetworkParams")
    private String adNetworkParams;
    @JsonProperty("churnNotificationParam")
    private Object churnNotificationParam;

}