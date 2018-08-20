
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product",
    "productPartnerMappings",
    "productCountryMapping",
    "smsConfigs",
    "retry",
    "churnNotifications",
    "adNetworkNotifications",
    "pricePoints",
    "stateConfigs",
    "blackouts",
    "activityFlows"
})
public class SaveProductRequest {

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

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty("productPartnerMappings")
    public List<ProductPartnerMapping> getProductPartnerMappings() {
        return productPartnerMappings;
    }

    @JsonProperty("productPartnerMappings")
    public void setProductPartnerMappings(List<ProductPartnerMapping> productPartnerMappings) {
        this.productPartnerMappings = productPartnerMappings;
    }

    @JsonProperty("productCountryMapping")
    public ProductCountryMapping getProductCountryMapping() {
        return productCountryMapping;
    }

    @JsonProperty("productCountryMapping")
    public void setProductCountryMapping(ProductCountryMapping productCountryMapping) {
        this.productCountryMapping = productCountryMapping;
    }

    @JsonProperty("smsConfigs")
    public List<SmsConfig> getSmsConfigs() {
        return smsConfigs;
    }

    @JsonProperty("smsConfigs")
    public void setSmsConfigs(List<SmsConfig> smsConfigs) {
        this.smsConfigs = smsConfigs;
    }

    @JsonProperty("retry")
    public List<Retry> getRetry() {
        return retry;
    }

    @JsonProperty("retry")
    public void setRetry(List<Retry> retry) {
        this.retry = retry;
    }

    @JsonProperty("churnNotifications")
    public List<ChurnNotification> getChurnNotifications() {
        return churnNotifications;
    }

    @JsonProperty("churnNotifications")
    public void setChurnNotifications(List<ChurnNotification> churnNotifications) {
        this.churnNotifications = churnNotifications;
    }

    @JsonProperty("adNetworkNotifications")
    public List<AdNetworkNotification> getAdNetworkNotifications() {
        return adNetworkNotifications;
    }

    @JsonProperty("adNetworkNotifications")
    public void setAdNetworkNotifications(List<AdNetworkNotification> adNetworkNotifications) {
        this.adNetworkNotifications = adNetworkNotifications;
    }

    @JsonProperty("pricePoints")
    public List<PricePoint> getPricePoints() {
        return pricePoints;
    }

    @JsonProperty("pricePoints")
    public void setPricePoints(List<PricePoint> pricePoints) {
        this.pricePoints = pricePoints;
    }

    @JsonProperty("stateConfigs")
    public List<StateConfig> getStateConfigs() {
        return stateConfigs;
    }

    @JsonProperty("stateConfigs")
    public void setStateConfigs(List<StateConfig> stateConfigs) {
        this.stateConfigs = stateConfigs;
    }

    @JsonProperty("blackouts")
    public List<Blackout> getBlackouts() {
        return blackouts;
    }

    @JsonProperty("blackouts")
    public void setBlackouts(List<Blackout> blackouts) {
        this.blackouts = blackouts;
    }

    @JsonProperty("activityFlows")
    public List<ActivityFlow> getActivityFlows() {
        return activityFlows;
    }

    @JsonProperty("activityFlows")
    public void setActivityFlows(List<ActivityFlow> activityFlows) {
        this.activityFlows = activityFlows;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
