
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userSubAuthKey",
    "userId",
    "msisdn",
    "subscriptionId",
    "startDate",
    "endDate",
    "nextBillingDate",
    "lastChargeDate",
    "chargedPrice",
    "country",
    "partnerId",
    "subscriptionStatus",
    "subscriptionValidityDays",
    "productId",
    "itemId",
    "itemTypeId",
    "subscriptionBillingCode",
    "chargedBillingCode",
    "customerTransactionId",
    "renewalAllowed",
    "activationDate",
    "deactivationDate",
    "payload",
    "summary",
    "mode",
    "paid",
    "userSource"
})
@Getter
@Setter
public class Status {

    @JsonProperty("userSubAuthKey")
    private String userSubAuthKey;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("msisdn")
    private Object msisdn;
    @JsonProperty("subscriptionId")
    private Long subscriptionId;
    @JsonProperty("startDate")
    private BigInteger startDate;
    @JsonProperty("endDate")
    private BigInteger endDate;
    @JsonProperty("nextBillingDate")
    private BigInteger nextBillingDate;
    @JsonProperty("lastChargeDate")
    private Object lastChargeDate;
    @JsonProperty("chargedPrice")
    private float chargedPrice;
    @JsonProperty("country")
    private String country;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("subscriptionStatus")
    private String subscriptionStatus;
    @JsonProperty("subscriptionValidityDays")
    private Integer subscriptionValidityDays;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("itemId")
    private Integer itemId;
    @JsonProperty("itemTypeId")
    private Integer itemTypeId;
    @JsonProperty("subscriptionBillingCode")
    private String subscriptionBillingCode;
    @JsonProperty("chargedBillingCode")
    private String chargedBillingCode;
    @JsonProperty("customerTransactionId")
    private String customerTransactionId;
    @JsonProperty("renewalAllowed")
    private Boolean renewalAllowed;
    @JsonProperty("activationDate")
    private BigInteger activationDate;
    @JsonProperty("deactivationDate")
    private BigInteger deactivationDate;
    @JsonProperty("payload")
    private Object payload;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("paid")
    private Boolean paid;
    @JsonProperty("userSource")
    private String userSource;
  
}
