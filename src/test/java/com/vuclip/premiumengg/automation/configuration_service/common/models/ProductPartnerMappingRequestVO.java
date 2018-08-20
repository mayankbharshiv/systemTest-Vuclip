package com.vuclip.premiumengg.automation.configuration_service.common.models;

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
public class ProductPartnerMappingRequestVO {
	private String productName;
	private String partnerName;
	private boolean chargingDependOnSmsDelivery;
	private boolean optOutSmsEnabled;
	private boolean preRenewalSmsEnabled;
	private String partnerConsentParserEndpoint;
	private String partnerConsentUrlGenerationEndpoint;
	private String dateFormat;
	private boolean blacklistApplicable;
	private boolean validityFromPartner;
	private int blacklistValidity;
	private boolean cooldownApplicable;
	private int cooldownValidity;
	private TimeUnit timeUnit;
	private boolean stepUpChargingApplicable;
	private int allowedFreeTrialCount;
}
