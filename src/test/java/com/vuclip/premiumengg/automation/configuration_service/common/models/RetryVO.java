package com.vuclip.premiumengg.automation.configuration_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"operationType"})
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RetryVO {
	private int retryId;
	private JobType activityType;
	private int maxRetryCount;
	private int retryIntervalInMinutes;
	private String attemptWindow;
	private TypeOfCycle typeOfCycle;
	private int batchSize;
	private int schedulingFrequencyInMinutes;
	private String schedulingDays;
	private String executingTimeWindow;
	private String executingDays;
	public Status status;
	private String productName;
	private String partnerName;
	private String countryName;
	private String operationType;
	private boolean actionDefaultEiligible;
	private boolean retryable;
}
