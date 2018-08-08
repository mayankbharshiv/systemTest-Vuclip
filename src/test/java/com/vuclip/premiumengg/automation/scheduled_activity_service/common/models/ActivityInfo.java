package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;


import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ActivityInfo {

    private String activityType;

    private String actionType;

    private String previousSubscriptionState;

    private String currentSubscriptionState;

    private String activityResult;


}
