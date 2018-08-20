package com.vuclip.premiumengg.automation.subscription_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum VerifyBlockUserStatusData {

	NOTBLACKLIST(100044L, "2018-06-29 11:47:54",  1530253086000L, 1, "BC03", 0, null,
			1529475466000L, "105234567877", 1528611466000L, 0, 8181, 8181,  1528611466000L, null, 0,
			"BC03", "DEACTIVATED", 30, "105234567877", "BLACKLIST", "blacklist_validity", "blacklisted", 1,"MY","NOT_BLACKLISTED"),

	BLACKLIST(100066L, "2018-06-29 11:47:54",  1530253086000L, 1, "BC03", 0, null,
			1940253086000L, "1052345678990", 1541738453289L, 0, 8181, 8181,  1530339486000L, null, 0,
			"BC03", "DEACTIVATED", 30, "1052345678990", "BLACKLIST", "blacklist_validity", "blacklisted", 1,"MY","BLACK_LISTED"),

	
	BLACKLISTNEWUSER(900055L, "2018-06-29 11:47:54",  1530253086000L, 1, "BC03", 0, null,
			1946150686000L, "1052345678166", 1541738453289L, 0, 8181, 8181,  1530339486000L, null, 0,
			"BC03", "DEACTIVATED", 30, "1052345678166", "BLACKLIST", "blacklist_validity", "blacklisted", 1,"MY","NOT_BLACKLISTED");

	
	Long subscription_id;
	String created_on;
	Long activation_date;
	int blacklisted;
	String charged_billing_code;
	int cooldowned;
	Long deactivation_date;
	Long end_date;
	String msisdn;
	Long next_billing_date;
	int paid;
	int partner_id;
	int product_id;
	Long start_date;
	String sub_type;
	int subscribable;
	String subscription_billing_code;
	String subscription_status;
	int validity_days;
	String user_id;
	String blockType;
	String validityType;
	String flagType;
	int blockTypeValue;
	String country;
	String blockListSummary;
}
