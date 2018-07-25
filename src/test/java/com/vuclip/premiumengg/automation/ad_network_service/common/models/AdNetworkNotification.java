package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"adNetworkNotificationId", "productId", "partnerId", "countryCode", "id", "paidPercentage",
        "freePercentage", "winbackPercentage", "operationType"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdNetworkNotification {

    @JsonProperty("adNetworkNotificationId")
    private Integer adNetworkNotificationId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("paidPercentage")
    private Integer paidPercentage;
    @JsonProperty("freePercentage")
    private Integer freePercentage;
    @JsonProperty("winbackPercentage")
    private Integer winbackPercentage;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}