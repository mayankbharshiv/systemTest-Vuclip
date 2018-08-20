package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "successful", "responseCode", "message", "adNetwork" })
public class AdNetworkResponseVO {
	@JsonProperty("successful")
	private boolean successful;
	@JsonProperty("responseCode")
	private int responseCode;
	@JsonProperty("message")
	private String message;
	@JsonProperty("adNetwork")
	private AdNetworkVO adNetwork;


}
