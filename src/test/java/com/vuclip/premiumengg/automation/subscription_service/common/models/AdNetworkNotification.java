
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
    "adNetworkNotificationId",
    "productId",
    "partnerId",
    "countryCode",
    "id",
    "paidPercentage",
    "freePercentage",
    "winbackPercentage",
    "operationType"
})
public class AdNetworkNotification {

    @JsonProperty("adNetworkNotificationId")
    private Integer adNetworkNotificationId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("paidPercentage")
    private Integer paidPercentage;
    @JsonProperty("freePercentage")
    private Integer freePercentage;
    @JsonProperty("winbackPercentage")
    private Integer winbackPercentage;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("adNetworkNotificationId")
    public Integer getAdNetworkNotificationId() {
        return adNetworkNotificationId;
    }

    @JsonProperty("adNetworkNotificationId")
    public void setAdNetworkNotificationId(Integer adNetworkNotificationId) {
        this.adNetworkNotificationId = adNetworkNotificationId;
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

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("paidPercentage")
    public Integer getPaidPercentage() {
        return paidPercentage;
    }

    @JsonProperty("paidPercentage")
    public void setPaidPercentage(Integer paidPercentage) {
        this.paidPercentage = paidPercentage;
    }

    @JsonProperty("freePercentage")
    public Integer getFreePercentage() {
        return freePercentage;
    }

    @JsonProperty("freePercentage")
    public void setFreePercentage(Integer freePercentage) {
        this.freePercentage = freePercentage;
    }

    @JsonProperty("winbackPercentage")
    public Integer getWinbackPercentage() {
        return winbackPercentage;
    }

    @JsonProperty("winbackPercentage")
    public void setWinbackPercentage(Integer winbackPercentage) {
        this.winbackPercentage = winbackPercentage;
    }

    @JsonProperty("operationType")
    public String getOperationType() {
        return operationType;
    }

    @JsonProperty("operationType")
    public void setOperationType(String operationType) {
        this.operationType = operationType;
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
