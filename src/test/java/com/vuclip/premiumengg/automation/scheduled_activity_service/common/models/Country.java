package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"countryCode",
"operationType"
})
public class Country {

@JsonProperty("countryCode")
private String countryCode;
@JsonProperty("operationType")
private String operationType;

/**
* No args constructor for use in serialization
* 
*/
public Country() {
}

/**
* 
* @param operationType
* @param countryCode
*/
public Country(String countryCode, String operationType) {
super();
this.countryCode = countryCode;
this.operationType = operationType;
}

@JsonProperty("countryCode")
public String getCountryCode() {
return countryCode;
}

@JsonProperty("countryCode")
public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
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

