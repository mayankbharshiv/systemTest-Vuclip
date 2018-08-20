package com.vuclip.subscription.response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vuclip.premiumengg.automation.subscription_service.common.models.StatusSummary;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionStatusData {

	private String userSubAuthKey;
	private Long subscriptionId;
	private Double chargedPrice;
	private Integer productId;
	private Integer partnerId;
	private String msisdn;
	private String userId;
	private Integer itemId;
	private Integer itemTypeId;
	private String subscriptionBillingCode;
	private String chargedBillingCode;
	private SubscriptionStatus subscriptionStatus;
	private String userSource;
	private String customerTransactionId;
	private String mode;
	private Long startDate;
	private Long endDate;
	private Long activationDate;
	private Long deactivationDate;
	private Long nextBillingDate;
	private String country;
	private Date lastChargeDate;
	private Integer subscriptionValidityDays;
	private Boolean paid;
	private Boolean renewalAllowed;
	private Map<String, String> payload = new HashMap<String, String>();
	private StatusSummary summary;
}
