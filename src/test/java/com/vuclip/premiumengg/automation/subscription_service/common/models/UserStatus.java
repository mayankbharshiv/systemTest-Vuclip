package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserStatus {

	private String userSubAuthKey;
	private String userId;
	private String msisdn;
	private Long subscriptionId;
	private Long startDate;
	private Long endDate;
	private Long nextBillingDate;
	private Long lastChargeDate;
	private Double chargedPrice;
	private String country;
	private int partnerId;
	private SubscriptionStatus subscriptionStatus;
	private int subscriptionValidityDays;
	private int productId;
	private int itemId;
	private int itemTypeId;
	private String subscriptionBillingCode;
	private String chargedBillingCode;
	private String customerTransactionId;
	private Boolean renewalAllowed;
    private Long activationDate;
    private Long deactivationDate;
	private Map<String, String> payload = new HashMap<String, String>();
	private String summary;
	private String mode;
	private boolean paid;
	private String userSource;
}