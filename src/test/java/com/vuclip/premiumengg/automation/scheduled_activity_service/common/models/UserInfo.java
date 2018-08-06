package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author shreyash
 *
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserInfo {

	private String msisdn;

	private String userId;

	private boolean isFreeTrialUser;

	private String userSource;
	
	private String userPreferredLanguage;
}
