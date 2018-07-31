package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"criterionId",
"name",
"operator",
"value",
"groupingOperator"
})
public class Criterion {

@JsonProperty("criterionId")
private Integer criterionId;
@JsonProperty("name")
private String name;
@JsonProperty("operator")
private String operator;
@JsonProperty("value")
private String value;
@JsonProperty("groupingOperator")
private String groupingOperator;

/**
* No args constructor for use in serialization
* 
*/
public Criterion() {
}

/**
* 
* @param criterionId
* @param name
* @param value
* @param operator
* @param groupingOperator
*/
public Criterion(Integer criterionId, String name, String operator, String value, String groupingOperator) {
super();
this.criterionId = criterionId;
this.name = name;
this.operator = operator;
this.value = value;
this.groupingOperator = groupingOperator;
}

@JsonProperty("criterionId")
public Integer getCriterionId() {
return criterionId;
}

@JsonProperty("criterionId")
public void setCriterionId(Integer criterionId) {
this.criterionId = criterionId;
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

@JsonProperty("groupingOperator")
public String getGroupingOperator() {
return groupingOperator;
}

@JsonProperty("groupingOperator")
public void setGroupingOperator(String groupingOperator) {
this.groupingOperator = groupingOperator;
}

}

