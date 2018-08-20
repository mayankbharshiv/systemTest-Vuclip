package com.vuclip.premiumengg.automation.configuration_service.common.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"operationType"})
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SmsConfigVO {
	private int smsConfigId;
	private SmsType type;
	private String redirectionContext;
	private int defaultSmsLanguageId;
	private int batchSize;
	private int smsLength;
	private boolean autoPlay;
	private String status;
	private List<CriteriaVO> criterias;
	private String productName;
	private String partnerName;
	private String countryName;
	private String operationType;
}