
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "eventInfo",
    "userInfo",
    "activityInfo",
    "subscriptionInfo",
    "activityEvent"
})
public class UserSubscriptionRequest {

    @JsonProperty("eventInfo")
    private EventInfo eventInfo;
    @JsonProperty("userInfo")
    private UserInfo userInfo;
    @JsonProperty("activityInfo")
    private ActivityInfo activityInfo;
    @JsonProperty("subscriptionInfo")
    private SubscriptionInfo subscriptionInfo;
    @JsonProperty("activityEvent")
    private ActivityEvent activityEvent;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserSubscriptionRequest() {
    }

    /**
     * 
     * @param activityInfo
     * @param eventInfo
     * @param subscriptionInfo
     * @param activityEvent
     * @param userInfo
     */
    public UserSubscriptionRequest(EventInfo eventInfo, UserInfo userInfo, ActivityInfo activityInfo, SubscriptionInfo subscriptionInfo, ActivityEvent activityEvent) {
        super();
        this.eventInfo = eventInfo;
        this.userInfo = userInfo;
        this.activityInfo = activityInfo;
        this.subscriptionInfo = subscriptionInfo;
        this.activityEvent = activityEvent;
    }

    @JsonProperty("eventInfo")
    public EventInfo getEventInfo() {
        return eventInfo;
    }

    @JsonProperty("eventInfo")
    public void setEventInfo(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
    }

    @JsonProperty("userInfo")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @JsonProperty("userInfo")
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @JsonProperty("activityInfo")
    public ActivityInfo getActivityInfo() {
        return activityInfo;
    }

    @JsonProperty("activityInfo")
    public void setActivityInfo(ActivityInfo activityInfo) {
        this.activityInfo = activityInfo;
    }

    @JsonProperty("subscriptionInfo")
    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    @JsonProperty("subscriptionInfo")
    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

    @JsonProperty("activityEvent")
    public ActivityEvent getActivityEvent() {
        return activityEvent;
    }

    @JsonProperty("activityEvent")
    public void setActivityEvent(ActivityEvent activityEvent) {
        this.activityEvent = activityEvent;
    }

}
