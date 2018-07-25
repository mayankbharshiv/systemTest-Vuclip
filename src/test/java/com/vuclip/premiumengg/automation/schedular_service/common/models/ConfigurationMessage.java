package com.vuclip.premiumengg.automation.schedular_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "product",
        "retry"
})
public class ConfigurationMessage {
    @JsonProperty("product")
    private Product product;
    @JsonProperty("retry")
    private Retry retry;

    /**
     * No args constructor for use in serialization
     */
    public ConfigurationMessage() {
    }

    /**
     * @param product
     * @param retry
     */
    public ConfigurationMessage(Product product, Retry retry) {
        super();
        this.product = product;
        this.retry = retry;
    }

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty("retry")
    public Retry getRetry() {
        return retry;
    }

    @JsonProperty("retry")
    public void setRetry(Retry retry) {
        this.retry = retry;
    }

}
