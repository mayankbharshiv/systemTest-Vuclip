package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "jobId",
        "partnerId",
        "productId",
        "country",
        "executingDays",
        "activityType",
        "timeWindow"
})
public class SchedulerRequest {

    @JsonProperty("jobId")
    private Integer jobId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("executingDays")
    private String executingDays;
    @JsonProperty("activityType")
    private String activityType;
    @JsonProperty("timeWindow")
    private String timeWindow;

    /**
     * No args constructor for use in serialization
     */
    public SchedulerRequest() {
    }

    /**
     * @param timeWindow
     * @param jobId
     * @param partnerId
     * @param executingDays
     * @param activityType
     * @param country
     * @param productId
     */
    public SchedulerRequest(Integer jobId, Integer partnerId, Integer productId, String country, String executingDays, String activityType, String timeWindow) {
        super();
        this.jobId = jobId;
        this.partnerId = partnerId;
        this.productId = productId;
        this.country = country;
        this.executingDays = executingDays;
        this.activityType = activityType;
        this.timeWindow = timeWindow;
    }

    @JsonProperty("jobId")
    public Integer getJobId() {
        return jobId;
    }

    @JsonProperty("jobId")
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    @JsonProperty("partnerId")
    public Integer getPartnerId() {
        return partnerId;
    }

    @JsonProperty("partnerId")
    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("executingDays")
    public String getExecutingDays() {
        return executingDays;
    }

    @JsonProperty("executingDays")
    public void setExecutingDays(String executingDays) {
        this.executingDays = executingDays;
    }

    @JsonProperty("activityType")
    public String getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @JsonProperty("timeWindow")
    public String getTimeWindow() {
        return timeWindow;
    }

    @JsonProperty("timeWindow")
    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }

}