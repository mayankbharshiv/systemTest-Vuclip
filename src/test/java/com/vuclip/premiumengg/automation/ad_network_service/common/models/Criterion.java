
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
@JsonPropertyOrder({ "criterionId", "name", "operator", "value", "groupingOperator" })
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}