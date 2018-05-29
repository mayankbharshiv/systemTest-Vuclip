
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "subscriptionId",
    "subscriptionStatus",
    "startDate",
    "endDate",
    "activationDate",
    "deactivationDate",
    "subscriptionBillingCode",
    "chargedBillingCode",
    "fallbackBillingCode",
    "mode",
    "circleCode",
    "customerTransactionId",
    "chargedPrice",
    "partnerId",
    "productId",
    "itemId",
    "itemTypeId",
    "nextBillingDate",
    "serviceId",
    "isDelayed",
    "paid",
    "country"
})
public class SubscriptionInfo {

    @JsonProperty("subscriptionId")
    private Integer subscriptionId;
    @JsonProperty("subscriptionStatus")
    private String subscriptionStatus;
    @JsonProperty("startDate")
    private Integer startDate;
    @JsonProperty("endDate")
    private Integer endDate;
    @JsonProperty("activationDate")
    private Integer activationDate;
    @JsonProperty("deactivationDate")
    private Integer deactivationDate;
    @JsonProperty("subscriptionBillingCode")
    private String subscriptionBillingCode;
    @JsonProperty("chargedBillingCode")
    private String chargedBillingCode;
    @JsonProperty("fallbackBillingCode")
    private Object fallbackBillingCode;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("circleCode")
    private Object circleCode;
    @JsonProperty("customerTransactionId")
    private String customerTransactionId;
    @JsonProperty("chargedPrice")
    private Double chargedPrice;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("itemId")
    private Integer itemId;
    @JsonProperty("itemTypeId")
    private Integer itemTypeId;
    @JsonProperty("nextBillingDate")
    private Integer nextBillingDate;
    @JsonProperty("serviceId")
    private String serviceId;
    @JsonProperty("isDelayed")
    private Boolean isDelayed;
    @JsonProperty("paid")
    private Boolean paid;
    @JsonProperty("country")
    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubscriptionInfo() {
    }

    /**
     * 
     * @param chargedPrice
     * @param startDate
     * @param partnerId
     * @param deactivationDate
     * @param endDate
     * @param activationDate
     * @param itemId
     * @param circleCode
     * @param subscriptionId
     * @param country
     * @param itemTypeId
     * @param productId
     * @param chargedBillingCode
     * @param mode
     * @param customerTransactionId
     * @param paid
     * @param serviceId
     * @param fallbackBillingCode
     * @param nextBillingDate
     * @param subscriptionBillingCode
     * @param isDelayed
     * @param subscriptionStatus
     */
    public SubscriptionInfo(Integer subscriptionId, String subscriptionStatus, Integer startDate, Integer endDate, Integer activationDate, Integer deactivationDate, String subscriptionBillingCode, String chargedBillingCode, Object fallbackBillingCode, String mode, Object circleCode, String customerTransactionId, Double chargedPrice, Integer partnerId, Integer productId, Integer itemId, Integer itemTypeId, Integer nextBillingDate, String serviceId, Boolean isDelayed, Boolean paid, String country) {
        super();
        this.subscriptionId = subscriptionId;
        this.subscriptionStatus = subscriptionStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activationDate = activationDate;
        this.deactivationDate = deactivationDate;
        this.subscriptionBillingCode = subscriptionBillingCode;
        this.chargedBillingCode = chargedBillingCode;
        this.fallbackBillingCode = fallbackBillingCode;
        this.mode = mode;
        this.circleCode = circleCode;
        this.customerTransactionId = customerTransactionId;
        this.chargedPrice = chargedPrice;
        this.partnerId = partnerId;
        this.productId = productId;
        this.itemId = itemId;
        this.itemTypeId = itemTypeId;
        this.nextBillingDate = nextBillingDate;
        this.serviceId = serviceId;
        this.isDelayed = isDelayed;
        this.paid = paid;
        this.country = country;
    }

    @JsonProperty("subscriptionId")
    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    @JsonProperty("subscriptionId")
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @JsonProperty("subscriptionStatus")
    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    @JsonProperty("subscriptionStatus")
    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    @JsonProperty("startDate")
    public Integer getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public Integer getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("activationDate")
    public Integer getActivationDate() {
        return activationDate;
    }

    @JsonProperty("activationDate")
    public void setActivationDate(Integer activationDate) {
        this.activationDate = activationDate;
    }

    @JsonProperty("deactivationDate")
    public Integer getDeactivationDate() {
        return deactivationDate;
    }

    @JsonProperty("deactivationDate")
    public void setDeactivationDate(Integer deactivationDate) {
        this.deactivationDate = deactivationDate;
    }

    @JsonProperty("subscriptionBillingCode")
    public String getSubscriptionBillingCode() {
        return subscriptionBillingCode;
    }

    @JsonProperty("subscriptionBillingCode")
    public void setSubscriptionBillingCode(String subscriptionBillingCode) {
        this.subscriptionBillingCode = subscriptionBillingCode;
    }

    @JsonProperty("chargedBillingCode")
    public String getChargedBillingCode() {
        return chargedBillingCode;
    }

    @JsonProperty("chargedBillingCode")
    public void setChargedBillingCode(String chargedBillingCode) {
        this.chargedBillingCode = chargedBillingCode;
    }

    @JsonProperty("fallbackBillingCode")
    public Object getFallbackBillingCode() {
        return fallbackBillingCode;
    }

    @JsonProperty("fallbackBillingCode")
    public void setFallbackBillingCode(Object fallbackBillingCode) {
        this.fallbackBillingCode = fallbackBillingCode;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("circleCode")
    public Object getCircleCode() {
        return circleCode;
    }

    @JsonProperty("circleCode")
    public void setCircleCode(Object circleCode) {
        this.circleCode = circleCode;
    }

    @JsonProperty("customerTransactionId")
    public String getCustomerTransactionId() {
        return customerTransactionId;
    }

    @JsonProperty("customerTransactionId")
    public void setCustomerTransactionId(String customerTransactionId) {
        this.customerTransactionId = customerTransactionId;
    }

    @JsonProperty("chargedPrice")
    public Double getChargedPrice() {
        return chargedPrice;
    }

    @JsonProperty("chargedPrice")
    public void setChargedPrice(Double chargedPrice) {
        this.chargedPrice = chargedPrice;
    }

    @JsonProperty("partnerId")
    public Integer getPartnerId() {
        return partnerId;
    }

    @JsonProperty("partnerId")
    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("itemId")
    public Integer getItemId() {
        return itemId;
    }

    @JsonProperty("itemId")
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("itemTypeId")
    public Integer getItemTypeId() {
        return itemTypeId;
    }

    @JsonProperty("itemTypeId")
    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    @JsonProperty("nextBillingDate")
    public Integer getNextBillingDate() {
        return nextBillingDate;
    }

    @JsonProperty("nextBillingDate")
    public void setNextBillingDate(Integer nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    @JsonProperty("serviceId")
    public String getServiceId() {
        return serviceId;
    }

    @JsonProperty("serviceId")
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @JsonProperty("isDelayed")
    public Boolean getIsDelayed() {
        return isDelayed;
    }

    @JsonProperty("isDelayed")
    public void setIsDelayed(Boolean isDelayed) {
        this.isDelayed = isDelayed;
    }

    @JsonProperty("paid")
    public Boolean getPaid() {
        return paid;
    }

    @JsonProperty("paid")
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

}
