package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productId", "countries" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCountryMapping {

	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("countries")
	private List<Country> countries = null;

}
