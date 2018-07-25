package com.vuclip.premiumengg.automation.ad_network_service.common.models;

public enum ANSTableNames {

    user_adnotification("user_adnotification"), ad_network("ad_network"), app_user("app_user"), billing_package(
            "billing_package"), product_adnetwork_details(
            "product_adnetwork_details"), temp_failover_store("temp_failover_store");

    private String value;

    private ANSTableNames(String value) {
        this.value = value;
    }

    public static ANSTableNames fromValue(String val) {
        for (ANSTableNames tableName : ANSTableNames.values()) {
            if (tableName.getValue().equals(val)) {
                return tableName;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
