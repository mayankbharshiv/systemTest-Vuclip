package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CriterionVO {
	private int criterionId;
	private CriterionName name;
	private CriterionOperator operator;
	private String value;
	private GroupingOperator groupingOperator;
}
