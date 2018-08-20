package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserSubscriptionDataForBlock {

	ACTIVATEDBLACKLIST(1000L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, null, "2018-08-29 11:00:06",
			"1234567890", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06", null, 1, "BC03",
			"ACTIVATED", 30, "1234567890", "BLACKLIST", "blacklist_validity", "blacklisted"),

	DEACTIVATEDBLACKLIST(1001L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, "2018-07-10 11:00:06",
			"2018-08-29 11:00:06", "1234567891", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06",
			null, 1, "BC03", "DEACTIVATED", 30, "1234567891", "BLACKLIST", "blacklist_validity", "blacklisted"),

	DEACTIVATEDCOOLDOWN(1002L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, "2018-07-10 11:00:06",
			"2018-08-29 11:00:06", "1234567892", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06",
			null, 1, "BC03", "DEACTIVATED", 30, "1234567892", "COOLDOWN", "cooldown_validity", "cooldowned"),

	DCT_INITCOOLDOWN(1003L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, "2018-07-10 11:00:06",
			"2018-08-29 11:00:06", "1234567893", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06",
			null, 1, "BC03", "DCT_INIT", 30, "1234567893", "COOLDOWN", "cooldown_validity", "cooldowned"),

	PARKNGBLACKLIST(1004L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, null, "2018-08-29 11:00:06",
			"1234567894", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06", null, 1, "BC03",
			"PARKING", 30, "1234567894", "BLACKLIST", "blacklist_validity", "blacklisted"),

	ACT_INITBLACKLIST(1005L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, null, "2018-08-29 11:00:06",
			"1234567895", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06", null, 1, "BC03",
			"ACT_INIT", 30, "1234567895", "BLACKLIST", "blacklist_validity", "blacklisted"),

	SUSPENDBLACKLIST(1006L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, null, "2018-08-29 11:00:06",
			"1234567896", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06", null, 1, "BC03",
			"SUSPEND", 30, "1234567896", "BLACKLIST", "blacklist_validity", "blacklisted"),

	ACTIVATEDBLACKLISTNEGATIVE(1007L, "2018-06-29 11:47:54", "2018-06-29 11:48:06", 0, "BC03", 0, null,
			"2018-08-29 11:00:06", "1234567897", new BigInteger("1541738453289"), 0, 8181, 8181, "2018-06-29 11:48:06",
			null, 1, "BC03", "ACTIVATED", 30, "1234567897", "BLACKLIST", "blacklist_validity", "blacklisted");

	Long subscription_id;
	String created_on;
	String activation_date;
	int blacklisted;
	String charged_billing_code;
	int cooldowned;
	String deactivation_date;
	String end_date;
	String msisdn;
	BigInteger next_billing_date;
	int paid;
	int partner_id;
	int product_id;
	String start_date;
	String sub_type;
	int subscribable;
	String subscription_billing_code;
	String subscription_status;
	int validity_days;
	String user_id;
	String blockType;
	String validityType;
	String flagType;

}
