package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"productId", "partnerId", "countryCode", "activityType", "subscriptionId", "attemptNumber"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SASQueueResponse {

    @JsonProperty("productId")
    private Object productId;
    @JsonProperty("partnerId")
    private Object partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("activityType")
    private String activitType;
    @JsonProperty("subscriptionId")
    private Object subscriptionId;
    @JsonProperty("attemptNumber")
    private Object attemptNumber;

}