package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "eventType",
        "userId",
        "productId",
        "partnerId",
        "subscriptionBillingCode",
        "chargedBillingCode",
        "fallbackBillingCode",
        "mode",
        "action",
        "activity",
        "transactionId",
        "transactionState",
        "chargedPrice",
        "partnerTransactionId",
        "itemId",
        "itemTypeId",
        "actionResult",
        "serviceId",
        "subscriptionId",
        "circleCode",
        "errorCode",
        "errorDesc",
        "delayed",
        "closed",
        "customerTransactionId",
        "userPreferredLanguage",
        "userSource",
        "nextBillingDate"
})
public class ActivityEvent {

    @JsonProperty("eventType")
    private String eventType;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("subscriptionBillingCode")
    private String subscriptionBillingCode;
    @JsonProperty("chargedBillingCode")
    private String chargedBillingCode;
    @JsonProperty("fallbackBillingCode")
    private String fallbackBillingCode;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("action")
    private String action;
    @JsonProperty("activity")
    private Object activity;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("transactionState")
    private String transactionState;
    @JsonProperty("chargedPrice")
    private Double chargedPrice;
    @JsonProperty("partnerTransactionId")
    private Object partnerTransactionId;
    @JsonProperty("itemId")
    private Integer itemId;
    @JsonProperty("itemTypeId")
    private Integer itemTypeId;
    @JsonProperty("actionResult")
    private String actionResult;
    @JsonProperty("serviceId")
    private String serviceId;
    @JsonProperty("subscriptionId")
    private Integer subscriptionId;
    @JsonProperty("circleCode")
    private Object circleCode;
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("errorDesc")
    private String errorDesc;
    @JsonProperty("delayed")
    private Boolean delayed;
    @JsonProperty("closed")
    private Boolean closed;
    @JsonProperty("customerTransactionId")
    private String customerTransactionId;
    @JsonProperty("userPreferredLanguage")
    private String userPreferredLanguage;
    @JsonProperty("userSource")
    private String userSource;
    @JsonProperty("nextBillingDate")
    private BigInteger nextBillingDate;

    /**
     * No args constructor for use in serialization
     */
    public ActivityEvent() {
    }

    /**
     * @param chargedPrice
     * @param transactionId
     * @param partnerId
     * @param userSource
     * @param eventType
     * @param circleCode
     * @param subscriptionId
     * @param mode
     * @param chargedBillingCode
     * @param itemTypeId
     * @param transactionState
     * @param customerTransactionId
     * @param actionResult
     * @param nextBillingDate
     * @param userId
     * @param action
     * @param closed
     * @param partnerTransactionId
     * @param activity
     * @param userPreferredLanguage
     * @param errorDesc
     * @param delayed
     * @param itemId
     * @param productId
     * @param fallbackBillingCode
     * @param serviceId
     * @param errorCode
     * @param subscriptionBillingCode
     */
    public ActivityEvent(String eventType, String userId, Integer productId, Integer partnerId, String subscriptionBillingCode, String chargedBillingCode, String fallbackBillingCode, String mode, String action, Object activity, String transactionId, String transactionState, Double chargedPrice, Object partnerTransactionId, Integer itemId, Integer itemTypeId, String actionResult, String serviceId, Integer subscriptionId, Object circleCode, String errorCode, String errorDesc, Boolean delayed, Boolean closed, String customerTransactionId, String userPreferredLanguage, String userSource, BigInteger nextBillingDate) {
        super();
        this.eventType = eventType;
        this.userId = userId;
        this.productId = productId;
        this.partnerId = partnerId;
        this.subscriptionBillingCode = subscriptionBillingCode;
        this.chargedBillingCode = chargedBillingCode;
        this.fallbackBillingCode = fallbackBillingCode;
        this.mode = mode;
        this.action = action;
        this.activity = activity;
        this.transactionId = transactionId;
        this.transactionState = transactionState;
        this.chargedPrice = chargedPrice;
        this.partnerTransactionId = partnerTransactionId;
        this.itemId = itemId;
        this.itemTypeId = itemTypeId;
        this.actionResult = actionResult;
        this.serviceId = serviceId;
        this.subscriptionId = subscriptionId;
        this.circleCode = circleCode;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.delayed = delayed;
        this.closed = closed;
        this.customerTransactionId = customerTransactionId;
        this.userPreferredLanguage = userPreferredLanguage;
        this.userSource = userSource;
        this.nextBillingDate = nextBillingDate;
    }

