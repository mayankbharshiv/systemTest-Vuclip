package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productId",
        "countries"
})
public class PublishProductCountryMapping {

    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("countries")
    private List<PublishCountry> countries = null;

    /**
     * No args constructor for use in serialization
     */
    public PublishProductCountryMapping() {
    }

    /**
     * @param countries
     * @param productId
     */
    public PublishProductCountryMapping(Integer productId, List<PublishCountry> countries) {
        super();
        this.productId = productId;
        this.countries = countries;
    }

    @JsonProperty("productId")
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("countries")
    public List<PublishCountry> getCountries() {
        return countries;
    }

    @JsonProperty("countries")
    public void setCountries(List<PublishCountry> countries) {
        this.countries = countries;
    }

}
