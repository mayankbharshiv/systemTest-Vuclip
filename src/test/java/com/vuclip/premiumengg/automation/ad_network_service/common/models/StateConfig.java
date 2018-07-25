package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"stateConfigId", "productId", "partnerId", "countryCode", "operationType", "pricePoint",
        "actInitDuration", "activeDuration", "parkingDuration", "graceDuration", "suspendDuration",
        "blacklistDuration"})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class StateConfig {

    @JsonProperty("stateConfigId")
    private Integer stateConfigId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;
    @JsonProperty("pricePoint")
    private String pricePoint;
    @JsonProperty("actInitDuration")
    private Integer actInitDuration;
    @JsonProperty("activeDuration")
    private Integer activeDuration;
    @JsonProperty("parkingDuration")
    private Integer parkingDuration;
    @JsonProperty("graceDuration")
    private Integer graceDuration;
    @JsonProperty("suspendDuration")
    private Integer suspendDuration;
    @JsonProperty("blacklistDuration")
    private Integer blacklistDuration;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}