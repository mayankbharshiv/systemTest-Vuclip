
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "successful", "message", "responseCode" })

@Getter
@Setter
public class FreeTrialResponseStatus {

	@JsonProperty("successful")
	private Boolean successful;
	@JsonProperty("message")
	private String message;
	@JsonProperty("responseCode")
	private String responseCode;
}
