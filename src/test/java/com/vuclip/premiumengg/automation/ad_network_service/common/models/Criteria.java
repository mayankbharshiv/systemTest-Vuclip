package com.vuclip.premiumengg.automation.ad_network_service.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"criteriaId", "smsText", "criterions", "dateCoumputationCriterion"})
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {

    @JsonProperty("criteriaId")
    private Integer criteriaId;
    @JsonProperty("smsText")
    private String smsText;
    @JsonProperty("criterions")
    private List<Criterion> criterions = null;
    @JsonProperty("dateCoumputationCriterion")
    private DateCoumputationCriterion dateCoumputationCriterion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}