
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "billingPackageId",
    "partnerId",
    "mode",
    "actions"
})
public class PublishActivityFlow {

    @JsonProperty("name")
    private String name;
    @JsonProperty("billingPackageId")
    private String billingPackageId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("actions")
    private List<PublishAction> actions = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PublishActivityFlow() {
    }

    /**
     * 
     * @param billingPackageId
     * @param name
     * @param partnerId
     * @param actions
     * @param mode
     */
    public PublishActivityFlow(String name, String billingPackageId, Integer partnerId, String mode, List<PublishAction> actions) {
        super();
        this.name = name;
        this.billingPackageId = billingPackageId;
        this.partnerId = partnerId;
        this.mode = mode;
        this.actions = actions;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("billingPackageId")
    public String getBillingPackageId() {
        return billingPackageId;
    }

    @JsonProperty("billingPackageId")
    public void setBillingPackageId(String billingPackageId) {
        this.billingPackageId = billingPackageId;
    }

    @JsonProperty("partnerId")
    public Integer getPartnerId() {
        return partnerId;
    }

    @JsonProperty("partnerId")
    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("actions")
    public List<PublishAction> getActions() {
        return actions;
    }

    @JsonProperty("actions")
    public void setActions(List<PublishAction> actions) {
        this.actions = actions;
    }

}
