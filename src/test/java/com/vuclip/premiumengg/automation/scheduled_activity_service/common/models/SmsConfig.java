package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"smsConfigId",
"type",
"redirectionContext",
"defaultSmsLanguageId",
"batchSize",
"smsLength",
"autoPlay",
"status",
"criterias",
"productId",
"partnerId",
"countryCode",
"operationType"
})
public class SmsConfig {

@JsonProperty("smsConfigId")
private Integer smsConfigId;
@JsonProperty("type")
private String type;
@JsonProperty("redirectionContext")
private String redirectionContext;
@JsonProperty("defaultSmsLanguageId")
private Integer defaultSmsLanguageId;
@JsonProperty("batchSize")
private Integer batchSize;
@JsonProperty("smsLength")
private Integer smsLength;
@JsonProperty("autoPlay")
private Boolean autoPlay;
@JsonProperty("status")
private String status;
@JsonProperty("criterias")
private List<Criteria> criterias = null;
@JsonProperty("productId")
private Integer productId;
@JsonProperty("partnerId")
private Integer partnerId;
@JsonProperty("countryCode")
private String countryCode;
@JsonProperty("operationType")
private String operationType;

/**
* No args constructor for use in serialization
* 
*/
public SmsConfig() {
}

/**
* 
* @param redirectionContext
* @param status
* @param criterias
* @param partnerId
* @param countryCode
* @param batchSize
* @param type
* @param productId
* @param defaultSmsLanguageId
* @param operationType
* @param smsConfigId
* @param smsLength
* @param autoPlay
*/
public SmsConfig(Integer smsConfigId, String type, String redirectionContext, Integer defaultSmsLanguageId, Integer batchSize, Integer smsLength, Boolean autoPlay, String status, List<Criteria> criterias, Integer productId, Integer partnerId, String countryCode, String operationType) {
super();
this.smsConfigId = smsConfigId;
this.type = type;
this.redirectionContext = redirectionContext;
this.defaultSmsLanguageId = defaultSmsLanguageId;
this.batchSize = batchSize;
this.smsLength = smsLength;
this.autoPlay = autoPlay;
this.status = status;
this.criterias = criterias;
this.productId = productId;
this.partnerId = partnerId;
this.countryCode = countryCode;
this.operationType = operationType;
}

@JsonProperty("smsConfigId")
public Integer getSmsConfigId() {
return smsConfigId;
}

@JsonProperty("smsConfigId")
public void setSmsConfigId(Integer smsConfigId) {
this.smsConfigId = smsConfigId;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("redirectionContext")
public String getRedirectionContext() {
return redirectionContext;
}

@JsonProperty("redirectionContext")
public void setRedirectionContext(String redirectionContext) {
this.redirectionContext = redirectionContext;
}

@JsonProperty("defaultSmsLanguageId")
public Integer getDefaultSmsLanguageId() {
return defaultSmsLanguageId;
}

@JsonProperty("defaultSmsLanguageId")
public void setDefaultSmsLanguageId(Integer defaultSmsLanguageId) {
this.defaultSmsLanguageId = defaultSmsLanguageId;
}

@JsonProperty("batchSize")
public Integer getBatchSize() {
return batchSize;
}

@JsonProperty("batchSize")
public void setBatchSize(Integer batchSize) {
this.batchSize = batchSize;
}

@JsonProperty("smsLength")
public Integer getSmsLength() {
return smsLength;
}

@JsonProperty("smsLength")
public void setSmsLength(Integer smsLength) {
this.smsLength = smsLength;
}

@JsonProperty("autoPlay")
public Boolean getAutoPlay() {
return autoPlay;
}

@JsonProperty("autoPlay")
public void setAutoPlay(Boolean autoPlay) {
this.autoPlay = autoPlay;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("criterias")
public List<Criteria> getCriterias() {
return criterias;
}

@JsonProperty("criterias")
public void setCriterias(List<Criteria> criterias) {
this.criterias = criterias;
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

@JsonProperty("operationType")
public String getOperationType() {
return operationType;
}

@JsonProperty("operationType")
public void setOperationType(String operationType) {
this.operationType = operationType;
}

}

