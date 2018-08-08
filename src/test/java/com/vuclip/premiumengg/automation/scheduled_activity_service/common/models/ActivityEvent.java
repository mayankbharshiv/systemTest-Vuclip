package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEvent {

    private EventInfo eventInfo;

    private Long eventTimeStamp;

    private String userId;

    private String msisdn;

    private int productId;

    private String productName;

    private int partnerId;

    private String partnerName;

    private String requestedBillingCode;

    private String requestedPrice;

    private String attemptedBillingCode;

    private String attemptedPrice;

    private String chargedBillingCode;

    private double chargedPrice;

    private String currency;

    private String mode;

    private String action;

    private String activity;

    private String transactionId;

    private String transactionState;

    private String partnerTransactionId;

    private Integer itemId;

    private Integer itemTypeId;

    private String actionResult;

    private String serviceId;

    private Long subscriptionId;

    private String circleCode;

    private String countryCode;

    private String errorCode;

    private String errorDesc;

    private boolean delayed;

    private boolean closed;

    private String customerTransactionId;

    private String userPreferredLanguage;

    private String userSource;

    private Long nextBillingDate;

    private String adNetworkParams;

    private String churnNotificationParam;

    private Long activationDate;

}