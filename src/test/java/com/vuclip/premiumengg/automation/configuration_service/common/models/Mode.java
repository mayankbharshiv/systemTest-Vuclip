package com.vuclip.premiumengg.automation.configuration_service.common.models;

public enum Mode {
	WAP, SMS, ANDROID_APP, IOS_APP, CC_TOOL, BACKEND;

	public static Mode fromValue(String val) {
		for (Mode type : Mode.values()) {
			if (type.toString().equals(val)) {
				return type;

			}
		}
		return null;
	}
}
