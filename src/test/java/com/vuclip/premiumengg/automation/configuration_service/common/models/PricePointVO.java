package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"operationType"})
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PricePointVO {

	private String billingCode;
	private double price;
	private String description;
	private int validity;
	private int noOfCredits;
	private String serviceId;
	private int appId;
	private int ujId;
	private int itemId;
	private ItemTypeId itemTypeId;
	private boolean balanceCheckRequired;
	private boolean fallbackApplicable;
	private boolean freeTrialApplicable;
	private String fallbackPpBillingCode;
	private String freeTrialBillingCode;
	private boolean isFreeTrial;
	private int exclusionPeriod;
	private boolean autoRenewalApplicable;
	private String status;
	private boolean contentAccessPostDeactivation;
	private int noOfDaysContentAccessAllowInParking;
	private int noOfDaysContentAccessAllowInSuspend;
	private int parkingPeriod;
	private int suspendPeriod;
	private int activationCoolDownPeriod;
	private String productName;
	private String partnerName;
	private String countryName;
	private String operationType;
	private int actInitValidity;
	private int deactivationRetryValidity;
	private int notificationWaitPeriod;
	private TimeUnit timeUnit;
}
