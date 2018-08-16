package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSync;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSyncQueue;
import com.vuclip.premiumengg.automation.subscription_service.common.models.RabbitMessage;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SaveProductRequest;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionBlock;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionBlockStatus;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionStatus;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionUnBlock;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import com.vuclip.subscription.nosql.docs.UserKeySubscriptionIdDoc;
import com.vuclip.subscription.nosql.docs.UserSubscriptionDoc;
import com.vuclip.subscription.rdbms.entities.BlockedUser;

public class SUtils {

	public static int productId = 8181;
	public static SaveProductRequest productConfig = null;
	private static Logger logger = Log4J.getLogger("SSUtils");

	/**
	 * 
	 * @param JsonFileName
	 * @param type
	 * @return
	 */
	public static <T> T loadJson(String JsonFileName, Class<T> type) {
		return ObjectMapperUtils
				.readValue("src/test/resources/configurations/subscription-service/request/" + JsonFileName, type);
	}

	public static void generateUserSubscriptionTableData(Long subscription_id, String created_on, Long activation_date,
			int blacklisted, String charged_billing_code, int cooldowned, Long deactivation_date, Long end_date,
			String msisdn, Long next_billing_date, int paid, int partner_id, int product_id, Long start_date,
			String sub_type, int subscribable, String subscription_billing_code, String subscription_status,
			int validity_days, String user_id, String user_sub_auth_key, int renewalAllowed) {
		Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

		logger.info("Generate User Subscription Table");

		insertRecordinDatabase.put("deactivation_date", deactivation_date);
		insertRecordinDatabase.put("subscription_id", subscription_id);
		insertRecordinDatabase.put("created_on", SUtils.dbReadableFormat(created_on));
		insertRecordinDatabase.put("activation_date", activation_date);
		insertRecordinDatabase.put("renewal_allowed", renewalAllowed);
		insertRecordinDatabase.put("charged_billing_code", SUtils.dbReadableFormat(charged_billing_code));
		insertRecordinDatabase.put("charged_price", 0);
		insertRecordinDatabase.put("circle_code", SUtils.dbReadableFormat("circlecode"));
		insertRecordinDatabase.put("country", SUtils.dbReadableFormat("MY"));
		insertRecordinDatabase.put("customer_transaction_id", SUtils.dbReadableFormat("TXN123456"));
		insertRecordinDatabase.put("end_date", end_date);
		insertRecordinDatabase.put("item_id", 1);
		insertRecordinDatabase.put("item_type_id", 1);
		insertRecordinDatabase.put("mode", SUtils.dbReadableFormat("WAP"));
		insertRecordinDatabase.put("msisdn", SUtils.dbReadableFormat(msisdn));
		insertRecordinDatabase.put("next_billing_date", next_billing_date);
		insertRecordinDatabase.put("paid", paid);
		insertRecordinDatabase.put("partner_id", partner_id);
		insertRecordinDatabase.put("product_id", product_id);
		insertRecordinDatabase.put("start_date", start_date);
		insertRecordinDatabase.put("subscription_billing_code", SUtils.dbReadableFormat(subscription_billing_code));
		insertRecordinDatabase.put("subscription_status", SUtils.dbReadableFormat(subscription_status));
		insertRecordinDatabase.put("validity_days", validity_days);
		insertRecordinDatabase.put("user_id", SUtils.dbReadableFormat(user_id));
		insertRecordinDatabase.put("user_preffered_language", SUtils.dbReadableFormat("en"));
		insertRecordinDatabase.put("user_source", SUtils.dbReadableFormat("D_KIM_87348"));
		insertIntoDatabase("user_subscription", insertRecordinDatabase);

	}

	public static void generateBlockedUserTableData(Long id, int blockType, String created_date, Long end_date,
			Integer product_id, Integer partner_id, Long start_date, String user_id, String country)

