package com.vuclip.premiumengg.automation.scheduled_activity_service.common.models;

import lombok.*;

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