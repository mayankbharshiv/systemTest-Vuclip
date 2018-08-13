package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class AdNetworkVO {
	private Integer adNetworkId;
	private String name;
	private Integer retryLimit;
	private String requestParamName;
	private String notificationUrl;
	private String httpMethod;
	private String notifyOnActivity;
	private String status;
	private String churnNotificationUrl;
	private String sourceIdentifier;
	private String operationType;
}
