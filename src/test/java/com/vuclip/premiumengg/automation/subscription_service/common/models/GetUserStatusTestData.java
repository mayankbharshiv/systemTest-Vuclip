package com.vuclip.premiumengg.automation.subscription_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GetUserStatusTestData {
	ACTINIT(0, "MY", "D_KIM_87348", "WAP", 69224L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0, null,
			0.0, 1944077086000L, "1111111116", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"ACT_INIT", 30, "1111111116", "ACTIVATION_IN_PROGRESS", "ACT_INIT"),

	ACTIVATED(0, "MY", "D_KIM_87348", "WAP", 69225L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0, null,
			0.0, 1944077086000L, "1111111117", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"ACTIVATED", 30, "1111111117", "ACTIVE", "ACTIVATED"),

	DEACTIVATEDVALIDITYEXPIRED(0, "MY", "D_KIM_87348", "WAP", 692226L, "2018-06-29 11:47:54", 1529433000000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1530253086000L, "11111111182", 1530253086000L, 0, 8181, 8181, 1529433000000L, null, 0,
			"BC03", "DEACTIVATED", 0, "11111111182", "INACTIVE", "DEACTIVATED"),

	DEACTIVATED(0, "MY", "D_KIM_87348", "WAP", 69227L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1944077086000L, "1111111119", 1535520606000L, 0, 8181, 8181, 1530253086000L, null, 0, "BC03",
			"DEACTIVATED", 15, "1111111119", "INACTIVE_WITH_VALIDITY", "DEACTIVATED");

	int renewalAllowed;
	String country;
	String userSource;
	String mode;
	Long subscription_id;
	String created_on;
	Long activation_date;
	int blacklisted;
	String charged_billing_code;
	String fallbackBillingCode;
	int cooldowned;
	Long deactivation_date;
	Double chargedPrice;
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
	String summary;
	String subscriptionStatus;
}
