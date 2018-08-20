package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"criteriaId", "smsText", "criterions", "dateCoumputationCriterion"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Criteria {

    @JsonProperty("criteriaId")
    private Integer criteriaId;
    @JsonProperty("smsText")
    private String smsText;
    @JsonProperty("criterions")
    private List<Criterion> criterions = null;
    @JsonProperty("dateCoumputationCriterion")
    private DateCoumputationCriterion dateCoumputationCriterion;

}
