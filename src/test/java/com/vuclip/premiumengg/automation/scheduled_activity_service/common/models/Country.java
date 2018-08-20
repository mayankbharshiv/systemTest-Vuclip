package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"countryCode", "operationType"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {

    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;

}
