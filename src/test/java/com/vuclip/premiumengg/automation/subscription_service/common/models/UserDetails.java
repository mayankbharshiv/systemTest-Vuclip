
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId"
})
@Getter
@Setter
public class UserDetails {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("msisdn")
    private String msisdn;
  }
