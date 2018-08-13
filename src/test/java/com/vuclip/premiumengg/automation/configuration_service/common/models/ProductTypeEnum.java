package com.vuclip.premiumengg.automation.configuration_service.common.models;
public enum ProductTypeEnum {

	SUBSCRIPTION_STORE(1), PPV(2), CHANNEL_BASED_STORE(3);

	private final int productType;

	ProductTypeEnum(int v) {
		productType = v;
	}

	public int value() {
		return productType;
	}

	public static ProductTypeEnum fromValue(int v) {
		for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
			if (productTypeEnum.productType == v) {
				return productTypeEnum;
			}
		}
		throw new IllegalArgumentException(String.valueOf(v));
	}

	public static ProductTypeEnum fromName(String name) {
		for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
			if (productTypeEnum.toString().equals(name)) {
				return productTypeEnum;
			}
		}
		throw new IllegalArgumentException(String.valueOf(name));
	}

}
