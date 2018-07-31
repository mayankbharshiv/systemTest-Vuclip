package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"dateComputationCriterionId",
"name",
"operator",
"value",
"unit"
})
public class DateCoumputationCriterion {

@JsonProperty("dateComputationCriterionId")
private Integer dateComputationCriterionId;
@JsonProperty("name")
private String name;
@JsonProperty("operator")
private String operator;
@JsonProperty("value")
private String value;
@JsonProperty("unit")
private String unit;

/**
* No args constructor for use in serialization
* 
*/
public DateCoumputationCriterion() {
}

/**
* 
* @param unit
* @param name
* @param value
* @param dateComputationCriterionId
* @param operator
*/
public DateCoumputationCriterion(Integer dateComputationCriterionId, String name, String operator, String value, String unit) {
super();
this.dateComputationCriterionId = dateComputationCriterionId;
this.name = name;
this.operator = operator;
this.value = value;
this.unit = unit;
}

@JsonProperty("dateComputationCriterionId")
public Integer getDateComputationCriterionId() {
return dateComputationCriterionId;
}

@JsonProperty("dateComputationCriterionId")
public void setDateComputationCriterionId(Integer dateComputationCriterionId) {
this.dateComputationCriterionId = dateComputationCriterionId;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("operator")
public String getOperator() {
return operator;
}

@JsonProperty("operator")
public void setOperator(String operator) {
this.operator = operator;
}

@JsonProperty("value")
public String getValue() {
return value;
}

@JsonProperty("value")
public void setValue(String value) {
this.value = value;
}

@JsonProperty("unit")
public String getUnit() {
return unit;
}

@JsonProperty("unit")
public void setUnit(String unit) {
this.unit = unit;
}

}

