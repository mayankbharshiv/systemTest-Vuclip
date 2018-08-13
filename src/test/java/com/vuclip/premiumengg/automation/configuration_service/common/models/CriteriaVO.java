package com.vuclip.premiumengg.automation.configuration_service.common.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CriteriaVO {
	private int criteriaId;
	private String smsText;
	private List<CriterionVO> criterions;
	private DateCoumputationCriterionVO dateCoumputationCriterion;
}
