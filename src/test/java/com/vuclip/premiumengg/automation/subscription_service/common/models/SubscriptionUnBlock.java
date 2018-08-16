
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId",
    "productId",
    "partnerId",
    "blockType",
    "country"
})
@Getter
@Setter
public class SubscriptionUnBlock {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("productId")
    private int productId;
    @JsonProperty("partnerId")
    private int partnerId;
    @JsonProperty("blockType")
    private String blockType;
    @JsonProperty("country")
    private String country;
    
    
}
