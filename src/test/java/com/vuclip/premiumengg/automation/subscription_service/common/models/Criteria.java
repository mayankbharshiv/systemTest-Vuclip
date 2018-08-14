
package com.vuclip.premiumengg.automation.subscription_service.common.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "criteriaId",
    "smsText",
    "criterions",
    "dateCoumputationCriterion"
})
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

    @JsonProperty("criteriaId")
    public Integer getCriteriaId() {
        return criteriaId;
    }

    @JsonProperty("criteriaId")
    public void setCriteriaId(Integer criteriaId) {
        this.criteriaId = criteriaId;
    }

    @JsonProperty("smsText")
    public String getSmsText() {
        return smsText;
    }

    @JsonProperty("smsText")
    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    @JsonProperty("criterions")
    public List<Criterion> getCriterions() {
        return criterions;
    }

    @JsonProperty("criterions")
    public void setCriterions(List<Criterion> criterions) {
        this.criterions = criterions;
    }

    @JsonProperty("dateCoumputationCriterion")
    public DateCoumputationCriterion getDateCoumputationCriterion() {
        return dateCoumputationCriterion;
    }

    @JsonProperty("dateCoumputationCriterion")
    public void setDateCoumputationCriterion(DateCoumputationCriterion dateCoumputationCriterion) {
        this.dateCoumputationCriterion = dateCoumputationCriterion;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
