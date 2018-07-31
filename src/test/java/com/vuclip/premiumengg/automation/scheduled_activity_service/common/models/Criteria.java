package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.util.List;
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

/**
* No args constructor for use in serialization
* 
*/
public Criteria() {
}

/**
* 
* @param dateCoumputationCriterion
* @param criterions
* @param criteriaId
* @param smsText
*/
public Criteria(Integer criteriaId, String smsText, List<Criterion> criterions, DateCoumputationCriterion dateCoumputationCriterion) {
super();
this.criteriaId = criteriaId;
this.smsText = smsText;
this.criterions = criterions;
this.dateCoumputationCriterion = dateCoumputationCriterion;
}

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

}