	{
		Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

		logger.info("Generate Blocked User Table");
		insertRecordinDatabase.put("id", id);
		insertRecordinDatabase.put("block_type", blockType);
		insertRecordinDatabase.put("created_on", SUtils.dbReadableFormat(created_date));
		insertRecordinDatabase.put("end_date", end_date);
		insertRecordinDatabase.put("partner_id", partner_id);
		insertRecordinDatabase.put("product_id", product_id);
		insertRecordinDatabase.put("start_date",start_date);
		insertRecordinDatabase.put("user_id", SUtils.dbReadableFormat(user_id));
		insertRecordinDatabase.put("country", SUtils.dbReadableFormat(country));
		insertIntoDatabase("blocked_user", insertRecordinDatabase);
	}

	public static void generateFreeTrialTableData(Integer id, String created_on, String last_free_trial_date,
			Integer availed_free_trial_count, String last_free_trial_billing_code, Integer product_id,
			Integer partner_id, String user_id)

	{
		Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

		logger.info("Generate Free Trial History Table");

		insertRecordinDatabase.put("id", id);
		insertRecordinDatabase.put("created_on", SUtils.dbReadableFormat(created_on));
		insertRecordinDatabase.put("last_free_trial_date", SUtils.dbReadableFormat(last_free_trial_date));
		insertRecordinDatabase.put("partner_id", partner_id);
		insertRecordinDatabase.put("product_id", product_id);
		insertRecordinDatabase.put("availed_free_trial_count", availed_free_trial_count);
		insertRecordinDatabase.put("last_free_trial_billing_code",
				SUtils.dbReadableFormat(last_free_trial_billing_code));
		insertRecordinDatabase.put("user_id", user_id);
		insertIntoDatabase("free_trial_history", insertRecordinDatabase);
	}

	public static void insertIntoProductPartner(int id, int allowed_free_trial_count, int blacklist_applicable,
			int blacklist_validity, int cooldown_applicable, int cooldown_validity, int partner_id, int product_id,
			int step_up_charging_applicable, int time_unit, int validity_from_partner)

	{
		Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

		logger.info("Generate Product Partner Table");

		insertRecordinDatabase.put("id", id);
		insertRecordinDatabase.put("allowed_free_trial_count", allowed_free_trial_count);
		insertRecordinDatabase.put("blacklist_applicable", blacklist_applicable);
		insertRecordinDatabase.put("blacklist_validity", blacklist_validity);
		insertRecordinDatabase.put("cooldown_applicable", cooldown_applicable);
		insertRecordinDatabase.put("cooldown_validity", cooldown_validity);
		insertRecordinDatabase.put("partner_id", partner_id);
		insertRecordinDatabase.put("product_id", product_id);
		insertRecordinDatabase.put("step_up_charging_applicable", step_up_charging_applicable);
		insertRecordinDatabase.put("time_unit", time_unit);
		insertRecordinDatabase.put("validity_from_partner", validity_from_partner);
		insertIntoDatabase("product_partner_mapping", insertRecordinDatabase);
	}

