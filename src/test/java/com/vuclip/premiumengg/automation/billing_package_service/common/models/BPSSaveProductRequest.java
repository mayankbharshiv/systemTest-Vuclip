package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "product",
        "pricePoints"
})
public class BPSSaveProductRequest {

    @JsonProperty("product")
    private Product product;
    @JsonProperty("pricePoints")
    private List<PricePoint> pricePoints = null;

    /**
     * No args constructor for use in serialization
     */
    public BPSSaveProductRequest() {
    }

    /**
     * @param pricePoints
     * @param product
     */
    public BPSSaveProductRequest(Product product, List<PricePoint> pricePoints) {
        super();
        this.product = product;
        this.pricePoints = pricePoints;
    }

    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty("pricePoints")
    public List<PricePoint> getPricePoints() {
        return pricePoints;
    }

    @JsonProperty("pricePoints")
    public void setPricePoints(List<PricePoint> pricePoints) {
        this.pricePoints = pricePoints;
    }

}
