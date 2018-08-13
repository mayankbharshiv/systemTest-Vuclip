package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "countryId", "countryName", "countryCode", "timezone", "currency", "operationType" })
public class CountryVO {

	@JsonProperty("countryId")
	private int countryId;
	@JsonProperty("countryName")
	private String countryName;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("timezone")
	private String timezone;
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("operationType")
	private String operationType;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public CountryVO() {
	}

	/**
	 * 
	 * @param countryId
	 * @param timezone
	 * @param countryName
	 * @param operationType
	 * @param countryCode
	 * @param currency
	 */
	public CountryVO(Integer countryId, String countryName, String countryCode, String timezone, String currency,
			String operationType) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.timezone = timezone;
		this.currency = currency;
		this.operationType = operationType;
	}

	@JsonProperty("countryId")
	public Integer getCountryId() {
		return countryId;
	}

	@JsonProperty("countryId")
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@JsonProperty("countryName")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("countryName")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("timezone")
	public String getTimezone() {
		return timezone;
	}

	@JsonProperty("timezone")
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}

	@JsonProperty("currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@JsonProperty("operationType")
	public String getOperationType() {
		return operationType;
	}

	@JsonProperty("operationType")
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

}