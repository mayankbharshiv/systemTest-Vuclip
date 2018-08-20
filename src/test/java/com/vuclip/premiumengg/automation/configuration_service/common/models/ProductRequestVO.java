package com.vuclip.premiumengg.automation.configuration_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestVO {
	private Integer productId;
	private String productName;
	private String productType;
	private String storeType;
	private String url;
	private String context;
	private long cassId;
	private boolean encryptionEnable;
	private int encryptionValidityInMinutes;
	private String callbackUrl;
	private String consentCancelUrl;
	private String errorUrl;
	private String description;
	private String status;

}