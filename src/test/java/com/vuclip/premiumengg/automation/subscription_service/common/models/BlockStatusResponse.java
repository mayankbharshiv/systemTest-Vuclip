
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "blockedUserData",
    "blockSummary"
})

@Getter
@Setter
public class BlockStatusResponse {

    @JsonProperty("blockedUserData")
    private BlockedUserData blockedUserData;
    @JsonProperty("blockSummary")
    private String blockSummary;
    
}
