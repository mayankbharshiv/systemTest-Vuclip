package com.vuclip.premiumengg.automation.services.vuconnect.utils;


import com.vuclip.premiumengg.automation.services.vuconnect.steps.StepVuConnectBilling;

import java.util.HashMap;
import java.util.Map;

public class VuConnectUtils {

    /**
     * @param stepVuConnectBilling
     * @return
     */
    public Map<String, Object> getVuconnectBillingParameters(StepVuConnectBilling stepVuConnectBilling) {

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("msisdn", stepVuConnectBilling.getMsisdn());
        params.put("transactionID", stepVuConnectBilling.getTransactionId());
        params.put("billingCode", stepVuConnectBilling.getBillingCode());
        params.put("parenbillingCode", stepVuConnectBilling.getParentBillingCode());
        params.put("activityType", stepVuConnectBilling.getActivityType());
        params.put("callbackURL", stepVuConnectBilling.getCallbackURL());
        params.put("authToke", stepVuConnectBilling.getAuthToken());
        params.put("activityResult", stepVuConnectBilling.getActivityResult());
        params.put("requestTypeId", stepVuConnectBilling.getRequestTypeId());
        params.put("chargingMode", stepVuConnectBilling.getChargingMode());
        params.put("itemId", stepVuConnectBilling.getItemId());
        params.put("itemTypeId", stepVuConnectBilling.getItemTypeId());
        params.put("languageId", stepVuConnectBilling.getLanguageId());
        params.put("subscriptionStatusId", stepVuConnectBilling.getSubscriptionStatusId());
        params.put("providerParam", stepVuConnectBilling.getProviderParam());

        return params;
    }
}