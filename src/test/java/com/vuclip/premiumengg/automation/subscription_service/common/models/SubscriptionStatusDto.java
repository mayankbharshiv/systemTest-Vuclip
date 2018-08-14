package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubscriptionStatusDto {

	private String userId;
	private Long userSubscriptionId;
	private Long subStartDate;
	private Long subEndDate;
	private Long nextBillingDate;
	private Double amount;
	private Country country;
	private Partner partner;
	private SubscriptionPlanCycle planCycle;
	private Long partnerId;
	private boolean isSubscribeable;
	private boolean isBalcklisted;
	private boolean isCooldown;
	private SubscriptionStatus subscriptionStatus;
	private String msisdn;
	private int subscriptionValidityDays;
	private int providerId;
	private String subscriptionBillingCode;
	private String chargedBillingCode;
	private String customerTransactionId;
	private SubType subType;
	private Advertisement advertisement;
	private boolean renewalAllowed;
	private Map<String, String> payload = new HashMap<String, String>();
}
