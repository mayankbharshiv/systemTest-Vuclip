
package com.vuclip.premiumengg.automation.schedular_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "partnerId",
    "country",
    "activityType",
    "schedulingFrequencyInMinuntes",
    "executingTimeWindow",
    "executingDays"
})
public class Retry {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("activityType")
    private String activityType;
    @JsonProperty("schedulingFrequencyInMinuntes")
    private Integer schedulingFrequencyInMinuntes;
    @JsonProperty("executingTimeWindow")
    private String executingTimeWindow;
    @JsonProperty("executingDays")
    private String executingDays;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Retry() {
    }

    /**
     * 
     * @param id
     * @param executingTimeWindow
     * @param schedulingFrequencyInMinuntes
     * @param partnerId
     * @param executingDays
     * @param activityType
     * @param country
     */
    public Retry(Integer id, Integer partnerId, String country, String activityType, Integer schedulingFrequencyInMinuntes, String executingTimeWindow, String executingDays) {
        super();
        this.id = id;
        this.partnerId = partnerId;
        this.country = country;
        this.activityType = activityType;
        this.schedulingFrequencyInMinuntes = schedulingFrequencyInMinuntes;
        this.executingTimeWindow = executingTimeWindow;
        this.executingDays = executingDays;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("partnerId")
    public Integer getPartnerId() {
        return partnerId;
    }

    @JsonProperty("partnerId")
    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("activityType")
    public String getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @JsonProperty("schedulingFrequencyInMinuntes")
    public Integer getSchedulingFrequencyInMinuntes() {
        return schedulingFrequencyInMinuntes;
    }

    @JsonProperty("schedulingFrequencyInMinuntes")
    public void setSchedulingFrequencyInMinuntes(Integer schedulingFrequencyInMinuntes) {
        this.schedulingFrequencyInMinuntes = schedulingFrequencyInMinuntes;
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

}
