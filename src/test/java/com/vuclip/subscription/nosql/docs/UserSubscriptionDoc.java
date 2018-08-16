package com.vuclip.subscription.nosql.docs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionStatus;
import com.vuclip.subscription.response.SubscriptionStatusData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSubscriptionDoc implements Serializable {

	private static final long serialVersionUID = -6416514525035588895L;
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
	private String circleCode;
	private String userPreferredLanguage;
	private Long startDate;
	private Long endDate;
	private Long activationDate;
	private Long deactivationDate;
	private Long nextBillingDate;
	private String country;
	private Date createdOn;
	private Date lastUpdatedOn;
	private Integer subscriptionValidityDays;
	private Boolean paid;
	private Boolean renewalAllowed;

	public SubscriptionStatusData getSubscriptionStatusData() {
		return SubscriptionStatusData.builder().chargedPrice(this.getChargedPrice())
				.chargedBillingCode(this.getChargedBillingCode()).country(this.getCountry())
				.customerTransactionId(this.getCustomerTransactionId()).msisdn(this.getMsisdn())
				.nextBillingDate(this.getNextBillingDate()).partnerId(this.getPartnerId())
				.productId(this.getProductId()).renewalAllowed(this.getRenewalAllowed()).endDate(this.getEndDate())
				.subscriptionBillingCode(this.getSubscriptionBillingCode())
				.subscriptionStatus(this.getSubscriptionStatus())
				.subscriptionValidityDays(this.getSubscriptionValidityDays()).startDate(this.getStartDate())
				.subscriptionId(this.getSubscriptionId()).userId(this.getUserId())
				.userSubAuthKey(this.getUserSubAuthKey()).build();
	}
}
