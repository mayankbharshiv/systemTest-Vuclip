package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"TimeWindow"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TimeWindow {

    @JsonProperty("TimeWindow")
    private TimeWindow_ timeWindow;
}