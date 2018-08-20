package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"smsConfigId", "type", "redirectionContext", "defaultSmsLanguageId", "batchSize", "smsLength",
        "autoPlay", "status", "criterias", "productId", "partnerId", "countryCode", "operationType"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmsConfig {

    @JsonProperty("smsConfigId")
    private Integer smsConfigId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("redirectionContext")
    private String redirectionContext;
    @JsonProperty("defaultSmsLanguageId")
    private Integer defaultSmsLanguageId;
    @JsonProperty("batchSize")
    private Integer batchSize;
    @JsonProperty("smsLength")
    private Integer smsLength;
    @JsonProperty("autoPlay")
    private Boolean autoPlay;
    @JsonProperty("status")
    private String status;
    @JsonProperty("criterias")
    private List<Criteria> criterias = null;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("partnerId")
    private Integer partnerId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("operationType")
    private String operationType;

}
