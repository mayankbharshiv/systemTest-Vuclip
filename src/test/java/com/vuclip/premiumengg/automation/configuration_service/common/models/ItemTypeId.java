package com.vuclip.premiumengg.automation.configuration_service.common.models;

public enum ItemTypeId {
	PRODUCT(0), CATEGORY(1), LANGUAGE(2), MICROSITE(3), BANNER(4), LINK(5);

	private final int value;

	ItemTypeId(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ItemTypeId fromValue(int val) {
		for (ItemTypeId type : ItemTypeId.values()) {
			if (type.getValue() == val) {
				return type;

			}
		}
		return null;
	}
}
