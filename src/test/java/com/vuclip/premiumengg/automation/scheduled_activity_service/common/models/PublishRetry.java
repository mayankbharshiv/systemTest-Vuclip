package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"productId", "partnerId", "countryCode", "activityType", "maxRetryCount", "retryIntervalInMinutes",
        "attemptWindow", "typeOfCycle", "batchSize", "schedulingFrequencyInMinutes", "schedulingDays",
        "executingTimeWindow", "executingDays", "status", "actionDefaultEiligible", "retryable"})
public class PublishRetry {

    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("productId", productId).append("partnerId", partnerId)
                .append("countryCode", countryCode).append("activityType", activityType)
                .append("maxRetryCount", maxRetryCount).append("retryIntervalInMinutes", retryIntervalInMinutes)
                .append("attemptWindow", attemptWindow).append("typeOfCycle", typeOfCycle)
                .append("batchSize", batchSize).append("schedulingFrequencyInMinutes", schedulingFrequencyInMinutes)
                .append("schedulingDays", schedulingDays).append("executingTimeWindow", executingTimeWindow)
                .append("executingDays", executingDays).append("status", status)
                .append("actionDefaultEiligible", actionDefaultEiligible).append("retryable", retryable).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(maxRetryCount).append(countryCode).append(partnerId)
                .append(batchSize).append(productId).append(schedulingDays).append(attemptWindow)
                .append(executingTimeWindow).append(schedulingFrequencyInMinutes).append(retryIntervalInMinutes)
                .append(retryable).append(executingDays).append(actionDefaultEiligible).append(typeOfCycle)
                .append(activityType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PublishRetry) == false) {
            return false;
        }
        PublishRetry rhs = ((PublishRetry) other);
        return new EqualsBuilder().append(status, rhs.status).append(maxRetryCount, rhs.maxRetryCount)
                .append(countryCode, rhs.countryCode).append(partnerId, rhs.partnerId).append(batchSize, rhs.batchSize)
                .append(productId, rhs.productId).append(schedulingDays, rhs.schedulingDays)
                .append(attemptWindow, rhs.attemptWindow).append(executingTimeWindow, rhs.executingTimeWindow)
                .append(schedulingFrequencyInMinutes, rhs.schedulingFrequencyInMinutes)
                .append(retryIntervalInMinutes, rhs.retryIntervalInMinutes).append(retryable, rhs.retryable)
                .append(executingDays, rhs.executingDays).append(actionDefaultEiligible, rhs.actionDefaultEiligible)
                .append(typeOfCycle, rhs.typeOfCycle).append(activityType, rhs.activityType).isEquals();
    }

}
