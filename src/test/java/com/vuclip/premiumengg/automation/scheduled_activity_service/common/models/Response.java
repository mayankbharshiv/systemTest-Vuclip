package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Response {

	protected boolean successful;
	protected String message;
	protected String responseCode;
}