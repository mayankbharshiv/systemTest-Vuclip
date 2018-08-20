package com.vuclip.premiumengg.automation.configuration_service.common.models;
public enum SmsType {
	CONTENT_SMS, ENGAGEMENT_SMS, PRE_RENEWAL_SMS, OPTOUT_SMS, ACTIVATION_SMS, DEACTIVATION_SMS, RENEWAL_SMS;
	
	public static SmsType fromValue(String val) {
		for (SmsType mode : SmsType.values()) {
			if (mode.toString().equals(val)) {
				return mode;

			}
		}
		return null;
	}
}
