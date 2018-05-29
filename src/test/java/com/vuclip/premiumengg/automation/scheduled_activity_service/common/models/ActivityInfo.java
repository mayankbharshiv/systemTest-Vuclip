
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "activityType",
    "actionType",
    "previousSubscriptionState",
    "currentSubscriptionState",
    "activityResult"
})
public class ActivityInfo {

    @JsonProperty("activityType")
    private String activityType;
    @JsonProperty("actionType")
    private String actionType;
    @JsonProperty("previousSubscriptionState")
    private String previousSubscriptionState;
    @JsonProperty("currentSubscriptionState")
    private String currentSubscriptionState;
    @JsonProperty("activityResult")
    private String activityResult;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ActivityInfo() {
    }

    /**
     * 
     * @param activityResult
     * @param previousSubscriptionState
     * @param currentSubscriptionState
     * @param actionType
     * @param activityType
     */
    public ActivityInfo(String activityType, String actionType, String previousSubscriptionState, String currentSubscriptionState, String activityResult) {
        super();
        this.activityType = activityType;
        this.actionType = actionType;
        this.previousSubscriptionState = previousSubscriptionState;
        this.currentSubscriptionState = currentSubscriptionState;
        this.activityResult = activityResult;
    }

    @JsonProperty("activityType")
    public String getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @JsonProperty("actionType")
    public String getActionType() {
        return actionType;
    }

    @JsonProperty("actionType")
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @JsonProperty("previousSubscriptionState")
    public String getPreviousSubscriptionState() {
        return previousSubscriptionState;
    }

    @JsonProperty("previousSubscriptionState")
    public void setPreviousSubscriptionState(String previousSubscriptionState) {
        this.previousSubscriptionState = previousSubscriptionState;
    }

    @JsonProperty("currentSubscriptionState")
    public String getCurrentSubscriptionState() {
        return currentSubscriptionState;
    }

    @JsonProperty("currentSubscriptionState")
    public void setCurrentSubscriptionState(String currentSubscriptionState) {
        this.currentSubscriptionState = currentSubscriptionState;
    }

    @JsonProperty("activityResult")
    public String getActivityResult() {
        return activityResult;
    }

    @JsonProperty("activityResult")
    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult;
    }

}
