package com.vuclip.premiumengg.automation.configuration_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricePointRequestVO {

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
	private String countryName;
	private String partnerName;
	private String productName;
	private int actInitValidity;
	private int deactivationRetryValidity;
	private int notificationWaitPeriod;
	private TimeUnit timeUnit;
}
