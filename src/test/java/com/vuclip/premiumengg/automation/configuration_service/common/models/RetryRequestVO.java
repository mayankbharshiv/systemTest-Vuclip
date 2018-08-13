package com.vuclip.premiumengg.automation.configuration_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetryRequestVO {
	private int retryId;
	private String productName;
	private String partnerName;
	private String countryName;
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
	private boolean actionDefaultEiligible;
	private boolean retryable;
}