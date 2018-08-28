package com.vuclip.premiumengg.automation.core_activity_service.common.utils;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserData;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.BlockedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ChargedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ChargedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ConsentInfo;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.DeactivateUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.DeactivateUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ItemInfo;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.ResultVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.SavePartnerRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.SaveProductRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnBlockedUserRequestVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnBlockedUserResponseVO;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UnblockResponse;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UserChargingInfo;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UserDetails;
import com.vuclip.premiumengg.automation.core_activity_service.common.models.UserStatus;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;

public class CASUtils {

	private static Logger logger = Log4J.getLogger("CASUtils");
	public static String uBSMockURL = null;
	public static int partnerId;
	public static int productId;

	/**
	 * @param JsonFileName
	 * @param type
	 * @return
	 */
	public static <T> T loadJson(String JsonFileName, Class<T> type) {
		return ObjectMapperUtils
				.readValue("src/test/resources/configurations/core-activity-service/request/" + JsonFileName, type);
	}

	public static ChargedUserRequestVO createMockRequestVO(Object... params) {
		logger.info("Create Mock Request for Charged User");
		ConsentInfo consentInfo = createConsentInfoMockRequest(params);
		ItemInfo itemInfo = createItemInfoMockRequest(params);
		UserChargingInfo userChargingInfo = createUSerChargingMockRequest(params);

		return ChargedUserRequestVO.builder().itemInfo(itemInfo).consentInfo(consentInfo)
				.userChargingInfo(userChargingInfo).mode((String) params[11]).billingCode((String) params[12])
				.dirtNetworkParams((String) params[13]).source((String) params[14]).build();

	}

	public static UnBlockedUserRequestVO createUnblockMockRequestVO(Object... params) {
		logger.info("Create Mock Request for Unblock User");

		return UnBlockedUserRequestVO.builder().userId((String) params[0]).msisdn((String) params[1])
				.productId((Integer) params[2]).partnerId((Integer) params[3]).blockType((String) params[4])
				.country((String) params[5]).build();
	}

	public static DeactivateUserRequestVO createDeactivateMockRequestVO(Object... params) {
		logger.info("Create Mock Request for Deactivate User");

		return DeactivateUserRequestVO.builder().userId((String) params[0]).msisdn((String) params[1])
				.productId((Integer) params[2]).itemId((String) params[3]).itemTypeId((String) params[4])
				.subscriptionId((Long) params[5]).mode((String) params[6]).build();
	}

	public static UnBlockedUserResponseVO getUnBlockedUserMockResponse(Object... params) {
		logger.info("Get Mock Request for Unblock User");

		return UnBlockedUserResponseVO.builder().response((UnblockResponse) params[0]).build();
	}

	public static BlockedUserRequestVO createBlockedUserMockRequestVO(Object... params) {
		logger.info("Create Mock Request for Blocked User");
		UserDetails userDetails = createUserDetailsMockRequest(params);

		return BlockedUserRequestVO.builder().userDetails(userDetails).productId((Integer) params[2])
				.partnerId((Integer) params[3]).country((String) params[4]).build();

	}

	public static UserDetails createUserDetailsMockRequest(Object... params) {

		return UserDetails.builder().userId((String) params[0]).msisdn((String) params[1]).build();

	}

	private static ConsentInfo createConsentInfoMockRequest(Object... params) {
		return ConsentInfo.builder().callBackUrl((String) params[2]).consentPageImageUrl((String) params[3])
				.consentPageNoUrl((String) params[4]).build();
	}

	private static ItemInfo createItemInfoMockRequest(Object... params) {
		return ItemInfo.builder().itemId((String) params[0]).itemTypeId((String) params[1]).build();

	}

	private static UserChargingInfo createUSerChargingMockRequest(Object... params) {
		return UserChargingInfo.builder().userId((String) params[5]).msisdn((String) params[6])
				.circleCode((String) params[7]).languageId((String) params[8]).microSiteId((String) params[9])
				.clientRequestInfo((String) params[10]).build();

	}

	public static ChargedUserResponseVO getChargedUSerMockResponse(Object... params) {
		return ChargedUserResponseVO.builder().activationConsentUrl((String) params[0]).resultVO((ResultVO) params[1])
				.userStatus((Object) params[2]).build();
	}

	public static BlockedUserResponseVO getBlokcedMockResponse(Object... params) {
		return BlockedUserResponseVO.builder().userStatus((UserStatus) params[0])
				.blockedUserData((BlockedUserData) params[1]).resultVO((ResultVO) params[2]).build();
	}

	public static DeactivateUserResponseVO getDeactivateMockResponse(Object... params) {
		return DeactivateUserResponseVO.builder().userStatus((UserStatus) params[0]).resultVO((ResultVO) params[1])
				.build();
	}

	public static SavePartnerRequestVO savePartnerRequest(int partnerId, String ubsMockUrl) {
		SavePartnerRequestVO savePartner = CASUtils.loadJson("savePartner.json", SavePartnerRequestVO.class);
		savePartner.setPartnerId(partnerId);
		savePartner.setPartnerActivationConsentParserUrl(
				savePartner.getPartnerActivationConsentParserUrl().replaceAll("partnerurl", ubsMockUrl));
		savePartner.setPartnerDeactivationConsentInitiationUrl(
				savePartner.getPartnerDeactivationConsentInitiationUrl().replaceAll("partnerurl", ubsMockUrl));
		savePartner.setPartnerActivationConsentInitiationUrl(
				savePartner.getPartnerActivationConsentInitiationUrl().replaceAll("partnerurl", ubsMockUrl));
		savePartner.setPartnerDeactivationConsentParserUrl(
				savePartner.getPartnerDeactivationConsentParserUrl().replaceAll("partnerurl", ubsMockUrl));
		return savePartner;
	}

	public static SaveProductRequestVO saveProductRequest(int productId) {
		SaveProductRequestVO saveProduct = CASUtils.loadJson("saveProduct.json", SaveProductRequestVO.class);
		String jsonString = ObjectMapperUtils.writeValueAsString(saveProduct);
		jsonString = jsonString.replaceAll("1111", String.valueOf(productId));
		saveProduct = ObjectMapperUtils.readValueFromString(jsonString, SaveProductRequestVO.class);
		return saveProduct;
	}
}
