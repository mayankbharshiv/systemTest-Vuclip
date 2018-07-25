package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"adNetworkId", "name", "retryLimit", "requestParamName", "notificationUrl", "httpMethod",
        "notifyOnActivity", "status", "churnNotificationUrl", "sourceIdentifier", "operationType"})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SaveAdnetwork {

    @JsonProperty("adNetworkId")
    private Integer adNetworkId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("retryLimit")
    private Integer retryLimit;
    @JsonProperty("requestParamName")
    private String requestParamName;
    @JsonProperty("notificationUrl")
    private String notificationUrl;
    @JsonProperty("httpMethod")
    private String httpMethod;
    @JsonProperty("notifyOnActivity")
    private String notifyOnActivity;
    @JsonProperty("status")
    private String status;
    @JsonProperty("churnNotificationUrl")
    private String churnNotificationUrl;
    @JsonProperty("sourceIdentifier")
    private String sourceIdentifier;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}