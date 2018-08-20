package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vuclip.premiumengg.automation.configuration_service.common.models.ActionType;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ActionVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ActivityFlowRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ActivityFlowVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ActivityType;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkNotificationRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.AdNetworkNotificationVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.BlackoutRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.BlackoutVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ChurnNotificationRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ChurnNotificationVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ConfigRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ConfigResponseVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ConfigVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CriteriaRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CriteriaVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CriterionRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.CriterionVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.DateCoumputationCriterionRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.DateCoumputationCriterionVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.FlowType;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ItemTypeId;
import com.vuclip.premiumengg.automation.configuration_service.common.models.JobType;
import com.vuclip.premiumengg.automation.configuration_service.common.models.Mode;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PricePointRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PricePointVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ProductCountryMappingRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ProductCountryMappingVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ProductPartnerMappingRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ProductPartnerMappingVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ProductRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ProductVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.RetryRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.RetryVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.SmsConfigRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.SmsConfigVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.SmsType;
import com.vuclip.premiumengg.automation.configuration_service.common.models.StateConfigRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.StateConfigVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.Status;
import com.vuclip.premiumengg.automation.configuration_service.common.models.TimeUnit;
import com.vuclip.premiumengg.automation.configuration_service.common.models.TypeOfChurn;
import com.vuclip.premiumengg.automation.configuration_service.common.models.TypeOfCycle;

public class ConfigUtil {
	// private static Logger logger = Log4J.getLogger("ConfigUtil");

	public static ConfigVO getMockConfigVO(ConfigRequestVO request) throws Exception {
		ProductVO product = getMockProductVO(request.getProduct());
		ProductPartnerMappingVO productPartnerMapping = getMockProductPartnerMappingVO(
				request.getProductPartnerMappings().get(0));
		ProductCountryMappingVO productCountryMapping = getMockProductCountryMappingVO(
				request.getProductCountryMapping());
		PricePointVO pricePoint = getMockPricePointVO(request.getPricePoints().get(0));
		AdNetworkNotificationVO adNetworkNotification = getMockAdNetworkNotificationVO(
				request.getAdNetworkNotifications().get(0));
		BlackoutVO blockout = getMockBlockoutVO(request.getBlockouts().get(0));
		ChurnNotificationVO churnNotification = getMockChurnNotificationVO(request.getChurnNotifications().get(0));
		RetryVO retry = getMockRetryVO(request.getRetry().get(0));
		StateConfigVO stateConfig = getMockStateConfigVO(request.getStateConfigs().get(0));
		SmsConfigVO smsConfig = getMockSmsConfigVO(request.getSmsConfigs().get(0));
		ActivityFlowVO activityFlowRequestVO = getMockActivityFlowRequestVO(request.getActivityFlows().get(0));

		return ConfigVO.builder().product(product).productPartnerMappings(Arrays.asList(productPartnerMapping))
				.productCountryMapping(productCountryMapping).pricePoints(Arrays.asList(pricePoint))
				.adNetworkNotifications(Arrays.asList(adNetworkNotification)).blockouts(Arrays.asList(blockout))
				.churnNotifications(Arrays.asList(churnNotification)).retry(Arrays.asList(retry))
				.stateConfigs(Arrays.asList(stateConfig)).smsConfigs(Arrays.asList(smsConfig))
				.activityFlows(Arrays.asList(activityFlowRequestVO)).build();
	}

