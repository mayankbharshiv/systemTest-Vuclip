
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "freeTrialEligibility", "response" })
@Getter
@Setter
public class FreeTrialResponse {

	@JsonProperty("freeTrialEligibility")
	private Boolean freeTrialEligibility;
	@JsonProperty("response")
	private FreeTrialResponseStatus response;
}
