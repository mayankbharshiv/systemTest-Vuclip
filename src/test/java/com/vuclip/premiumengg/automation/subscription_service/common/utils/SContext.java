package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author rahul s
 */
public class SContext {

    public static int productId;
    public static int partnerId;
    public static String billingCode;
    public static String productName;
    public static String partnertName;
    public static String countryCode = "IN";

    public static void generateProductPartnerIdNameAndBillingCode() {
        productId = RandomUtils.nextInt(100, 500);
        partnerId = productId;
        billingCode = "BC" + productId;
        productName = "PROD" + productId;
        partnertName = "PART" + productId;
        Log4J.getLogger()
                .info("TEST CONTEXT: Product id: {" + productId + "} Partner id:{" + partnerId + "}, Billing Code: {"
                        + billingCode + "},Product Name:{" + productName + "}, Partner Name:{" + partnertName + "}");
    }

}