	public static ConfigRequestVO createMockRequestVO(Object... params) {
		ProductRequestVO product = createMockProductRequestVO(params);
		ProductPartnerMappingRequestVO productPartnerMapping = createMockProductPartnerMappingRequestVO(params);
		ProductCountryMappingRequestVO productCountryMapping = createMockProductCountryMappingRequestVO(params);
		PricePointRequestVO pricePoint = createMockPricePointRequestVO(params);
		AdNetworkNotificationRequestVO adNetworkNotification = createMockAdNetworkRequestVO(params);
		BlackoutRequestVO blockout = createMockBlockoutRequestVO(params);
		ChurnNotificationRequestVO churnNotificatoin = createMockChurnNotificationRequestVO(params);
		StateConfigRequestVO stateConfig = createMockStateConfigRequestVO(params);
		RetryRequestVO retry = createMockRetryRequestVO(params);
		SmsConfigRequestVO smsConfig = createMockSmsConfigRequestVO(params);
		ActivityFlowRequestVO activityFlowRequestVO = createMockActivityFlowRequestVO(params);

		return ConfigRequestVO.builder().product(product).productPartnerMappings(Arrays.asList(productPartnerMapping))
				.productCountryMapping(productCountryMapping).pricePoints(Arrays.asList(pricePoint))
				.adNetworkNotifications(Arrays.asList(adNetworkNotification)).blockouts(Arrays.asList(blockout))
				.churnNotifications(Arrays.asList(churnNotificatoin)).stateConfigs(Arrays.asList(stateConfig))
				.retry(Arrays.asList(retry)).smsConfigs(Arrays.asList(smsConfig))
				.activityFlows(Arrays.asList(activityFlowRequestVO)).build();
	}

	public static ProductRequestVO createMockProductRequestVO(Object... params) {

		return ProductRequestVO.builder().productId(10).productName((String) params[0]).productType((String) params[1])
				.storeType("SUBSCRIPTION_STORE").url((String) params[3]).context((String) params[4])
				.cassId((long) params[5]).encryptionEnable((boolean) params[6])
				.encryptionValidityInMinutes((int) params[7]).callbackUrl((String) params[8])
				.consentCancelUrl((String) params[9]).errorUrl((String) params[10]).description((String) params[11])
				.status((String) params[12]).build();
	}

	public static ProductPartnerMappingRequestVO createMockProductPartnerMappingRequestVO(Object... params) {

		return ProductPartnerMappingRequestVO.builder().productName((String) params[0]).partnerName((String) params[13])
				.chargingDependOnSmsDelivery((boolean) params[14]).optOutSmsEnabled((boolean) params[15])
				.preRenewalSmsEnabled((boolean) params[16]).partnerConsentParserEndpoint((String) params[17])
				.partnerConsentUrlGenerationEndpoint((String) params[18]).dateFormat((String) params[19])
				.timeUnit(TimeUnit.HOUR).build();
	}

	public static ActivityFlowRequestVO createMockActivityFlowRequestVO(Object... params) {
		String billingCode = "1-1-IN-2236-06072018162944124";
		return ActivityFlowRequestVO.builder().activityFlowId(10).productName((String) params[0])
				.partnerName((String) params[13]).name(ActivityType.ACTIVATION).countryName((String) params[20])
				.billingCode(billingCode).actions((Arrays.asList(ActionVO.builder().action(ActionType.CONSENT)
						.actionId(10).flowType(FlowType.VUCLIP_CONSENT).build())))
				.mode(Mode.WAP).build();
	}

	public static ProductCountryMappingRequestVO createMockProductCountryMappingRequestVO(Object... params) {

		return ProductCountryMappingRequestVO.builder().productName((String) params[0])
				.countries(Arrays.asList((String) params[20])).build();
	}

	public static PricePointRequestVO createMockPricePointRequestVO(Object... params) {

		return PricePointRequestVO.builder().productName((String) params[0]).partnerName((String) params[13])
				.countryName((String) params[20]).price((Double) params[22]).validity((int) params[23])
				.noOfCredits((int) params[24]).serviceId((String) params[25]).appId((int) params[26])
				.ujId((int) params[27]).itemId((int) params[28]).itemTypeId((ItemTypeId) params[29])
				.balanceCheckRequired((boolean) params[30]).description((String) params[31])
				.fallbackApplicable((boolean) params[32]).freeTrialApplicable((boolean) params[34])
				.isFreeTrial((boolean) params[36]).exclusionPeriod((int) params[37])
				.autoRenewalApplicable((boolean) params[38]).status((String) params[39])
				.contentAccessPostDeactivation((boolean) params[40])
				.noOfDaysContentAccessAllowInParking((int) params[41])
				.noOfDaysContentAccessAllowInSuspend((int) params[42]).parkingPeriod((int) params[43])
				.suspendPeriod((int) params[44]).activationCoolDownPeriod((int) params[45]).timeUnit(TimeUnit.HOUR)
				.build();
	}

