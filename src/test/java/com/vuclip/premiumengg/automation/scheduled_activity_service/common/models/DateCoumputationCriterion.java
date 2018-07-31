package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "dateComputationCriterionId", "name", "operator", "value", "unit" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DateCoumputationCriterion {

	@JsonProperty("dateComputationCriterionId")
	private Integer dateComputationCriterionId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("operator")
	private String operator;
	@JsonProperty("value")
	private String value;
	@JsonProperty("unit")
	private String unit;

}
