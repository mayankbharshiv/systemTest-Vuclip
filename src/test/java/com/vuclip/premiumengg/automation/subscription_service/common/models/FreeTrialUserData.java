package com.vuclip.premiumengg.automation.subscription_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FreeTrialUserData {

	VALIDUSERFREE(3001, "2018-04-29 11:47:54", "2018-04-29 11:48:06", 1, "BC03", 8181, 8181, "3519027613",true,"MY"),

	INVALIDUSERFREE(3009, "2018-06-29 11:47:54", "2018-07-25 11:48:06", 1, "BC03", 8181, 8181, "323456789",false,"MY");

	Integer id;
	String created_on;
	String last_free_trial_date;
	Integer availed_free_trial_count;
	String last_free_trial_billing_code;
	Integer product_id;
	Integer partner_id;
	String user_id;
	Boolean expectedResult;
	String country;
}
