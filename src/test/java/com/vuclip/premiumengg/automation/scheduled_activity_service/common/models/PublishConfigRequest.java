package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"product", "productPartnerMappings", "productCountryMapping", "smsConfigs", "retry",
        "churnNotifications", "adNetworkNotifications", "pricePoints", "stateConfigs", "blackouts", "activityFlows"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublishConfigRequest {

    @JsonProperty("product")
    private Product product;
    @JsonProperty("productPartnerMappings")
    private List<ProductPartnerMapping> productPartnerMappings = null;
    @JsonProperty("productCountryMapping")
    private ProductCountryMapping productCountryMapping;
    @JsonProperty("smsConfigs")
    private List<SmsConfig> smsConfigs = null;
    @JsonProperty("retry")
    private List<Retry> retry = null;
    @JsonProperty("churnNotifications")
    private List<ChurnNotification> churnNotifications = null;
    @JsonProperty("adNetworkNotifications")
    private List<AdNetworkNotification> adNetworkNotifications = null;
    @JsonProperty("pricePoints")
    private List<PricePoint> pricePoints = null;
    @JsonProperty("stateConfigs")
    private List<StateConfig> stateConfigs = null;
    @JsonProperty("blackouts")
    private List<Blackout> blackouts = null;
    @JsonProperty("activityFlows")
    private List<ActivityFlow> activityFlows = null;

}
