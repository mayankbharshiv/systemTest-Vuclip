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
public class ActivityFlowRequestVO {
	private int activityFlowId;
	private String productName;
	private String partnerName;
	private String countryName;
	private ActivityType name;
	private String billingCode;
	private Mode mode;
	private List<ActionVO> actions;
}
