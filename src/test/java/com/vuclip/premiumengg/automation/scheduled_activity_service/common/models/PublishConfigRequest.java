
package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product",
    "productPartnerMappings",
    "productCountryMapping",
    "adNetworkNotifications",
    "activityFlows",
    "pricePoints",
    "retry",
    "blackouts"
})
public class PublishConfigRequest {

    @JsonProperty("product")
    private PublishProduct product;
    @JsonProperty("productPartnerMappings")
    private List<PublishProductPartnerMapping> productPartnerMappings = null;
    @JsonProperty("productCountryMapping")
    private PublishProductCountryMapping productCountryMapping;
    @JsonProperty("adNetworkNotifications")
    private List<PublishAdNetworkNotification> adNetworkNotifications = null;
    @JsonProperty("activityFlows")
    private List<PublishActivityFlow> activityFlows = null;
    @JsonProperty("pricePoints")
    private List<PublishPricePoint> pricePoints = null;
    @JsonProperty("retry")
    private List<PublishRetry> retry = null;
    @JsonProperty("blackouts")
    private List<PublishBlackout> blackouts = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PublishConfigRequest() {
    }

    /**
     * 
     * @param pricePoints
     * @param product
     * @param productPartnerMappings
     * @param adNetworkNotifications
     * @param blackouts
     * @param productCountryMapping
     * @param activityFlows
     * @param retry
     */
    public PublishConfigRequest(PublishProduct product, List<PublishProductPartnerMapping> productPartnerMappings, PublishProductCountryMapping productCountryMapping, List<PublishAdNetworkNotification> adNetworkNotifications, List<PublishActivityFlow> activityFlows, List<PublishPricePoint> pricePoints, List<PublishRetry> retry, List<PublishBlackout> blackouts) {
        super();
        this.product = product;
        this.productPartnerMappings = productPartnerMappings;
        this.productCountryMapping = productCountryMapping;
        this.adNetworkNotifications = adNetworkNotifications;
        this.activityFlows = activityFlows;
        this.pricePoints = pricePoints;
        this.retry = retry;
        this.blackouts = blackouts;
    }

    @JsonProperty("product")
    public PublishProduct getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(PublishProduct product) {
        this.product = product;
    }

    @JsonProperty("productPartnerMappings")
    public List<PublishProductPartnerMapping> getProductPartnerMappings() {
        return productPartnerMappings;
    }

    @JsonProperty("productPartnerMappings")
    public void setProductPartnerMappings(List<PublishProductPartnerMapping> productPartnerMappings) {
        this.productPartnerMappings = productPartnerMappings;
    }

    @JsonProperty("productCountryMapping")
    public PublishProductCountryMapping getProductCountryMapping() {
        return productCountryMapping;
    }

    @JsonProperty("productCountryMapping")
    public void setProductCountryMapping(PublishProductCountryMapping productCountryMapping) {
        this.productCountryMapping = productCountryMapping;
    }

    @JsonProperty("adNetworkNotifications")
    public List<PublishAdNetworkNotification> getAdNetworkNotifications() {
        return adNetworkNotifications;
    }

    @JsonProperty("adNetworkNotifications")
    public void setAdNetworkNotifications(List<PublishAdNetworkNotification> adNetworkNotifications) {
        this.adNetworkNotifications = adNetworkNotifications;
    }

    @JsonProperty("activityFlows")
    public List<PublishActivityFlow> getActivityFlows() {
        return activityFlows;
    }

    @JsonProperty("activityFlows")
    public void setActivityFlows(List<PublishActivityFlow> activityFlows) {
        this.activityFlows = activityFlows;
    }

    @JsonProperty("pricePoints")
    public List<PublishPricePoint> getPricePoints() {
        return pricePoints;
    }

    @JsonProperty("pricePoints")
    public void setPricePoints(List<PublishPricePoint> pricePoints) {
        this.pricePoints = pricePoints;
    }

    @JsonProperty("retry")
    public List<PublishRetry> getRetry() {
        return retry;
    }

    @JsonProperty("retry")
    public void setRetry(List<PublishRetry> retry) {
        this.retry = retry;
    }

    @JsonProperty("blackouts")
    public List<PublishBlackout> getBlackouts() {
        return blackouts;
    }

    @JsonProperty("blackouts")
    public void setBlackouts(List<PublishBlackout> blackouts) {
        this.blackouts = blackouts;
    }

}
