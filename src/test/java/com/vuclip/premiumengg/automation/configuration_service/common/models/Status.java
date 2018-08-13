package com.vuclip.premiumengg.automation.configuration_service.common.models;

import javaslang.collection.Array;

public enum Status {
	active, INACTIVE;

	public static Status permissiveValueOf(String status) throws Exception {
		return Array.of(values()).filter(s -> s.toString().equalsIgnoreCase(status))
				.getOrElseThrow(() -> new Exception("No Status enum found for status : " + status));
	}

}
