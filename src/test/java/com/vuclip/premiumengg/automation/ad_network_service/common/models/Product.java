
package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "productName", "productType", "storeType", "url", "context", "cassId",
		"encryptionEnable", "encryptionValidityInMinutes", "callbackUrl", "consentCancelUrl", "errorUrl", "description",
		"status", "operationType" })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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

}