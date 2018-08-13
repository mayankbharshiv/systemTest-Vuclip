package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"operationType"})
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
public class ProductVO {
	
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
    private long cassId;
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
