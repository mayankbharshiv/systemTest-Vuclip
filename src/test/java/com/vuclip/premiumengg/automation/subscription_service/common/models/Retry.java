
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
    "retryId",
    "productId",
    "partnerId",
    "countryCode",
    "operationType",
    "activityType",
    "maxRetryCount",
    "retryIntervalInMinutes",
    "attemptWindow",
    "typeOfCycle",
    "batchSize",
    "schedulingFrequencyInMinutes",
    "schedulingDays",
    "executingTimeWindow",
    "executingDays",
    "status",
    "actionDefaultEiligible",
    "retryable"
})
public class Retry {

    @JsonProperty("retryId")
    private Integer retryId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("activityType")
    private String activityType;
    @JsonProperty("maxRetryCount")
    private Integer maxRetryCount;
    @JsonProperty("retryIntervalInMinutes")
    private Integer retryIntervalInMinutes;
    @JsonProperty("attemptWindow")
    private String attemptWindow;
    @JsonProperty("typeOfCycle")
    private String typeOfCycle;
    @JsonProperty("batchSize")
    private Integer batchSize;
    @JsonProperty("schedulingFrequencyInMinutes")
    private Integer schedulingFrequencyInMinutes;
    @JsonProperty("schedulingDays")
    private String schedulingDays;
    @JsonProperty("executingTimeWindow")
    private String executingTimeWindow;
    @JsonProperty("executingDays")
    private String executingDays;
    @JsonProperty("status")
    private String status;
    @JsonProperty("actionDefaultEiligible")
    private Boolean actionDefaultEiligible;
    @JsonProperty("retryable")
    private Boolean retryable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("retryId")
    public Integer getRetryId() {
        return retryId;
    }

    @JsonProperty("retryId")
    public void setRetryId(Integer retryId) {
        this.retryId = retryId;
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

    @JsonProperty("activityType")
    public String getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @JsonProperty("maxRetryCount")
    public Integer getMaxRetryCount() {
        return maxRetryCount;
    }

    @JsonProperty("maxRetryCount")
    public void setMaxRetryCount(Integer maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @JsonProperty("retryIntervalInMinutes")
    public Integer getRetryIntervalInMinutes() {
        return retryIntervalInMinutes;
    }

    @JsonProperty("retryIntervalInMinutes")
    public void setRetryIntervalInMinutes(Integer retryIntervalInMinutes) {
        this.retryIntervalInMinutes = retryIntervalInMinutes;
    }

    @JsonProperty("attemptWindow")
    public String getAttemptWindow() {
        return attemptWindow;
    }

    @JsonProperty("attemptWindow")
    public void setAttemptWindow(String attemptWindow) {
        this.attemptWindow = attemptWindow;
    }

    @JsonProperty("typeOfCycle")
    public String getTypeOfCycle() {
        return typeOfCycle;
    }

    @JsonProperty("typeOfCycle")
    public void setTypeOfCycle(String typeOfCycle) {
        this.typeOfCycle = typeOfCycle;
    }

    @JsonProperty("batchSize")
    public Integer getBatchSize() {
        return batchSize;
    }

    @JsonProperty("batchSize")
    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    @JsonProperty("schedulingFrequencyInMinutes")
    public Integer getSchedulingFrequencyInMinutes() {
        return schedulingFrequencyInMinutes;
    }

    @JsonProperty("schedulingFrequencyInMinutes")
    public void setSchedulingFrequencyInMinutes(Integer schedulingFrequencyInMinutes) {
        this.schedulingFrequencyInMinutes = schedulingFrequencyInMinutes;
    }

    @JsonProperty("schedulingDays")
    public String getSchedulingDays() {
        return schedulingDays;
    }

    @JsonProperty("schedulingDays")
    public void setSchedulingDays(String schedulingDays) {
        this.schedulingDays = schedulingDays;
    }

    @JsonProperty("executingTimeWindow")
    public String getExecutingTimeWindow() {
        return executingTimeWindow;
    }

    @JsonProperty("executingTimeWindow")
    public void setExecutingTimeWindow(String executingTimeWindow) {
        this.executingTimeWindow = executingTimeWindow;
    }

    @JsonProperty("executingDays")
    public String getExecutingDays() {
        return executingDays;
    }

    @JsonProperty("executingDays")
    public void setExecutingDays(String executingDays) {
        this.executingDays = executingDays;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("actionDefaultEiligible")
    public Boolean getActionDefaultEiligible() {
        return actionDefaultEiligible;
    }

    @JsonProperty("actionDefaultEiligible")
    public void setActionDefaultEiligible(Boolean actionDefaultEiligible) {
        this.actionDefaultEiligible = actionDefaultEiligible;
    }

    @JsonProperty("retryable")
    public Boolean getRetryable() {
        return retryable;
    }

    @JsonProperty("retryable")
    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
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
