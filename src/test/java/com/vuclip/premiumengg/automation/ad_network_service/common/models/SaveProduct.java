package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"product", "productPartnerMappings", "productCountryMapping", "smsConfigs", "retry",
        "churnNotifications", "adNetworkNotifications", "pricePoints", "stateConfigs", "blackouts", "activityFlows"})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SaveProduct {

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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}