package com.vuclip.premiumengg.automation.configuration_service.common.models;
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
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdNetworkNotificationVO {
	private int adNetworkNotificationId;
	private String name;
	private int paidPercentage;
	private int freePercentage;
	private int winbackPercentage;
	private String productName;
	private String partnerName;
	private String countryName;
	private String pricePoint;
	private String operationType;
}
