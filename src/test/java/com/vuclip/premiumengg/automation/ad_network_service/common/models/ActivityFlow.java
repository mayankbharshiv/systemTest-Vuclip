package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"activityFlowId", "productId", "partnerId", "countryCode", "name", "billingCode", "mode",
        "actions", "operationType"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ActivityFlow {

    @JsonProperty("activityFlowId")
    private Integer activityFlowId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("billingCode")
    private String billingCode;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("actions")
    private List<Action> actions = null;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}