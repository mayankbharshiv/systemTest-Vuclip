package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"activityType", "actionType", "previousSubscriptionState", "currentSubscriptionState",
        "activityResult"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
