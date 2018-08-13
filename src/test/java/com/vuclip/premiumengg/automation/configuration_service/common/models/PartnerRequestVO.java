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
public class PartnerRequestVO {
	private String partnerName;
	private String description;
	private String status;
	private boolean autoRenewalApplicable;
	private String type;
	private boolean stepUpCharging;
	private String userIdentifier;
	private boolean balanceCheckRequired;
	private String activationManagedBy;
	private String renewalManagedBy;
	private String deactivationManagedBy;
	private boolean hasMoActivations;
	private boolean hasMoDeactivations;
	private String partnerActivationConsentParserUrl;
	private String partnerDeactivationConsentParserUrl;
	private String partnerActivationConsentInitiationUrl;
	private String partnerDeactivationConsentInitiationUrl;
}
