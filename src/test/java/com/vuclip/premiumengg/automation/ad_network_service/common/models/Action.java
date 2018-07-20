
package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "actionId",
    "action",
    "flowType"
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Action {

    @JsonProperty("actionId")
    private Integer actionId;
    @JsonProperty("action")
    private String action;
    @JsonProperty("flowType")
    private String flowType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
