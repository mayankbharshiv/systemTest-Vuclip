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
@EqualsAndHashCode(exclude= {"operationType"})
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StateConfigVO {
	private int stateConfigId;
	private int actInitDuration;
	private int activeDuration;
	private int parkingDuration;
	private int graceDuration;
	private int suspendDuration;
	private int blacklistDuration;
	private String productName;
	private String partnerName;
	private String countryName;
	private String pricePoint;
	private String operationType;
}
