package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserSubscriptionRequest {

	private EventInfo eventInfo;

	private UserInfo userInfo;

	private ActivityInfo activityInfo;

	private SubscriptionInfo subscriptionInfo;

	private ActivityEvent activityEvent;

	private Response response;

}