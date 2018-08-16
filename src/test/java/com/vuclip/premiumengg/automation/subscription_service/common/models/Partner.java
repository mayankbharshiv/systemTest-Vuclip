package com.vuclip.premiumengg.automation.subscription_service.common.models;

public enum Partner {
	UMOBILE("Umobile");
	
	private String name;
	private Partner(String name){
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
