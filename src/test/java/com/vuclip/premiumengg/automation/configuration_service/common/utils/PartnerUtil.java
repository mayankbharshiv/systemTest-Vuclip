package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerResponseVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerVO;

public class PartnerUtil {
	public static int productId;
	private static Logger logger = Log4J.getLogger("PartnerUtil");

	public static PartnerRequestVO createMockRequestVO(Object... params) {
		logger.info("Create mock reqeust for partner");
		return PartnerRequestVO.builder().partnerName((String) params[0]).description((String) params[1])
				.status((String) params[2]).autoRenewalApplicable((boolean) params[3]).type((String) params[4])
				.stepUpCharging((boolean) params[5]).userIdentifier((String) params[6])
				.balanceCheckRequired((boolean) params[7]).activationManagedBy((String) params[8])
				.renewalManagedBy((String) params[9]).deactivationManagedBy((String) params[10])
				.hasMoActivations((boolean) params[11]).hasMoDeactivations((boolean) params[12])
				.partnerActivationConsentParserUrl((String) params[13])
				.partnerDeactivationConsentParserUrl((String) params[14])
				.partnerActivationConsentInitiationUrl((String) params[15])
				.partnerDeactivationConsentInitiationUrl((String) params[16]).build();
	}

	public static PartnerResponseVO getMockPartnerVO(PartnerRequestVO request, String operationType,
			boolean isSuccessful, int responseCode, String message) {
		logger.info("Get mock reqeust for partner");
		return PartnerResponseVO.builder()
				.partner(new PartnerVO(0, request.getPartnerName(), request.getDescription(), request.getStatus(),
						request.isAutoRenewalApplicable(), request.getType(), request.isStepUpCharging(),
						request.getUserIdentifier(), request.isBalanceCheckRequired(), request.getActivationManagedBy(),
						request.getRenewalManagedBy(), request.getDeactivationManagedBy(), request.isHasMoActivations(),
						request.isHasMoDeactivations(), operationType,request.getPartnerActivationConsentParserUrl(),
						request.getPartnerDeactivationConsentParserUrl(),
						request.getPartnerActivationConsentInitiationUrl(),
						request.getPartnerDeactivationConsentInitiationUrl() ))
				.successful(isSuccessful).responseCode(responseCode).message(message).build();
	}

	public static int boolToInt(boolean b) {
		return Boolean.compare(b, false);
	}
}
