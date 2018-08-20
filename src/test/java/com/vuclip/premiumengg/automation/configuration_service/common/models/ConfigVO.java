package com.vuclip.premiumengg.automation.configuration_service.common.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vuclip.premiumengg.automation.ad_network_service.common.models.Country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({ "countries" })

@JsonPropertyOrder({ "product", "productPartnerMappings", "productCountryMapping", "smsConfigs", "retry",
		"churnNotifications", "adNetworkNotifications", "pricePoints", "stateConfigs", "blockouts","activityFlows","countries" })

public class ConfigVO {

	@JsonProperty("product")
	private ProductVO product;
	@JsonProperty("productPartnerMappings")
	private List<ProductPartnerMappingVO> productPartnerMappings = null;
	@JsonProperty("productCountryMapping")
	private ProductCountryMappingVO productCountryMapping;
	@JsonProperty("smsConfigs")
	private List<SmsConfigVO> smsConfigs = null;
	@JsonProperty("retry")
	private List<RetryVO> retry = null;
	@JsonProperty("churnNotifications")
	private List<ChurnNotificationVO> churnNotifications = null;
	@JsonProperty("adNetworkNotifications")
	private List<AdNetworkNotificationVO> adNetworkNotifications = null;
	@JsonProperty("pricePoints")
	private List<PricePointVO> pricePoints = null;
	@JsonProperty("stateConfigs")
	private List<StateConfigVO> stateConfigs = null;
	@JsonProperty("blockouts")
	private List<BlackoutVO> blockouts = null;
	@JsonProperty("activityFlows")
	private List<ActivityFlowVO> activityFlows = new ArrayList<>();
	@JsonProperty("countries")
	private List<Country> countries;
}
