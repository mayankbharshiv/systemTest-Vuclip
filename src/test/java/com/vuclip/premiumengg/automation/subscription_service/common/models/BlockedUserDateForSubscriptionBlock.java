package com.vuclip.premiumengg.automation.subscription_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BlockedUserDateForSubscriptionBlock {

	ACTIVATEDBLACKLISTTHENCOOLDOWN(2000L, "2018-06-29 11:47:54", "2018-08-29 11:00:06", 8181, 8181,
			"2018-06-29 11:48:06", "2234567890", "BLACKLIST", "blacklist_validity", "blacklisted", 2),

	ACTIVATEDCOOLDOWNTHENBLACKLIST(2051L, "2018-06-29 11:47:54", "2018-08-29 11:00:06", 8181, 8181,
			"2018-06-29 11:48:06", "2234567891", "COOLDOWN", "cooldown_validity", "cooldowned", 1),

	UNBLOCKBLACKLIST(2010L, "2018-06-29 11:47:54", "2018-07-01 11:00:06", 8181, 8181, "2018-06-30 11:48:06",
			"2234567892", "BLACKLIST", "blacklist_validity", "blacklisted", 1),

	UNBLOCKCOOLDOWN(2015L, "2018-06-29 11:47:54", "2018-07-01 11:00:06", 8181, 8181, "2018-06-30 11:48:06",
			"2234567892", "BLACKLIST", "blacklist_validity", "blacklisted", 2);

	Long subscription_id;
	String created_on;
	String end_date;
	int partner_id;
	int product_id;
	String start_date;
	String user_id;
	String blockType;
	String validityType;
	String flagType;
	int blockTypeValue;

}
