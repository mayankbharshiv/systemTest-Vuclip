package com.vuclip.premiumengg.automation.configuration_service.common.models;

import java.util.List;

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
@EqualsAndHashCode(exclude= {"operationType"})
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ActivityFlowVO {
	private int activityFlowId;
	private ActivityType name;
	private String billingCode;
	private Mode mode;
	private List<ActionVO> actions;
	private String productName;
	private String partnerName;
	private String countryName;
	private String operationType;
}
