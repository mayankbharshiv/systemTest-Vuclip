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
public class AdNetworkNotificationRequestVO {
	private int adNetworkNotificationId;
	private String productName;
	private String partnerName;
	private String countryName;
	private String pricePoint;
	private String name;
	private int paidPercentage;
	private int freePercentage;
	private int winbackPercentage;
}
