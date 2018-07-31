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
@JsonPropertyOrder({ "criterionId", "name", "operator", "value", "groupingOperator" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Criterion {

	@JsonProperty("criterionId")
	private Integer criterionId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("operator")
	private String operator;
	@JsonProperty("value")
	private String value;
	@JsonProperty("groupingOperator")
	private String groupingOperator;

}
