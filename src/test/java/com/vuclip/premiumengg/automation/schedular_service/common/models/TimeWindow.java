package com.vuclip.premiumengg.automation.schedular_service.common.models;

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
public class TimeWindow {
	private String startime;
	private String endTime;
}
