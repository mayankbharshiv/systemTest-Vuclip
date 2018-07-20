
package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({ "smsConfigId", "type", "redirectionContext", "defaultSmsLanguageId", "batchSize", "smsLength",
		"autoPlay", "status", "criterias", "productId", "partnerId", "countryCode", "operationType" })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SmsConfig {

	@JsonProperty("smsConfigId")
	private Integer smsConfigId;
	@JsonProperty("type")
	private String type;
	@JsonProperty("redirectionContext")
	private String redirectionContext;
	@JsonProperty("defaultSmsLanguageId")
	private Integer defaultSmsLanguageId;
	@JsonProperty("batchSize")
	private Integer batchSize;
	@JsonProperty("smsLength")
	private Integer smsLength;
	@JsonProperty("autoPlay")
	private Boolean autoPlay;
	@JsonProperty("status")
	private String status;
	@JsonProperty("criterias")
	private List<Criteria> criterias = null;
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

}