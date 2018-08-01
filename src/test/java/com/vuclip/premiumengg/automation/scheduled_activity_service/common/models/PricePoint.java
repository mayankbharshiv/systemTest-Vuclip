package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"billingCode", "price", "description", "validity", "noOfCredits", "serviceId", "appId", "ujId",
        "itemId", "itemTypeId", "balanceCheckRequired", "fallbackApplicable", "freeTrialApplicable",
        "fallbackPpBillingCode", "freeTrialBillingCode", "freeTrialDays", "exclusionPeriod", "autoRenewalApplicable",
        "status", "contentAccessPostDeactivation", "noOfDaysContentAccessAllowInParking",
        "noOfDaysContentAccessAllowInSuspend", "parkingPeriod", "suspendPeriod", "activationCoolDownPeriod",
        "productId", "partnerId", "countryCode", "operationType", "actInitValidity", "deactivationRetryValidity",
        "notificationWaitPeriod", "timeUnit"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PricePoint {

    @JsonProperty("billingCode")
    private String billingCode;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("validity")
    private Integer validity;
    @JsonProperty("noOfCredits")
    private Integer noOfCredits;
    @JsonProperty("serviceId")
    private String serviceId;
    @JsonProperty("appId")
    private Integer appId;
    @JsonProperty("ujId")
    private Integer ujId;
    @JsonProperty("itemId")
    private Integer itemId;
    @JsonProperty("itemTypeId")
    private Integer itemTypeId;
    @JsonProperty("balanceCheckRequired")
    private Boolean balanceCheckRequired;
    @JsonProperty("fallbackApplicable")
    private Boolean fallbackApplicable;
    @JsonProperty("freeTrialApplicable")
    private Boolean freeTrialApplicable;
    @JsonProperty("fallbackPpBillingCode")
    private String fallbackPpBillingCode;
    @JsonProperty("freeTrialBillingCode")
    private String freeTrialBillingCode;
    @JsonProperty("freeTrialDays")
    private Integer freeTrialDays;
    @JsonProperty("exclusionPeriod")
    private Integer exclusionPeriod;
    @JsonProperty("autoRenewalApplicable")
    private Boolean autoRenewalApplicable;
    @JsonProperty("status")
    private String status;
    @JsonProperty("contentAccessPostDeactivation")
    private Boolean contentAccessPostDeactivation;
    @JsonProperty("noOfDaysContentAccessAllowInParking")
    private Integer noOfDaysContentAccessAllowInParking;
    @JsonProperty("noOfDaysContentAccessAllowInSuspend")
    private Integer noOfDaysContentAccessAllowInSuspend;
    @JsonProperty("parkingPeriod")
    private Integer parkingPeriod;
    @JsonProperty("suspendPeriod")
    private Integer suspendPeriod;
    @JsonProperty("activationCoolDownPeriod")
    private Integer activationCoolDownPeriod;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("actInitValidity")
    private Integer actInitValidity;
    @JsonProperty("deactivationRetryValidity")
    private Integer deactivationRetryValidity;
    @JsonProperty("notificationWaitPeriod")
    private Integer notificationWaitPeriod;
    @JsonProperty("timeUnit")
    private String timeUnit;

}
