package com.vuclip.premiumengg.automation.schedular_service.common.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TimeWindow {
    private String startime;
    private String endTime;
}
