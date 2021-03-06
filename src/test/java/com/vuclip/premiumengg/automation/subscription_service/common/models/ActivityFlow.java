
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "activityFlowId",
    "productId",
    "partnerId",
    "countryCode",
    "name",
    "billingCode",
    "mode",
    "actions",
    "operationType"
})
public class ActivityFlow {

    @JsonProperty("activityFlowId")
    private Integer activityFlowId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("billingCode")
    private String billingCode;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("actions")
    private List<Action> actions = null;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("activityFlowId")
    public Integer getActivityFlowId() {
        return activityFlowId;
    }

    @JsonProperty("activityFlowId")
    public void setActivityFlowId(Integer activityFlowId) {
        this.activityFlowId = activityFlowId;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("billingCode")
    public String getBillingCode() {
        return billingCode;
    }

    @JsonProperty("billingCode")
    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("actions")
    public List<Action> getActions() {
        return actions;
    }

    @JsonProperty("actions")
    public void setActions(List<Action> actions) {
        this.actions = actions;
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
