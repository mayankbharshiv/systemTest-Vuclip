package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"blackoutId", "productId", "partnerId", "countryCode", "period", "operationType",
        "blackoutWindow"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Blackout {

    @JsonProperty("blackoutId")
    private Integer blackoutId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("period")
    private String period;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("blackoutWindow")
    private String blackoutWindow;

}
