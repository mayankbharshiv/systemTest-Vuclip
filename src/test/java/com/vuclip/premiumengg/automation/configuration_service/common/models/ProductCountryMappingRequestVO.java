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
public class ProductCountryMappingRequestVO {
	private String productName;
	private List<String> countries;
}
