package com.vuclip.premiumengg.automation.subscription_service.common.models;
/**
 * Subscription status
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public enum SubscriptionStatus {

	DEACTIVATED, ACTIVATED, ERROR, DCT_INIT, SUSPEND, ACT_INIT, PARKING;

	private String name;


	public String getName() {
		return name;
	}
}
