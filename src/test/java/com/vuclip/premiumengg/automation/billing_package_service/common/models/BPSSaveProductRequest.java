package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"product", "pricePoints"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BPSSaveProductRequest {

    @JsonProperty("product")
    private BPSProduct product;
    @JsonProperty("pricePoints")
    private List<BPSPricePoint> pricePoints = null;

}