	/**
	 * 
	 * @param tabeName
	 * @param dataSet
	 */
	private static void insertIntoDatabase(String tabeName, Map<String, Object> dataSet) {
		logger.info("Insert Into Database");
		SDBHelper.addRecordInTable(tabeName, dataSet);

	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String dbReadableFormat(String key) {
		return ("'" + key + "'");
	}

	public static void generateRedisValueUserId(Long subscription_id, String created_on, Long activation_date,
			int blacklisted, String charged_billing_code, int cooldowned, Long deactivation_date, Long end_date,
			String msisdn, Long next_billing_date, int paid, int partner_id, int product_id, Long start_date,
			String sub_type, int subscribable, String subscription_billing_code, String subscription_status,
			int validity_days, String user_id, String user_sub_auth_key, int renewalAllowed) throws ParseException {

		logger.info("Generate Redis Value");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		logger.info("Subscription status in Redis=>" + subscription_status);
		UserSubscriptionDoc redisEntryValueDetail = new UserSubscriptionDoc();
		try {

			redisEntryValueDetail = loadJson("redis.json", UserSubscriptionDoc.class);
			if (deactivation_date != null) {
				redisEntryValueDetail.setDeactivationDate(deactivation_date);
			} else {
				redisEntryValueDetail.setDeactivationDate(null);
			}
			redisEntryValueDetail.setSubscriptionId(subscription_id);
			// redisEntryValueDetail.setUserSubAuthKey(user_sub_auth_key);
			redisEntryValueDetail.setCreatedOn(df.parse(created_on));
			redisEntryValueDetail.setActivationDate(activation_date);
			redisEntryValueDetail.setChargedBillingCode(charged_billing_code);
			redisEntryValueDetail.setChargedPrice(0D);
			redisEntryValueDetail.setCountry("MY");
			redisEntryValueDetail.setCustomerTransactionId("TXN123456");
			redisEntryValueDetail.setEndDate(end_date);
			redisEntryValueDetail.setItemId(1);
			redisEntryValueDetail.setItemTypeId(1);
			redisEntryValueDetail.setMode("WAP");
			redisEntryValueDetail.setMsisdn(msisdn);
			redisEntryValueDetail.setNextBillingDate(next_billing_date);
			redisEntryValueDetail.setPaid(DateUtils.convertIntToBoolean(paid));
			// redisEntryValueDetail.setPartner("UMOBILE");
			redisEntryValueDetail.setPartnerId(partner_id);
			// redisEntryValueDetail.setPlanCycle("WEEKLY");
			redisEntryValueDetail.setProductId(product_id);
			redisEntryValueDetail.setStartDate(start_date);
			// redisEntryValueDetail.setSubType(SubType.PAID);
			// redisEntryValueDetail.setSubscribeable(DateUtils.convertIntToBoolean(subscribable));
			redisEntryValueDetail.setSubscriptionBillingCode(subscription_billing_code);
			redisEntryValueDetail.setSubscriptionValidityDays(validity_days);
			redisEntryValueDetail.setUserId(user_id);
			redisEntryValueDetail.setUserPreferredLanguage("en");
			redisEntryValueDetail.setUserSource("D_KIM_87348");
			// redisEntryValueDetail.setClientUserId("viu-xyt-84873");
			redisEntryValueDetail.setRenewalAllowed(DateUtils.convertIntToBoolean(renewalAllowed));

			switch (subscription_status) {
			case "ACT_INIT":
				redisEntryValueDetail.setSubscriptionStatus(SubscriptionStatus.ACT_INIT);
				break;
			case "ACTIVATED":
				redisEntryValueDetail.setSubscriptionStatus(SubscriptionStatus.ACTIVATED);
				break;
			case "DEACTIVATED":
				redisEntryValueDetail.setSubscriptionStatus(SubscriptionStatus.DEACTIVATED);
				break;
			case "PARKING":
				redisEntryValueDetail.setSubscriptionStatus(SubscriptionStatus.PARKING);
				break;
			case "SUSPEND":
				redisEntryValueDetail.setSubscriptionStatus(SubscriptionStatus.SUSPEND);
				break;
			case "DCT_INIT":
				redisEntryValueDetail.setSubscriptionStatus(SubscriptionStatus.DCT_INIT);
				break;
			default:
				logger.info("No match for subscription status");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		UserKeySubscriptionIdDoc userKeySubscriptionIdDoc = new UserKeySubscriptionIdDoc();
		userKeySubscriptionIdDoc.setSubscriptionId(subscription_id);
		String redisKeyUSERID = user_sub_auth_key;
		String redisKeyMSISDN = "MSISDN_" + product_id + "_" + msisdn;
		logger.info("Redis Key UserId:" + redisKeyUSERID);
		logger.info("Redis Key Msisdn:" + redisKeyMSISDN);
		logger.info("Redis Key SubId:" + String.valueOf(subscription_id));
		RedisTemplateConnection.getRedisConnection().opsForValue().set(redisKeyUSERID, userKeySubscriptionIdDoc);
		RedisTemplateConnection.getRedisConnection().opsForValue().set(redisKeyMSISDN, userKeySubscriptionIdDoc);
		RedisTemplateConnection.getRedisConnection().opsForValue()
				.set(String.valueOf(userKeySubscriptionIdDoc.getSubscriptionId()), redisEntryValueDetail);
		logger.info("Redis Data" + redisEntryValueDetail.toString());
	}

	public static SubscriptionUnBlock generateSubscriptionUnBlockValues(String userId, String blockType, int productId,
			int partnerId, String country) {
		logger.info("generate subscription block values");
		SubscriptionUnBlock subscriptionUnBlock = loadJson("susbscriptionUnBlock.json", SubscriptionUnBlock.class);
		subscriptionUnBlock.setBlockType(blockType);
		subscriptionUnBlock.setUserId(userId);
		subscriptionUnBlock.setPartnerId(productId);
		subscriptionUnBlock.setProductId(partnerId);
		subscriptionUnBlock.setCountry(country);
		return subscriptionUnBlock;
	}

	public static SubscriptionBlock generateSubscriptionBlockValues(String userId, int productId, int partnerId,
			String country, String msisdn) {
		logger.info("generate subscription block values");
		SubscriptionBlock subscriptionBlock = loadJson("subscriptionBlock.json", SubscriptionBlock.class);
		subscriptionBlock.getUserDetails().setUserId(userId);
		subscriptionBlock.getUserDetails().setMsisdn(msisdn);
		subscriptionBlock.setPartnerId(productId);
		subscriptionBlock.setProductId(partnerId);
		subscriptionBlock.setCountry(country);
		return subscriptionBlock;
	}

	public static SubscriptionBlockStatus generateSubscriptionBlockStatusValues(String userId, int productId,
			int partnerId, String country) {
		logger.info("generate subscription block values");
		SubscriptionBlockStatus subscriptionBlockStatus = loadJson("subscriptionBlockStatus.json",
				SubscriptionBlockStatus.class);
		subscriptionBlockStatus.setUserId(userId);
		subscriptionBlockStatus.setPartnerId(productId);
		subscriptionBlockStatus.setProductId(partnerId);
		subscriptionBlockStatus.setCountry(country);
		return subscriptionBlockStatus;
	}

	public static ConfirmSync generateConfirmCallValues(Integer productId, Integer partnerId,
			String subscriptionBillingCode, String chargedBillingCode, String fallbackBillingCode, Integer actionId,
			Integer activityId, Integer transactionStateId, String actionResult, Long subscriptionId, String userId,
			Boolean closed, Long nextBillingDate) {
		logger.info("generate confirm sync api values");
		ConfirmSync confirmValues = loadJson("ConfirmSync.json", ConfirmSync.class);
		confirmValues.setPartnerId(partnerId);
		confirmValues.setProductId(productId);
		confirmValues.setSubscriptionBillingCode(subscriptionBillingCode);
		confirmValues.setChargedBillingCode(chargedBillingCode);
		confirmValues.setFallbackBillingCode(fallbackBillingCode);
		confirmValues.setActionId(actionId);
		confirmValues.setActivityId(activityId);
		confirmValues.setTransactionStateId(transactionStateId);
		confirmValues.setActionResult(actionResult);
		confirmValues.setSubscriptionId(subscriptionId);
		confirmValues.setUserId(userId);
		confirmValues.setClosed(closed);
		confirmValues.setNextBillingDate(nextBillingDate);
		return confirmValues;

	}

	public static RabbitMessage generateRabbitMQValues(String activity, int partnerId, int productId,
			String subscriptionBillingCode, String chargedBillingCode, String action, String transactionState,
			Long subscriptionId, Boolean closed, Long nextBillingDate, String actionResult, Long activation_date,
			String transactionId, String userId) throws Exception {
		logger.info("generate rabbit mq values");
		RabbitMessage rabbitMessage = loadJson("RabbitMQMessage.json", RabbitMessage.class);
		logger.info("set default Value");

		rabbitMessage.setActivity(activity);
		rabbitMessage.setProductId(productId);
		rabbitMessage.setPartnerId(partnerId);
		rabbitMessage.setAttemptedBillingCode(subscriptionBillingCode);
		rabbitMessage.setChargedBillingCode(chargedBillingCode);
		rabbitMessage.setAction(action);
		rabbitMessage.setTransactionState(transactionState);
		rabbitMessage.setSubscriptionId(subscriptionId);
		rabbitMessage.setClosed(closed);
		rabbitMessage.setNextBillingDate(nextBillingDate);
		rabbitMessage.setActionResult(actionResult);
		rabbitMessage.setActivationDate(activation_date);
		rabbitMessage.setRequestedBillingCode(chargedBillingCode);
		rabbitMessage.setTransactionId(transactionId);
		rabbitMessage.setUserId(userId);
		rabbitMessage.setMsisdn(userId);

		SRabitMessageHelper.addMessageToQueue(rabbitMessage);

		return null;

	}

	public static void generateRedisValueForBlockUnblock(Long subscription_id, String country, String blockType,
			String created_on, Long end_date, String msisdn, int partnerId, int productId, Long start_date,
			String user_id) throws ParseException {

		logger.info("Generate Redis Value for Block/Unblock");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		BlockedUser redisEntryBlockUnblock = new BlockedUser();
		try {

			redisEntryBlockUnblock = loadJson("RedisBlockUnblock.json", BlockedUser.class);
			redisEntryBlockUnblock.setId(subscription_id);
			redisEntryBlockUnblock.setBlockType(blockType);
			redisEntryBlockUnblock.setCountry(country);
			redisEntryBlockUnblock.setCreateDate(df.parse(created_on));
			redisEntryBlockUnblock.setStartDate(start_date);
			redisEntryBlockUnblock.setEndDate(end_date);
			redisEntryBlockUnblock.setUserId(user_id);
			redisEntryBlockUnblock.setMsisdn(msisdn);
			redisEntryBlockUnblock.setProductId(productId);
			redisEntryBlockUnblock.setPartnerId(partnerId);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		String redisKey = "USERID_" + productId + "_" + partnerId + "_" + country + "_" + user_id;
		logger.info("Redis Key for Block/Unblock User:" + redisKey);
		RedisTemplateConnection.getRedisConnection().opsForValue().set(redisKey, redisEntryBlockUnblock);
		System.out.println("Redis Data for Block/Unblock" + redisEntryBlockUnblock.toString());
	}

	public static ConfirmSyncQueue generateConfirmDataForQueue(Integer productId, Integer partnerId,
			String subscriptionBillingCode, String chargedBillingCode, String fallbackBillingCode, Integer actionId,
			Integer activityId, Integer transactionStateId, String actionResult, Long subscriptionId, String userId,
			Boolean closed, Long nextBillingDate) {
		logger.info("generate confirm sync api values");
		ConfirmSyncQueue confirmValues = loadJson("ConfirmSyncQueue.json", ConfirmSyncQueue.class);
		confirmValues.setPartnerId(partnerId);
		confirmValues.setProductId(productId);
		confirmValues.setRequestedBillingCode(subscriptionBillingCode);
		confirmValues.setChargedBillingCode(chargedBillingCode);
		confirmValues.setAttemptedBillingCode(fallbackBillingCode);
		confirmValues.setAction(actionId);
		confirmValues.setActivity(activityId);
		confirmValues.setTransactionState(transactionStateId);
		confirmValues.setActionResult(actionResult);
		confirmValues.setSubscriptionId(subscriptionId);
		confirmValues.setUserId(userId);
		confirmValues.setClosed(closed);
		confirmValues.setNextBillingDate(nextBillingDate);
		return confirmValues;

	}
}
