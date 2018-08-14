package com.vuclip.premiumengg.automation.subscription_service.common.models;

/**
 * SubscriptionPlanCycle
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public enum SubscriptionPlanCycle {

	MONTHLY("Monthly",30), WEEKLY("Weekly",7), DAILY("Daily", 1), UNLIMITED("Unlimited",-1), RANDOM("Random", -2),
	
	FORTNIGHTLY("Fortnightly", 15), QUARTERLY("Quarterly", 90), YEARLY("Yearly",365), CALCULATED("Calculated", 0);
	
	private String name;
	private int days;
	
	private SubscriptionPlanCycle(String name, int days){
		this.name=name;
		this.days=days;
	}
	
	public String getName() {
		return name;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
}