	public static AdNetworkNotificationRequestVO createMockAdNetworkRequestVO(Object... params) {

		return AdNetworkNotificationRequestVO.builder().adNetworkNotificationId(10).productName((String) params[0])
				.partnerName((String) params[13]).countryName((String) params[20]).pricePoint((String) params[21])
				.name((String) params[46]).paidPercentage((int) params[47]).freePercentage((int) params[48])
				.winbackPercentage((int) params[49]).build();
	}

	public static BlackoutRequestVO createMockBlockoutRequestVO(Object... params) {

		return BlackoutRequestVO.builder().blackoutId(10).productName((String) params[0])
				.partnerName((String) params[13]).countryName((String) params[20]).build();
	}

	public static ChurnNotificationRequestVO createMockChurnNotificationRequestVO(Object... params) {

		return ChurnNotificationRequestVO.builder().churnNotificationId(10).productName((String) params[0])
				.partnerName((String) params[13]).countryName((String) params[20])
				.typeOfChurn(TypeOfChurn.valueOf((String) params[51])).period((String) params[52]).build();
	}

	public static RetryRequestVO createMockRetryRequestVO(Object... params) {

		return RetryRequestVO.builder().retryId(10).productName((String) params[0]).partnerName((String) params[13])
				.countryName((String) params[20]).activityType(JobType.valueOf((String) params[53]))
				.maxRetryCount((int) params[54]).retryIntervalInMinutes((int) params[55])
				.attemptWindow((String) params[56]).typeOfCycle(TypeOfCycle.valueOf((String) params[57]))
				.batchSize((int) params[58]).schedulingFrequencyInMinutes((int) params[59]).status(Status.active)
				.build();
	}

	public static StateConfigRequestVO createMockStateConfigRequestVO(Object... params) {

		return StateConfigRequestVO.builder().stateConfigId(10).productName((String) params[0])
				.partnerName((String) params[13]).countryName((String) params[20]).pricePoint((String) params[21])
				.actInitDuration((int) params[60]).activeDuration((int) params[61]).parkingDuration((int) params[62])
				.graceDuration((int) params[63]).suspendDuration((int) params[64]).blacklistDuration((int) params[65])
				.build();
	}

	@SuppressWarnings("unchecked")
	public static SmsConfigRequestVO createMockSmsConfigRequestVO(Object... params) {

		return SmsConfigRequestVO.builder().smsConfigId(10).productName((String) params[0])
				.partnerName((String) params[13]).countryName((String) params[20])
				.type(SmsType.valueOf((String) params[66])).redirectionContext((String) params[67])
				.defaultSmsLanguageId((int) params[68]).batchSize((int) params[69]).smsLength((int) params[70])
				.isAutoPlay((boolean) params[71]).status((String) params[72])
				.criterias((List<CriteriaRequestVO>) params[73]).build();
	}

	public static ProductVO getMockProductVO(ProductRequestVO request) throws Exception {
		return ProductVO.builder().productId(1).productName(request.getProductName())
				.productType(request.getProductType()).storeType(request.getStoreType()).url(request.getUrl())
				.context(request.getContext()).cassId(request.getCassId())
				.encryptionEnable(request.isEncryptionEnable())
				.encryptionValidityInMinutes(request.getEncryptionValidityInMinutes())
				.callbackUrl(request.getCallbackUrl()).consentCancelUrl(request.getConsentCancelUrl())
				.errorUrl(request.getErrorUrl()).description(request.getDescription())
				.status(Status.permissiveValueOf(request.getStatus()).toString()).build();
	}

