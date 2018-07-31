package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"startTime",
"endTime"
})
public class TimeWindow_ {

@JsonProperty("startTime")
private String startTime;
@JsonProperty("endTime")
private String endTime;

/**
* No args constructor for use in serialization
* 
*/
public TimeWindow_() {
}

/**
* 
* @param startTime
* @param endTime
*/
public TimeWindow_(String startTime, String endTime) {
super();
this.startTime = startTime;
this.endTime = endTime;
}

@JsonProperty("startTime")
public String getStartTime() {
return startTime;
}

@JsonProperty("startTime")
public void setStartTime(String startTime) {
this.startTime = startTime;
}

@JsonProperty("endTime")
public String getEndTime() {
return endTime;
}

@JsonProperty("endTime")
public void setEndTime(String endTime) {
this.endTime = endTime;
}

}