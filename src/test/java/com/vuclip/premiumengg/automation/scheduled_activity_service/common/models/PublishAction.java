package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "action",
        "flowType"
})
public class PublishAction {

    @JsonProperty("action")
    private String action;
    @JsonProperty("flowType")
    private String flowType;

    /**
     * No args constructor for use in serialization
     */
    public PublishAction() {
    }

    /**
     * @param flowType
     * @param action
     */
    public PublishAction(String action, String flowType) {
        super();
        this.action = action;
        this.flowType = flowType;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("flowType")
    public String getFlowType() {
        return flowType;
    }

    @JsonProperty("flowType")
    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

}
