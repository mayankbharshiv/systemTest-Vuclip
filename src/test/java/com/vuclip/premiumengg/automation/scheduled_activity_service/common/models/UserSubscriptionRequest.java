package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import lombok.*;

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