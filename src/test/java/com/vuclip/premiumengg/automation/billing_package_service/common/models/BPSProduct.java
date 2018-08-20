package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"productId", "productName", "productType", "storeType", "url", "context", "cassId",
        "encryptionEnable", "encryptionValidityInMinutes", "callbackUrl", "consentCancelUrl", "errorUrl", "description",
        "status", "operationType"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BPSProduct {

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

}