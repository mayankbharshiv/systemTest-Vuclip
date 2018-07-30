package com.vuclip.premiumengg.automation.billing_package_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timezone", "currency", "countryName", "countryCode", "operationType"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BPSSaveCountryRequest {

    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;

}