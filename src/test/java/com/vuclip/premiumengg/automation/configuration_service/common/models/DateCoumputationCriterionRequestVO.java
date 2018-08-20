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
public class DateCoumputationCriterionRequestVO {
	private DateCoumputationCriterionName name;
	private DateCoumputationCriterionOperator operator;
	private String value;
	private TimeUnit unit;
}
