package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productId",
        "partnerId",
        "countryCode",
        "blackoutWindow"
})
public class PublishBlackout {

    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("blackoutWindow")
    private String blackoutWindow;

    /**
     * No args constructor for use in serialization
     */
    public PublishBlackout() {
    }

    /**
     * @param blackoutWindow
     * @param countryCode
     * @param partnerId
     * @param productId
     */
    public PublishBlackout(Integer productId, Integer partnerId, String countryCode, String blackoutWindow) {
        super();
        this.productId = productId;
        this.partnerId = partnerId;
        this.countryCode = countryCode;
        this.blackoutWindow = blackoutWindow;
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

    @JsonProperty("blackoutWindow")
    public String getBlackoutWindow() {
        return blackoutWindow;
    }

    @JsonProperty("blackoutWindow")
    public void setBlackoutWindow(String blackoutWindow) {
        this.blackoutWindow = blackoutWindow;
    }

}
