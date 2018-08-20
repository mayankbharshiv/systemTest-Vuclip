package com.vuclip.premiumengg.automation.billing_package_service.common.models;

/**
 * Created by Kohitij_Das on 22/03/18.
 */
public enum BillingResponse {

    SUCCESS(true, 200, "Success"),
    NOTFOUND(false, 404, "No billing packages found"),
    BADREQUEST(false, 400, "productId : cannot be null");

    private final boolean successful;

    private final int responseCode;

    private final String message;

    BillingResponse(boolean successful, int responseCode, String message) {
        this.successful = successful;
        this.responseCode = responseCode;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }

}
