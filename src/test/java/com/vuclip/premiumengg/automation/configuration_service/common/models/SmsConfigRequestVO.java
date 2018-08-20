package com.vuclip.premiumengg.automation.configuration_service.common.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsConfigRequestVO {
	private int smsConfigId;
	private String productName;
	private String partnerName;
	private String countryName;
	private SmsType type;
	private String redirectionContext;
	private int defaultSmsLanguageId;
	private int batchSize;
	private int smsLength;
	private boolean isAutoPlay;
	private String status;
	private List<CriteriaRequestVO> criterias;
}