    @JsonProperty("eventType")
    public String getEventType() {
        return eventType;
    }

    @JsonProperty("eventType")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getFallbackBillingCode() {
        return fallbackBillingCode;
    }

    @JsonProperty("fallbackBillingCode")
    public void setFallbackBillingCode(String fallbackBillingCode) {
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

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("activity")
    public Object getActivity() {
        return activity;
    }

    @JsonProperty("activity")
    public void setActivity(Object activity) {
        this.activity = activity;
    }

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("transactionState")
    public String getTransactionState() {
        return transactionState;
    }

    @JsonProperty("transactionState")
    public void setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }

    @JsonProperty("chargedPrice")
    public Double getChargedPrice() {
        return chargedPrice;
    }

    @JsonProperty("chargedPrice")
    public void setChargedPrice(Double chargedPrice) {
        this.chargedPrice = chargedPrice;
    }

    @JsonProperty("partnerTransactionId")
    public Object getPartnerTransactionId() {
        return partnerTransactionId;
    }

    @JsonProperty("partnerTransactionId")
    public void setPartnerTransactionId(Object partnerTransactionId) {
        this.partnerTransactionId = partnerTransactionId;
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

    @JsonProperty("actionResult")
    public String getActionResult() {
        return actionResult;
    }

    @JsonProperty("actionResult")
    public void setActionResult(String actionResult) {
        this.actionResult = actionResult;
    }

    @JsonProperty("serviceId")
    public String getServiceId() {
        return serviceId;
    }

    @JsonProperty("serviceId")
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @JsonProperty("subscriptionId")
    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    @JsonProperty("subscriptionId")
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @JsonProperty("circleCode")
    public Object getCircleCode() {
        return circleCode;
    }

    @JsonProperty("circleCode")
    public void setCircleCode(Object circleCode) {
        this.circleCode = circleCode;
    }

    @JsonProperty("errorCode")
    public String getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("errorDesc")
    public String getErrorDesc() {
        return errorDesc;
    }

    @JsonProperty("errorDesc")
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @JsonProperty("delayed")
    public Boolean getDelayed() {
        return delayed;
    }

    @JsonProperty("delayed")
    public void setDelayed(Boolean delayed) {
        this.delayed = delayed;
    }

    @JsonProperty("closed")
    public Boolean getClosed() {
        return closed;
    }

    @JsonProperty("closed")
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    @JsonProperty("customerTransactionId")
    public String getCustomerTransactionId() {
        return customerTransactionId;
    }

    @JsonProperty("customerTransactionId")
    public void setCustomerTransactionId(String customerTransactionId) {
        this.customerTransactionId = customerTransactionId;
    }

    @JsonProperty("userPreferredLanguage")
    public String getUserPreferredLanguage() {
        return userPreferredLanguage;
    }

    @JsonProperty("userPreferredLanguage")
    public void setUserPreferredLanguage(String userPreferredLanguage) {
        this.userPreferredLanguage = userPreferredLanguage;
    }

    @JsonProperty("userSource")
    public String getUserSource() {
        return userSource;
    }

    @JsonProperty("userSource")
    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    @JsonProperty("nextBillingDate")
    public BigInteger getNextBillingDate() {
        return nextBillingDate;
    }

    @JsonProperty("nextBillingDate")
    public void setNextBillingDate(BigInteger nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

}
