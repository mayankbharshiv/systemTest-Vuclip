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
public class AdNetworkRequestVO {
	private String name;
	private int retryLimit;
	private String requestParamName;
	private String notificationUrl;
	private String httpMethod;
	private String notifyOnActivity;
	private String status;
	private String churnNotificationUrl;
	private String sourceIdentifier;
}
