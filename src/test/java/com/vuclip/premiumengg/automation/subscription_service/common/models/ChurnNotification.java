
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "churnNotificationId",
    "typeOfChurn",
    "period",
    "productId",
    "partnerId",
    "countryCode",
    "operationType"
})
public class ChurnNotification {

    @JsonProperty("churnNotificationId")
    private Integer churnNotificationId;
    @JsonProperty("typeOfChurn")
    private String typeOfChurn;
    @JsonProperty("period")
    private String period;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("churnNotificationId")
    public Integer getChurnNotificationId() {
        return churnNotificationId;
    }

    @JsonProperty("churnNotificationId")
    public void setChurnNotificationId(Integer churnNotificationId) {
        this.churnNotificationId = churnNotificationId;
    }

    @JsonProperty("typeOfChurn")
    public String getTypeOfChurn() {
        return typeOfChurn;
    }

    @JsonProperty("typeOfChurn")
    public void setTypeOfChurn(String typeOfChurn) {
        this.typeOfChurn = typeOfChurn;
    }

    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("partnerId")
    public Integer getPartnerId() {
        return partnerId;
    }

    @JsonProperty("partnerId")
    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("operationType")
    public String getOperationType() {
        return operationType;
    }

    @JsonProperty("operationType")
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
