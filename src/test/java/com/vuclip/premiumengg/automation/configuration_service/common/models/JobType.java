package com.vuclip.premiumengg.automation.configuration_service.common.models;

public enum JobType {
	ACTIVATION_RETRY, WINBACK, FREETRIAL_RENEWAL, RENEWAL, RENEWAL_RETRY, SYSTEM_CHURN, DEACTIVATION_RETRY, ENGAGEMENT_SMS, PRE_RENEWAL_SMS, CONTENT_SMS, OPTOUT_SMS;

	public static JobType fromValue(String val) {
		for (JobType type : JobType.values()) {
			if (type.toString().equals(val)) {
				return type;

			}
		}
		return null;
	}
}
