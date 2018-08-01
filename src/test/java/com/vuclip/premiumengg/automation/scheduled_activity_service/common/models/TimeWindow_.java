package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"startTime", "endTime"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimeWindow_ {

    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;

}