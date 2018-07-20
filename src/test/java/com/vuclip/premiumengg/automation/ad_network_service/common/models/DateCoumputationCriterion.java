
package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "dateComputationCriterionId", "name", "operator", "value", "unit" })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}