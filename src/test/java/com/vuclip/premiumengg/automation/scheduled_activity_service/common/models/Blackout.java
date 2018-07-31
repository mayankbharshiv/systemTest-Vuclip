package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"blackoutId",
"productId",
"partnerId",
"countryCode",
"period",
"operationType",
"blackoutWindow"
})
public class Blackout {

@JsonProperty("blackoutId")
private Integer blackoutId;
@JsonProperty("productId")
private Integer productId;
@JsonProperty("partnerId")
private Integer partnerId;
@JsonProperty("countryCode")
private String countryCode;
@JsonProperty("period")
private String period;
@JsonProperty("operationType")
private String operationType;
@JsonProperty("blackoutWindow")
private String blackoutWindow;

/**
* No args constructor for use in serialization
* 
*/
public Blackout() {
}

/**
* 
* @param operationType
* @param blackoutWindow
* @param countryCode
* @param partnerId
* @param period
* @param productId
* @param blackoutId
*/
public Blackout(Integer blackoutId, Integer productId, Integer partnerId, String countryCode, String period, String operationType, String blackoutWindow) {
super();
this.blackoutId = blackoutId;
this.productId = productId;
this.partnerId = partnerId;
this.countryCode = countryCode;
this.period = period;
this.operationType = operationType;
this.blackoutWindow = blackoutWindow;
}

@JsonProperty("blackoutId")
public Integer getBlackoutId() {
return blackoutId;
}

@JsonProperty("blackoutId")
public void setBlackoutId(Integer blackoutId) {
this.blackoutId = blackoutId;
}

@JsonProperty("productId")
public Integer getProductId() {
return productId;
}

@JsonProperty("productId")
public void setProductId(Integer productId) {
this.productId = productId;
}

@JsonProperty("partnerId")
public Integer getPartnerId() {
return partnerId;
}

@JsonProperty("partnerId")
public void setPartnerId(Integer partnerId) {
this.partnerId = partnerId;
}

@JsonProperty("countryCode")
public String getCountryCode() {
return countryCode;
}

@JsonProperty("countryCode")
public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

@JsonProperty("period")
public String getPeriod() {
return period;
}

@JsonProperty("period")
public void setPeriod(String period) {
this.period = period;
}

@JsonProperty("operationType")
public String getOperationType() {
return operationType;
}

@JsonProperty("operationType")
public void setOperationType(String operationType) {
this.operationType = operationType;
}

@JsonProperty("blackoutWindow")
public String getBlackoutWindow() {
return blackoutWindow;
}

@JsonProperty("blackoutWindow")
public void setBlackoutWindow(String blackoutWindow) {
this.blackoutWindow = blackoutWindow;
}

}

