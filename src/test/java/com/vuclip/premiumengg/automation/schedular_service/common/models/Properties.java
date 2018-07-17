
package com.vuclip.premiumengg.automation.schedular_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "delivery_mode", "content_type" })
public class Properties {

	@JsonProperty("delivery_mode")
	private Integer deliveryMode;
	@JsonProperty("content_type")
	private String contentType;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Properties() {
	}

	/**
	 * 
	 * @param deliveryMode
	 * @param contentType
	 */
	public Properties(Integer deliveryMode, String contentType) {
		super();
		this.deliveryMode = deliveryMode;
		this.contentType = contentType;
	}

	@JsonProperty("delivery_mode")
	public Integer getDeliveryMode() {
		return deliveryMode;
	}

	@JsonProperty("delivery_mode")
	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	@JsonProperty("content_type")
	public String getContentType() {
		return contentType;
	}

	@JsonProperty("content_type")
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
