package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "countryCode"
})
public class PublishCountry {

    @JsonProperty("countryCode")
    private String countryCode;

    /**
     * No args constructor for use in serialization
     */
    public PublishCountry() {
    }

    /**
     * @param countryCode
     */
    public PublishCountry(String countryCode) {
        super();
        this.countryCode = countryCode;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
