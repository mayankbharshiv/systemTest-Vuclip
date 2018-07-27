package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "product", "pricePoints" })
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
