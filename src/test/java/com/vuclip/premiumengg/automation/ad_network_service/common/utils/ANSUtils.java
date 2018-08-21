package com.vuclip.premiumengg.automation.ad_network_service.common.utils;

import com.vuclip.premiumengg.automation.ad_network_service.common.models.Message;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveAdnetwork;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveCountry;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.SaveProduct;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

import java.math.BigInteger;

public class ANSUtils {

    /**
     * @param JsonFileName
     * @param type
     * @return
     */
    public static <T> T loadJson(String JsonFileName, Class<T> type) {
        return ObjectMapperUtils
                .readValue("src/test/resources/configurations/ad-network-service/request/" + JsonFileName, type);
    }

    public static SaveProduct generateSaveProductConfig(Integer productId, String productName, Integer partnerId,
                                                        Integer adNetworkId, String billingCode, String countryCode, Integer paidPercentage, Integer freePercentage,
                                                        Integer winbackPercentage, Boolean freeTrialApplicable, Integer parkingPeriod) {

        SaveProduct saveProductRequest = loadJson("saveProduct.json", SaveProduct.class);

        saveProductRequest.getProduct().setProductId(productId);
        saveProductRequest.getProduct().setProductName(productName);
        saveProductRequest.getProductPartnerMappings().get(0).setProductId(productId);
        saveProductRequest.getProductPartnerMappings().get(0).setPartnerId(partnerId);
        saveProductRequest.getProductCountryMapping().setProductId(productId);
        saveProductRequest.getProductCountryMapping().getCountries().get(0).setCountryCode(countryCode);
        saveProductRequest.getSmsConfigs().get(0).setPartnerId(partnerId);
        saveProductRequest.getSmsConfigs().get(0).setProductId(productId);
        saveProductRequest.getSmsConfigs().get(0).setCountryCode(countryCode);
        saveProductRequest.getRetry().get(0).setPartnerId(partnerId);
        saveProductRequest.getRetry().get(0).setProductId(productId);
        saveProductRequest.getRetry().get(0).setCountryCode(countryCode);
        saveProductRequest.getChurnNotifications().get(0).setProductId(productId);
        saveProductRequest.getChurnNotifications().get(0).setPartnerId(partnerId);
        saveProductRequest.getChurnNotifications().get(0).setCountryCode(countryCode);
        saveProductRequest.getAdNetworkNotifications().get(0).setProductId(productId);
        saveProductRequest.getAdNetworkNotifications().get(0).setPartnerId(partnerId);
        saveProductRequest.getAdNetworkNotifications().get(0).setCountryCode(countryCode);
        saveProductRequest.getAdNetworkNotifications().get(0).setAdNetworkNotificationId(adNetworkId);
        saveProductRequest.getAdNetworkNotifications().get(0).setPaidPercentage(paidPercentage);
        saveProductRequest.getAdNetworkNotifications().get(0).setFreePercentage(freePercentage);
        saveProductRequest.getAdNetworkNotifications().get(0).setWinbackPercentage(winbackPercentage);
        saveProductRequest.getAdNetworkNotifications().get(1).setProductId(productId);
        saveProductRequest.getAdNetworkNotifications().get(1).setPartnerId(partnerId);
        saveProductRequest.getAdNetworkNotifications().get(1).setCountryCode(countryCode);
        saveProductRequest.getAdNetworkNotifications().get(1).setWinbackPercentage(winbackPercentage);
        saveProductRequest.getAdNetworkNotifications().get(1).setPaidPercentage(paidPercentage);
        saveProductRequest.getAdNetworkNotifications().get(1).setFreePercentage(freePercentage);
        saveProductRequest.getPricePoints().get(0).setProductId(productId);
        saveProductRequest.getPricePoints().get(0).setPartnerId(partnerId);
        saveProductRequest.getPricePoints().get(0).setBillingCode(billingCode);
        saveProductRequest.getPricePoints().get(0).setFallbackPpBillingCode(billingCode);
        saveProductRequest.getPricePoints().get(0).setFreeTrialBillingCode(billingCode);
        saveProductRequest.getPricePoints().get(0).setFreeTrialApplicable(freeTrialApplicable);
        saveProductRequest.getPricePoints().get(0).setParkingPeriod(parkingPeriod);
        saveProductRequest.getPricePoints().get(0).setCountryCode(countryCode);
        saveProductRequest.getStateConfigs().get(0).setProductId(productId);
        saveProductRequest.getStateConfigs().get(0).setPartnerId(partnerId);
        saveProductRequest.getStateConfigs().get(0).setCountryCode(countryCode);
        saveProductRequest.getBlackouts().get(0).setPartnerId(partnerId);
        saveProductRequest.getBlackouts().get(0).setProductId(productId);
        saveProductRequest.getBlackouts().get(0).setCountryCode(countryCode);
        saveProductRequest.getActivityFlows().get(0).setProductId(productId);
        saveProductRequest.getActivityFlows().get(0).setPartnerId(partnerId);
        saveProductRequest.getActivityFlows().get(0).setBillingCode(billingCode);
        saveProductRequest.getActivityFlows().get(0).setCountryCode(countryCode);
        return saveProductRequest;
    }

