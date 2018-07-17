package com.vuclip.premiumengg.automation.schedular_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "properties", "routing_key", "payload", "payload_encoding" })
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage {

	@JsonProperty("properties")
	private Properties properties;
	@JsonProperty("routing_key")
	private String routingKey;
	@JsonProperty("payload")
	private String payload;
	@JsonProperty("payload_encoding")
	private String payloadEncoding;

}