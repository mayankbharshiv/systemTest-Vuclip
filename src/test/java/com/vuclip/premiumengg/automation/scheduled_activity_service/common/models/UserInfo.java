package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"billingUserId", "msisdn", "clientUserId", "userSource", "userPreferredLanguage",
        "freeTrialUser"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfo {

    @JsonProperty("billingUserId")
    private String billingUserId;
    @JsonProperty("msisdn")
    private Object msisdn;
    @JsonProperty("clientUserId")
    private String clientUserId;
    @JsonProperty("userSource")
    private String userSource;
    @JsonProperty("userPreferredLanguage")
    private String userPreferredLanguage;
    @JsonProperty("freeTrialUser")
    private Boolean freeTrialUser;
}
