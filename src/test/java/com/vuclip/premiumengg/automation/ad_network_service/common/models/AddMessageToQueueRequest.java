package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"properties", "routing_key", "payload", "payload_encoding"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddMessageToQueueRequest {

    @JsonProperty("properties")
    private Properties properties;
    @JsonProperty("routing_key")
    private String routingKey;
    @JsonProperty("payload")
    private String payload;
    @JsonProperty("payload_encoding")
    private String payloadEncoding;


}
