package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonPropertyOrder({
    "successful",
    "responseCode",
    "message",
    "config"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigResponseVO {

	@JsonProperty("successful")
	private Boolean successful;
	@JsonProperty("responseCode")
	private Integer responseCode;
	@JsonProperty("message")
	private String message;
	@JsonProperty("config")
	private ConfigVO config;

	
}
