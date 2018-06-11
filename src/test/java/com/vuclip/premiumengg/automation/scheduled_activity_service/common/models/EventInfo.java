package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "logTime",
        "eventId"
})
public class EventInfo {

    @JsonProperty("logTime")
    private long logTime;
    @JsonProperty("eventId")
    private String eventId;

    /**
     * No args constructor for use in serialization
     */
    public EventInfo() {
    }

    /**
     * @param eventId
     * @param logTime
     */
    public EventInfo(long logTime, String eventId) {
        super();
        this.logTime = logTime;
        this.eventId = eventId;
    }

    @JsonProperty("logTime")
    public long getLogTime() {
        return logTime;
    }

    @JsonProperty("logTime")
    public void setLogTime(long logTime) {
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

}
