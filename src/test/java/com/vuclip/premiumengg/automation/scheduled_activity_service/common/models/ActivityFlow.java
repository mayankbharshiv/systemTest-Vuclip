package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "activityFlowId", "productId", "partnerId", "countryCode", "name", "billingCode", "mode",
		"actions", "operationType" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityFlow {

	@JsonProperty("activityFlowId")
	private Integer activityFlowId;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("partnerId")
	private Integer partnerId;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("name")
	private String name;
	@JsonProperty("billingCode")
	private String billingCode;
	@JsonProperty("mode")
	private String mode;
	@JsonProperty("actions")
	private List<Action> actions = null;
	@JsonProperty("operationType")
	private String operationType;

}
