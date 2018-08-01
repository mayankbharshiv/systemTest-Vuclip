package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"churnNotificationId", "typeOfChurn", "period", "productId", "partnerId", "countryCode",
        "operationType"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChurnNotification {

    @JsonProperty("churnNotificationId")
    private Integer churnNotificationId;
    @JsonProperty("typeOfChurn")
    private String typeOfChurn;
    @JsonProperty("period")
    private String period;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;

}
