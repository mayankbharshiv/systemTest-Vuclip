package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "adNetworkNotificationId",
        "productId",
        "partnerId",
        "countryCode",
        "pricePoint",
        "id",
        "paidPercentage",
        "freePercentage",
        "winbackPercentage"
})
public class PublishAdNetworkNotification {

    @JsonProperty("adNetworkNotificationId")
    private Integer adNetworkNotificationId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("pricePoint")
    private String pricePoint;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("paidPercentage")
    private Integer paidPercentage;
    @JsonProperty("freePercentage")
    private Integer freePercentage;
    @JsonProperty("winbackPercentage")
    private Integer winbackPercentage;

    /**
     * No args constructor for use in serialization
     */
    public PublishAdNetworkNotification() {
    }

    /**
     * @param id
     * @param paidPercentage
     * @param adNetworkNotificationId
     * @param countryCode
     * @param partnerId
     * @param freePercentage
     * @param winbackPercentage
     * @param pricePoint
     * @param productId
     */
    public PublishAdNetworkNotification(Integer adNetworkNotificationId, Integer productId, Integer partnerId, String countryCode, String pricePoint, Integer id, Integer paidPercentage, Integer freePercentage, Integer winbackPercentage) {
        super();
        this.adNetworkNotificationId = adNetworkNotificationId;
        this.productId = productId;
        this.partnerId = partnerId;
        this.countryCode = countryCode;
        this.pricePoint = pricePoint;
        this.id = id;
        this.paidPercentage = paidPercentage;
        this.freePercentage = freePercentage;
        this.winbackPercentage = winbackPercentage;
    }

    @JsonProperty("adNetworkNotificationId")
    public Integer getAdNetworkNotificationId() {
        return adNetworkNotificationId;
    }

    @JsonProperty("adNetworkNotificationId")
    public void setAdNetworkNotificationId(Integer adNetworkNotificationId) {
        this.adNetworkNotificationId = adNetworkNotificationId;
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

    @JsonProperty("pricePoint")
    public String getPricePoint() {
        return pricePoint;
    }

    @JsonProperty("pricePoint")
    public void setPricePoint(String pricePoint) {
        this.pricePoint = pricePoint;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("paidPercentage")
    public Integer getPaidPercentage() {
        return paidPercentage;
    }

    @JsonProperty("paidPercentage")
    public void setPaidPercentage(Integer paidPercentage) {
        this.paidPercentage = paidPercentage;
    }

    @JsonProperty("freePercentage")
    public Integer getFreePercentage() {
        return freePercentage;
    }

    @JsonProperty("freePercentage")
    public void setFreePercentage(Integer freePercentage) {
        this.freePercentage = freePercentage;
    }

    @JsonProperty("winbackPercentage")
    public Integer getWinbackPercentage() {
        return winbackPercentage;
    }

    @JsonProperty("winbackPercentage")
    public void setWinbackPercentage(Integer winbackPercentage) {
        this.winbackPercentage = winbackPercentage;
    }

}
