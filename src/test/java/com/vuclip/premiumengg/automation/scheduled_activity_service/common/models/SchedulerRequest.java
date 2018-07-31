package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "jobId", "partnerId", "productId", "country", "executingDays", "activityType", "timeWindow",
		"eventInfo" })
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerRequest {

	@JsonProperty("jobId")
	private Integer jobId;
	@JsonProperty("partnerId")
	private Integer partnerId;
	@JsonProperty("productId")
	private Integer productId;
	@JsonProperty("country")
	private String country;
	@JsonProperty("executingDays")
	private String executingDays;
	@JsonProperty("activityType")
	private String activityType;
	@JsonProperty("timeWindow")
	private List<TimeWindow> timeWindow = null;
	@JsonProperty("eventInfo")
	private EventInfo eventInfo;

}
////// --com.vuclip.premiumengg.automation.e2e.common.models.TimeWindow.java//////--
