package com.vuclip.premiumengg.automation.subscription_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConfirmSyncData {
	CONFIRMISNOTCLOSED(8181, 8181, "BC03", "BC03", "BC03", 1, 1, 3, "SUCCESS", 5001L, "51234567890", false,
			1541738453289L,"ACT_INIT","act_init_validity","D_ABC_KIM","WAP"),

	CONFIRMFAILURE(8181, 8181, "BC03", "BC03", "BC03", 1, 1, 6, "FAILURE", 5004L, "51234567895", false,
			1541738453289L,null,null,"D_ABC_KIM","WAP"),

	CONFIRMERROR(8181, 8181, "BC03", "BC03", "BC03", 1, 1, 7, "ERROR", 5005L, "51234567893", false,
			1541738453289L,null,null,"D_ABC_KIM","WAP"),

	CONFIRMCANCELED(8181, 8181, "BC03", "BC03", "BC03", 1, 1, 6, "CANCEL", 5006L, "51234567894", false,
			1541738453289L,null,null,"D_ABC_KIM","WAP"),

	CONFIRMCONSENTCHRGCLOSED(8181, 8181, "BC03", "BC03", "BC03", 2, 1, 3, "SUCCESS", 5002L, "51234567891", true,
			1541738453289L,"ACTIVATED","validity","D_ABC_KIM","WAP"),

	CONFIRMISCLOSED(8181, 8181, "BC03", "BC03", "BC03", 1, 1, 3, "SUCCESS", 5003L, "51234567892", true,
			1541738453289L,"ACTIVATED","validity","D_ABC_KIM","WAP");

	Integer productId;
	Integer partnerId;
	String subscriptionBillingCode;
	String chargedBillingCode;
	String fallbackBillingCode;
	Integer actionId;
	Integer activityId;
	Integer transactionStateId;
	String actionResult;
	Long subscriptionId;
	String userId;
	Boolean closed;
	Long nextBillingDate;
	String subscriptionStatus;
	String validityColumn;
	String userSource;
	String mode;
	
}
