
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "stateConfigId",
    "productId",
    "partnerId",
    "countryCode",
    "operationType",
    "pricePoint",
    "actInitDuration",
    "activeDuration",
    "parkingDuration",
    "graceDuration",
    "suspendDuration",
    "blacklistDuration"
})
public class StateConfig {

    @JsonProperty("stateConfigId")
    private Integer stateConfigId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("pricePoint")
    private String pricePoint;
    @JsonProperty("actInitDuration")
    private Integer actInitDuration;
    @JsonProperty("activeDuration")
    private Integer activeDuration;
    @JsonProperty("parkingDuration")
    private Integer parkingDuration;
    @JsonProperty("graceDuration")
    private Integer graceDuration;
    @JsonProperty("suspendDuration")
    private Integer suspendDuration;
    @JsonProperty("blacklistDuration")
    private Integer blacklistDuration;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("stateConfigId")
    public Integer getStateConfigId() {
        return stateConfigId;
    }

    @JsonProperty("stateConfigId")
    public void setStateConfigId(Integer stateConfigId) {
        this.stateConfigId = stateConfigId;
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

    @JsonProperty("pricePoint")
    public String getPricePoint() {
        return pricePoint;
    }

    @JsonProperty("pricePoint")
    public void setPricePoint(String pricePoint) {
        this.pricePoint = pricePoint;
    }

    @JsonProperty("actInitDuration")
    public Integer getActInitDuration() {
        return actInitDuration;
    }

    @JsonProperty("actInitDuration")
    public void setActInitDuration(Integer actInitDuration) {
        this.actInitDuration = actInitDuration;
    }

    @JsonProperty("activeDuration")
    public Integer getActiveDuration() {
        return activeDuration;
    }

    @JsonProperty("activeDuration")
    public void setActiveDuration(Integer activeDuration) {
        this.activeDuration = activeDuration;
    }

    @JsonProperty("parkingDuration")
    public Integer getParkingDuration() {
        return parkingDuration;
    }

    @JsonProperty("parkingDuration")
    public void setParkingDuration(Integer parkingDuration) {
        this.parkingDuration = parkingDuration;
    }

    @JsonProperty("graceDuration")
    public Integer getGraceDuration() {
        return graceDuration;
    }

    @JsonProperty("graceDuration")
    public void setGraceDuration(Integer graceDuration) {
        this.graceDuration = graceDuration;
    }

    @JsonProperty("suspendDuration")
    public Integer getSuspendDuration() {
        return suspendDuration;
    }

    @JsonProperty("suspendDuration")
    public void setSuspendDuration(Integer suspendDuration) {
        this.suspendDuration = suspendDuration;
    }

    @JsonProperty("blacklistDuration")
    public Integer getBlacklistDuration() {
        return blacklistDuration;
    }

    @JsonProperty("blacklistDuration")
    public void setBlacklistDuration(Integer blacklistDuration) {
        this.blacklistDuration = blacklistDuration;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