    public static SaveCountry generateSaveCountryrRequest(String countryCode, String timezone) {
        SaveCountry saveCountryRequest = loadJson("saveCountry.json", SaveCountry.class);
        saveCountryRequest.setCountryCode(countryCode);
        saveCountryRequest.setTimezone(timezone);
        saveCountryRequest.setCountryName(countryCode);
        return saveCountryRequest;
    }

    public static SaveAdnetwork generateSaveAdnetworkRequest(Integer adNetworkId, String requestParamName, String sourceIdentifer) {
        SaveAdnetwork saveAdNetworkRequest = loadJson("saveAdNetwork.json", SaveAdnetwork.class);
        saveAdNetworkRequest.setAdNetworkId(adNetworkId);
        saveAdNetworkRequest.setRequestParamName(requestParamName);
        saveAdNetworkRequest.setSourceIdentifier(sourceIdentifer);
        saveAdNetworkRequest.setChurnNotificationUrl(ANSTestContext.churnNotificationUrl.replace("XXX", requestParamName));
        saveAdNetworkRequest
                .setNotificationUrl(ANSTestContext.notificationUrl.replace("XXX", requestParamName));
        return saveAdNetworkRequest;
    }
	
	/*public static SaveProduct saveProduct(Integer productId, String productName, Integer partnerId, Integer adNetworkId,
			String billingCode, String countryCode, Integer paidPercentage, Integer freePercentage,
			Integer winbackPercentage, Boolean freeTrialApplicable, Integer parkingPeriod) throws Exception {
		
		SaveProduct saveProductRequest = ANSUtils.generateSaveProductConfig(productId, productName, partnerId,
				adNetworkId, billingCode, countryCode, paidPercentage, freePercentage, winbackPercentage,
				freeTrialApplicable, parkingPeriod);

		return saveProductRequest;
	}*/

    public static SaveProduct generateSaveProductConfig(Integer productId, String productName, Integer partnerId, Integer adNetworkId,
                                                        String billingCode) throws Exception {

        return generateSaveProductConfig(productId, productName, partnerId, adNetworkId, billingCode, ANSTestContext.countryCode,
                ANSTestContext.paidPercentage, ANSTestContext.freePercentage, ANSTestContext.winbackPercentage,
                ANSTestContext.freeTrialApplicable, ANSTestContext.parkingPeriod);
    }


    public static Message generateMessageForQueue(Integer productId, String userId, String billingCode, String billingPrice,
                                                  String action, String activity, String transactionState, String actionResult, long subscriptionId,
                                                   BigInteger nextBillingDate, String adNetworkParams, Object churnNotificationParam,
                                                  String transactionId, Object userSource) throws Exception {

        Message message = new Message();
        message.setProductId(productId);
        message.setProductName(String.valueOf(productId));
        message.setPartnerId(productId);
        message.setPartnerName(String.valueOf(productId));
        message.setUserId(userId);
        message.setMsisdn(userId);
        message.setAttemptedBillingCode(billingCode);
        message.setAttemptedPrice(billingPrice);
        message.setChargedBillingCode(billingCode);
        message.setChargedPrice(billingPrice);
        message.setRequestedBillingCode(billingCode);
        message.setRequestedPrice(billingPrice);
        message.setAction(action);
        message.setActivity(activity);
        message.setTransactionState(transactionState);
        message.setActionResult(actionResult);
        message.setSubscriptionId(subscriptionId);
        message.setNextBillingDate(nextBillingDate);
        message.setAdNetworkParams(adNetworkParams);
        message.setChurnNotificationParam(churnNotificationParam);
        message.setUserSource(userSource);
        message.setMode("WAP");
        message.setDelayed(false);
        message.setClosed(true);
        message.setCustomerTransactionId(transactionId);
        message.setTransactionId(transactionId);
        System.out.println(ObjectMapperUtils.writeValueAsString(message));
        return message;
    }


}
