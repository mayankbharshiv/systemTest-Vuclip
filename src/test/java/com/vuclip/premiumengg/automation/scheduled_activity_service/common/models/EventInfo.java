//////--com.vuclip.premiumengg.automation.e2e.common.models.EventInfo.java//////--

package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"logTime",
"eventId",
"eventType"
})
public class EventInfo {

@JsonProperty("logTime")
private String logTime;
@JsonProperty("eventId")
private String eventId;
@JsonProperty("eventType")
private String eventType;

/**
* No args constructor for use in serialization
* 
*/
public EventInfo() {
}

/**
* 
* @param eventId
* @param logTime
* @param eventType
*/
public EventInfo(String logTime, String eventId, String eventType) {
super();
this.logTime = logTime;
this.eventId = eventId;
this.eventType = eventType;
}

@JsonProperty("logTime")
public String getLogTime() {
return logTime;
}

@JsonProperty("logTime")
public void setLogTime(String logTime) {
this.logTime = logTime;
}

@JsonProperty("eventId")
public String getEventId() {
return eventId;
}

@JsonProperty("eventId")
public void setEventId(String eventId) {
this.eventId = eventId;
}

@JsonProperty("eventType")
public String getEventType() {
return eventType;
}

@JsonProperty("eventType")
public void setEventType(String eventType) {
this.eventType = eventType;
}

}
//////--com.vuclip.premiumengg.automation.e2e.common.models.SASSchedularRequest.java//////--