	public static ProductPartnerMappingVO getMockProductPartnerMappingVO(ProductPartnerMappingRequestVO request) {
		return ProductPartnerMappingVO.builder().productName(request.getProductName())
				.partnerName(request.getPartnerName())
				.chargingDependOnSmsDelivery(request.isChargingDependOnSmsDelivery())
				.optOutSmsEnabled(request.isOptOutSmsEnabled()).preRenewalSmsEnabled(request.isPreRenewalSmsEnabled())
				.partnerConsentParserEndpoint(request.getPartnerConsentParserEndpoint())
				.partnerConsentUrlGenerationEndpoint(request.getPartnerConsentUrlGenerationEndpoint())
				.dateFormat(request.getDateFormat()).build();
	}

	public static ProductCountryMappingVO getMockProductCountryMappingVO(ProductCountryMappingRequestVO request) {
		return ProductCountryMappingVO.builder().productName("VIU").countries(Arrays.asList("India")).build();
	}

	public static PricePointVO getMockPricePointVO(PricePointRequestVO request) {
		String billingCode = "1-1-IN-2236-06072018162944124";
		return PricePointVO.builder().billingCode(billingCode).price(request.getPrice()).validity(request.getValidity())
				.noOfCredits(request.getNoOfCredits()).serviceId(request.getServiceId()).appId(request.getAppId())
				.ujId(request.getUjId()).itemId(request.getItemId()).itemTypeId(request.getItemTypeId())
				.balanceCheckRequired(request.isBalanceCheckRequired()).description(request.getDescription())
				.fallbackApplicable(request.isFallbackApplicable()).fallbackPpBillingCode(billingCode)
				.freeTrialApplicable(request.isFreeTrialApplicable()).freeTrialBillingCode(billingCode)
				.isFreeTrial(request.isFreeTrial()).exclusionPeriod(request.getExclusionPeriod())
				.autoRenewalApplicable(request.isAutoRenewalApplicable()).status(request.getStatus())
				.contentAccessPostDeactivation(request.isContentAccessPostDeactivation())
				.noOfDaysContentAccessAllowInParking(request.getNoOfDaysContentAccessAllowInParking())
				.noOfDaysContentAccessAllowInSuspend(request.getNoOfDaysContentAccessAllowInSuspend())
				.parkingPeriod(request.getParkingPeriod()).suspendPeriod(request.getSuspendPeriod())
				.activationCoolDownPeriod(request.getActivationCoolDownPeriod()).build();
	}

	public static AdNetworkNotificationVO getMockAdNetworkNotificationVO(AdNetworkNotificationRequestVO request) {
		return AdNetworkNotificationVO.builder().adNetworkNotificationId(1).name(request.getName())
				.paidPercentage(request.getPaidPercentage()).freePercentage(request.getFreePercentage())
				.pricePoint(request.getPricePoint()).winbackPercentage(request.getWinbackPercentage()).build();
	}

	public static BlackoutVO getMockBlockoutVO(BlackoutRequestVO request) {
		return BlackoutVO.builder().blackoutId(1).productName(request.getProductName())
				.partnerName(request.getPartnerName()).countryName("India").build();
	}

	public static ChurnNotificationVO getMockChurnNotificationVO(ChurnNotificationRequestVO request) {
		return ChurnNotificationVO.builder().churnNotificationId(1).typeOfChurn(request.getTypeOfChurn())
				.period(request.getPeriod()).build();
	}

	public static RetryVO getMockRetryVO(RetryRequestVO request) {
		return RetryVO.builder().retryId(1).activityType(request.getActivityType())
				.maxRetryCount(request.getMaxRetryCount()).retryIntervalInMinutes(request.getRetryIntervalInMinutes())
				.attemptWindow(request.getAttemptWindow()).typeOfCycle(request.getTypeOfCycle())
				.batchSize(request.getBatchSize())
				.schedulingFrequencyInMinutes(request.getSchedulingFrequencyInMinutes()).build();
	}

	public static StateConfigVO getMockStateConfigVO(StateConfigRequestVO request) {
		return StateConfigVO.builder().stateConfigId(1).actInitDuration(request.getActInitDuration())
				.activeDuration(request.getActiveDuration()).parkingDuration(request.getParkingDuration())
				.graceDuration(request.getGraceDuration()).suspendDuration(request.getSuspendDuration())
				.blacklistDuration(request.getBlacklistDuration()).build();
	}

