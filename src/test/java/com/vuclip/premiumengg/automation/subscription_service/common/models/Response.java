package com.vuclip.premiumengg.automation.subscription_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {

	protected boolean successful;
	protected String message;
	protected String responseCode;
}