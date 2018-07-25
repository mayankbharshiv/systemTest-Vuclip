package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"countryId", "countryName", "countryCode", "timezone", "currency", "operationType"})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SaveCountry {

    @JsonProperty("countryId")
    private Integer countryId;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("operationType")
    private String operationType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
