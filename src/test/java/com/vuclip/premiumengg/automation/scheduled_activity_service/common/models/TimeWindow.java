package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"TimeWindow"
})
public class TimeWindow {

@JsonProperty("TimeWindow")
private TimeWindow_ timeWindow;

/**
* No args constructor for use in serialization
* 
*/
public TimeWindow() {
}

/**
* 
* @param timeWindow
*/
public TimeWindow(TimeWindow_ timeWindow) {
super();
this.timeWindow = timeWindow;
}

@JsonProperty("TimeWindow")
public TimeWindow_ getTimeWindow() {
return timeWindow;
}

@JsonProperty("TimeWindow")
public void setTimeWindow(TimeWindow_ timeWindow) {
this.timeWindow = timeWindow;
}

}
//////--com.vuclip.premiumengg.automation.e2e.common.models.TimeWindow_.java//////--