	public static ActivityFlowVO getMockActivityFlowRequestVO(ActivityFlowRequestVO activityFlowRequestVO) {
		String billingCode = "1-1-IN-2236-06072018162944124";
		return ActivityFlowVO.builder().productName(activityFlowRequestVO.getProductName()).activityFlowId(10)
				.partnerName(activityFlowRequestVO.getPartnerName()).name(ActivityType.ACTIVATION).countryName("India")
				.billingCode(billingCode)
				.actions((Arrays.asList(
						ActionVO.builder().action(ActionType.CONSENT).flowType(FlowType.VUCLIP_CONSENT).build())))
				.mode(Mode.WAP).build();
	}

	public static SmsConfigVO getMockSmsConfigVO(SmsConfigRequestVO request) {
		List<CriteriaVO> criterias = convertToCriteria(request.getCriterias());
		return SmsConfigVO.builder().smsConfigId(1).type(request.getType())
				.redirectionContext(request.getRedirectionContext())
				.defaultSmsLanguageId(request.getDefaultSmsLanguageId()).batchSize(request.getBatchSize())
				.smsLength(request.getSmsLength()).autoPlay(request.isAutoPlay()).status(request.getStatus())
				.criterias(criterias).build();
	}

	public static List<CriteriaVO> convertToCriteria(List<CriteriaRequestVO> criterias) {
		List<CriteriaVO> criteriaResponseVOs = new ArrayList<>();
		criterias.forEach(criteria -> {
			DateCoumputationCriterionVO dateCoumputationCriterion = covertToDateCoumputationCriterion(
					criteria.getDateCoumputationCriterion());
			List<CriterionVO> criterions = convertToCriterion(criteria.getCriterions());
			CriteriaVO responseVO = CriteriaVO.builder().criteriaId(1).smsText(criteria.getSmsText())
					.dateCoumputationCriterion(dateCoumputationCriterion).criterions(criterions).build();
			criteriaResponseVOs.add(responseVO);
		});
		return criteriaResponseVOs;
	}

	public static List<CriterionVO> convertToCriterion(List<CriterionRequestVO> criterions) {
		List<CriterionVO> criterionResponseVOs = new ArrayList<>();
		criterions.forEach(criterion -> {
			CriterionVO responseVO = CriterionVO.builder().criterionId(1).name(criterion.getName())
					.value(criterion.getValue()).operator(criterion.getOperator())
					.groupingOperator(criterion.getGroupingOperator()).build();
			criterionResponseVOs.add(responseVO);
		});
		return criterionResponseVOs;
	}

	public static DateCoumputationCriterionVO covertToDateCoumputationCriterion(
			DateCoumputationCriterionRequestVO dateCoumputationCriterion) {

		return DateCoumputationCriterionVO.builder().dateComputationCriterionId(1)
				.name(dateCoumputationCriterion.getName()).value(dateCoumputationCriterion.getValue())
				.unit(dateCoumputationCriterion.getUnit()).build();
	}

	public static ConfigResponseVO createUpdateConfigData(ConfigResponseVO updateConfig) {

		updateConfig.getConfig().getProduct().setConsentCancelUrl("www.test.com");
		updateConfig.getConfig().getProduct().setOperationType(null);
		updateConfig.getConfig().getProductPartnerMappings().get(0).setOperationType(null);
		updateConfig.getConfig().getProductCountryMapping().setOperationType(null);
		updateConfig.getConfig().getSmsConfigs().get(0).setOperationType(null);
		updateConfig.getConfig().getChurnNotifications().get(0).setOperationType(null);
		updateConfig.getConfig().getAdNetworkNotifications().get(0).setOperationType(null);
		updateConfig.getConfig().getPricePoints().get(0).setOperationType(null);
		updateConfig.getConfig().getRetry().get(0).setOperationType(null);
		updateConfig.getConfig().getStateConfigs().get(0).setOperationType(null);
		updateConfig.getConfig().getBlockouts().get(0).setOperationType(null);
		updateConfig.getConfig().getActivityFlows().get(0).setOperationType(null);
		
		
		
		
		
		return updateConfig;

	}

}
