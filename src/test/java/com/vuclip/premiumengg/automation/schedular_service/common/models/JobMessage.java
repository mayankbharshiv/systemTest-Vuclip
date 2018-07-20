package com.vuclip.premiumengg.automation.schedular_service.common.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JobMessage  implements Serializable{
	private static final long serialVersionUID = 6069336730680520055L;
	private Long jobId;
	private Long partnerId;
	private Long productId;
	private String country;
	private String executingDays;
	private String activityType;
	private List<TimeWindow> timeWindow= new ArrayList<>();
	private EventInfo eventInfo;
}
