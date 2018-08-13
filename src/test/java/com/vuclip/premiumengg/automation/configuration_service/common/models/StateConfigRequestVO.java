package com.vuclip.premiumengg.automation.configuration_service.common.models;

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
public class StateConfigRequestVO {
	private int stateConfigId;
	private String productName;
	private String partnerName;
	private String countryName;
	private String pricePoint;
	private int actInitDuration;
	private int activeDuration;
	private int parkingDuration;
	private int graceDuration;
	private int suspendDuration;
	private int blacklistDuration;
}
