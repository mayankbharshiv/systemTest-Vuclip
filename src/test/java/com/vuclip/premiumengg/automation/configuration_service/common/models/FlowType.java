package com.vuclip.premiumengg.automation.configuration_service.common.models;
public enum FlowType {
	VUCLIP_CONSENT, PARTNER_CONSENT, VUCLIP_SYNC, VUCLIP_ASYNC, PARTNER_ASYNC;
	
	public static FlowType fromValue(String val) {
		for (FlowType type : FlowType.values()) {
			if (type.toString().equals(val)) {
				return type;
			}
		}
		return null;
	}
}
