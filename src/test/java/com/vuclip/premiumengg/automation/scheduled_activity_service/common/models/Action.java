
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
@JsonPropertyOrder({ "action", "flowType" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Action {

	@JsonProperty("action")
	private String action;
	@JsonProperty("flowType")
	private String flowType;

}
