package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"eventInfo", "userInfo", "activityInfo", "subscriptionInfo", "activityEvent"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
}