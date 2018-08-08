package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;


import lombok.*;

/**
 * @author shreyash
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EventInfo {
    private long logTime;

    private String eventId;

    private String eventType;
}
