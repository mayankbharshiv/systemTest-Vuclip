package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;


import lombok.*;

/**
 * @author shreyash
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SubscriptionInfo {

    private Long subscriptionId;

    private String subscriptionStatus;

    private Long startDate;

    private Long endDate;

    private Long activationDate;

    private Long deactivationDate;

    private String subscriptionBillingCode;

    private String chargedBillingCode;

    private String mode;

    private String circleCode;

    private String customerTransactionId;

    private boolean isPaid;

    private String chargedPrice;

    private Integer partnerId;

    private Integer productId;

    private Integer itemId;

    private Integer itemTypeId;

    private Long nextBillingDate;

    private String serviceId;

    private String isDelayed;

    private String country;

    private Long lastChargedDate;

    private Double subscriptionPrice;

}
