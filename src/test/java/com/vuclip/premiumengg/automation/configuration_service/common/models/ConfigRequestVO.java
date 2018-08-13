package com.vuclip.premiumengg.automation.configuration_service.common.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigRequestVO {
	private ProductRequestVO product;
	private List<ProductPartnerMappingRequestVO> productPartnerMappings;
	private ProductCountryMappingRequestVO productCountryMapping;
	private List<SmsConfigRequestVO> smsConfigs;
	private List<RetryRequestVO> retry;
	private List<ChurnNotificationRequestVO> churnNotifications;
	private List<AdNetworkNotificationRequestVO> adNetworkNotifications;
	private List<PricePointRequestVO> pricePoints;
	private List<StateConfigRequestVO> stateConfigs;
	private List<BlackoutRequestVO> blockouts;
	private List<ActivityFlowRequestVO> activityFlows;

}