/**
 *
 */
package com.vuclip.premiumengg.automation.schedular_service.common.models;

import lombok.*;

import java.util.Date;

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
