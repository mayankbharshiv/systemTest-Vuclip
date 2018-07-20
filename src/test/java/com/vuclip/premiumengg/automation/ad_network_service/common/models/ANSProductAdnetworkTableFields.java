package com.vuclip.premiumengg.automation.ad_network_service.common.models;

public enum ANSProductAdnetworkTableFields {

	ad_network_id("ad_network_id"), billing_code("billing_code"), churn_notif_period(
			"churn_notif_period"), churn_notif_timeunit("churn_notif_timeunit"), country_name(
					"country_name"), free_percentage("free_percentage"), paid_percentage("paid_percentage"), partner_id(
							"partner_id"), product_id(
									"product_id"), timezone("timezone"), winback_percentage("winback_percentage");

	private String value;

	private ANSProductAdnetworkTableFields(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ANSTableNames fromValue(String val) {
		for (ANSTableNames tableName : ANSTableNames.values()) {
			if (tableName.getValue().equals(val)) {
				return tableName;
			}
		}
		return null;
	}
}
