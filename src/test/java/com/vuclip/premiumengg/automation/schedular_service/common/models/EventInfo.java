/**
 * 
 */
package com.vuclip.premiumengg.automation.schedular_service.common.models;

import java.util.Date;

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
public class EventInfo {
	private Date logTime;
	private String eventId;
	private String eventType;
}
