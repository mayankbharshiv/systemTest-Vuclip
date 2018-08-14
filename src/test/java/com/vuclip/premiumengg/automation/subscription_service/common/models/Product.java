
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
    "productId",
    "productName",
    "productType",
    "storeType",
    "url",
    "context",
    "cassId",
    "encryptionEnable",
    "encryptionValidityInMinutes",
    "callbackUrl",
    "consentCancelUrl",
    "errorUrl",
    "description",
    "status",
    "operationType"
})
public class Product {

    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productType")
    private String productType;
    @JsonProperty("storeType")
    private String storeType;
    @JsonProperty("url")
    private String url;
    @JsonProperty("context")
    private String context;
    @JsonProperty("cassId")
    private Integer cassId;
    @JsonProperty("encryptionEnable")
    private Boolean encryptionEnable;
    @JsonProperty("encryptionValidityInMinutes")
    private Integer encryptionValidityInMinutes;
    @JsonProperty("callbackUrl")
    private String callbackUrl;
    @JsonProperty("consentCancelUrl")
    private String consentCancelUrl;
    @JsonProperty("errorUrl")
    private String errorUrl;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("productType")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("productType")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("storeType")
    public String getStoreType() {
        return storeType;
    }

    @JsonProperty("storeType")
    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("context")
    public String getContext() {
        return context;
    }

    @JsonProperty("context")
    public void setContext(String context) {
        this.context = context;
    }

    @JsonProperty("cassId")
    public Integer getCassId() {
        return cassId;
    }

    @JsonProperty("cassId")
    public void setCassId(Integer cassId) {
        this.cassId = cassId;
    }

    @JsonProperty("encryptionEnable")
    public Boolean getEncryptionEnable() {
        return encryptionEnable;
    }

    @JsonProperty("encryptionEnable")
    public void setEncryptionEnable(Boolean encryptionEnable) {
        this.encryptionEnable = encryptionEnable;
    }

    @JsonProperty("encryptionValidityInMinutes")
    public Integer getEncryptionValidityInMinutes() {
        return encryptionValidityInMinutes;
    }

    @JsonProperty("encryptionValidityInMinutes")
    public void setEncryptionValidityInMinutes(Integer encryptionValidityInMinutes) {
        this.encryptionValidityInMinutes = encryptionValidityInMinutes;
    }

    @JsonProperty("callbackUrl")
    public String getCallbackUrl() {
        return callbackUrl;
    }

    @JsonProperty("callbackUrl")
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @JsonProperty("consentCancelUrl")
    public String getConsentCancelUrl() {
        return consentCancelUrl;
    }

    @JsonProperty("consentCancelUrl")
    public void setConsentCancelUrl(String consentCancelUrl) {
        this.consentCancelUrl = consentCancelUrl;
    }

    @JsonProperty("errorUrl")
    public String getErrorUrl() {
        return errorUrl;
    }

    @JsonProperty("errorUrl")
    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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
