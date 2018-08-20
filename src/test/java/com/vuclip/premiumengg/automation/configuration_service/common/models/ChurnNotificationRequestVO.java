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
public class ChurnNotificationRequestVO {
	private int churnNotificationId;
	private String productName;
	private String partnerName;
	private String countryName;
	private TypeOfChurn typeOfChurn;
	private String period;
}
