
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "retryId",
    "productId",
    "partnerId",
    "countryCode",
    "activityType",
    "maxRetryCount",
    "batchSize",
    "status",
    "retryIntervalInMinuntes",
    "attemptWindows",
    "schedulingDays",
    "exceutionWindow"
})
public class PublishRetry {

    @JsonProperty("retryId")
    private Integer retryId;
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
    @JsonProperty("batchSize")
    private Integer batchSize;
    @JsonProperty("status")
    private String status;
    @JsonProperty("retryIntervalInMinuntes")
    private Integer retryIntervalInMinuntes;
    @JsonProperty("attemptWindows")
    private String attemptWindows;
    @JsonProperty("schedulingDays")
    private String schedulingDays;
    @JsonProperty("exceutionWindow")
    private String exceutionWindow;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PublishRetry() {
    }

    /**
     * 
     * @param schedulingDays
     * @param retryIntervalInMinuntes
     * @param status
     * @param maxRetryCount
     * @param retryId
     * @param attemptWindows
     * @param countryCode
     * @param partnerId
     * @param batchSize
     * @param exceutionWindow
     * @param activityType
     * @param productId
     */
    public PublishRetry(Integer retryId, Integer productId, Integer partnerId, String countryCode, String activityType, Integer maxRetryCount, Integer batchSize, String status, Integer retryIntervalInMinuntes, String attemptWindows, String schedulingDays, String exceutionWindow) {
        super();
        this.retryId = retryId;
        this.productId = productId;
        this.partnerId = partnerId;
        this.countryCode = countryCode;
        this.activityType = activityType;
        this.maxRetryCount = maxRetryCount;
        this.batchSize = batchSize;
        this.status = status;
        this.retryIntervalInMinuntes = retryIntervalInMinuntes;
        this.attemptWindows = attemptWindows;
        this.schedulingDays = schedulingDays;
        this.exceutionWindow = exceutionWindow;
    }

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

    @JsonProperty("batchSize")
    public Integer getBatchSize() {
        return batchSize;
    }

    @JsonProperty("batchSize")
    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("retryIntervalInMinuntes")
    public Integer getRetryIntervalInMinuntes() {
        return retryIntervalInMinuntes;
    }

    @JsonProperty("retryIntervalInMinuntes")
    public void setRetryIntervalInMinuntes(Integer retryIntervalInMinuntes) {
        this.retryIntervalInMinuntes = retryIntervalInMinuntes;
    }

    @JsonProperty("attemptWindows")
    public String getAttemptWindows() {
        return attemptWindows;
    }

    @JsonProperty("attemptWindows")
    public void setAttemptWindows(String attemptWindows) {
        this.attemptWindows = attemptWindows;
    }

    @JsonProperty("schedulingDays")
    public String getSchedulingDays() {
        return schedulingDays;
    }

    @JsonProperty("schedulingDays")
    public void setSchedulingDays(String schedulingDays) {
        this.schedulingDays = schedulingDays;
    }

    @JsonProperty("exceutionWindow")
    public String getExceutionWindow() {
        return exceutionWindow;
    }

    @JsonProperty("exceutionWindow")
    public void setExceutionWindow(String exceutionWindow) {
        this.exceutionWindow = exceutionWindow;
    }

}
