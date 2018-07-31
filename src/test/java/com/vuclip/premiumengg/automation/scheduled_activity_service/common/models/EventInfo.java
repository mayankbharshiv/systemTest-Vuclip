//////--com.vuclip.premiumengg.automation.e2e.common.models.EventInfo.java//////--

package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "logTime", "eventId", "eventType" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventInfo {

	@JsonProperty("logTime")
	private String logTime;
	@JsonProperty("eventId")
	private String eventId;
	@JsonProperty("eventType")
	private String eventType;

}
