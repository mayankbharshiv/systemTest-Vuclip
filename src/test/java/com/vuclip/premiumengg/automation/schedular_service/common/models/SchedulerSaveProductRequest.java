package com.vuclip.premiumengg.automation.schedular_service.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"retry"})
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerSaveProductRequest {

    @JsonProperty("retry")
    private List<Retry> retry = null;

}