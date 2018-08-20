package com.vuclip.premiumengg.automation.subscription_service.common.models;

public enum Country {
	
	MY("IN", "Inida","RS");
	
	private String ccode;
	private String name;
	private String currency;
	
	private Country(String ccode,String name, String currency){
		this.setCcode(ccode);
		this.setName(name);
		this.setCurrency(currency);
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
