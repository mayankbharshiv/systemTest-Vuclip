package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import java.text.ParseException;

import org.apache.commons.lang3.RandomUtils;

import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSync;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSyncData;
import com.vuclip.premiumengg.automation.subscription_service.common.models.ConfirmSyncQueue;
import com.vuclip.premiumengg.automation.subscription_service.common.models.GetUserStatusTestData;
import com.vuclip.premiumengg.automation.subscription_service.common.models.StateTransitionData;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionUBData;
import com.vuclip.premiumengg.automation.subscription_service.common.models.VerifyBlockUserStatusData;

public class TestDataCreator {

	static Long subscription_id;
	static String created_on;
	static Long end_date;
	static int partner_id;
	static int product_id;
	static Long start_date;
	static String user_id;
	static Long activation_date;
	static int blacklisted;
	static String charged_billing_code;
	static int cooldowned;
	static Long deactivation_date;
	static String msisdn;
	static Long next_billing_date;
	static int paid;
	static String sub_type;
	static int subscribable;
	static String subscription_billing_code;
	static String subscription_status;
	static int validity_days;
	static int renewalAllowed;
	static String user_sub_auth_key;
	static String country;

	public static String createSubscriptionUnblockTestData(SubscriptionUBData subscriptionUnblockTest)
			throws ParseException {
		subscription_id = subscriptionUnblockTest.getSubscription_id();
		created_on = subscriptionUnblockTest.getCreated_on();
		end_date = subscriptionUnblockTest.getEnd_date();
		partner_id = subscriptionUnblockTest.getPartner_id();
		product_id = subscriptionUnblockTest.getProduct_id();
		start_date = subscriptionUnblockTest.getStart_date();
		user_id = subscriptionUnblockTest.getUser_id();
		String blockType = subscriptionUnblockTest.getBlockType();
		// String flagType = subscriptionUnblockTest.getFlagType();
		int blockTypeValue = subscriptionUnblockTest.getBlockTypeValue();
		activation_date = subscriptionUnblockTest.getActivation_date();
		blacklisted = subscriptionUnblockTest.getBlacklisted();
		charged_billing_code = subscriptionUnblockTest.getCharged_billing_code();
		cooldowned = subscriptionUnblockTest.getCooldowned();
		deactivation_date = subscriptionUnblockTest.getDeactivation_date();
		msisdn = subscriptionUnblockTest.getMsisdn();
		next_billing_date = subscriptionUnblockTest.getNext_billing_date();
		paid = subscriptionUnblockTest.getPaid();
		sub_type = subscriptionUnblockTest.getSub_type();
		subscribable = subscriptionUnblockTest.getSubscribable();
		subscription_billing_code = subscriptionUnblockTest.getSubscription_billing_code();
		subscription_status = subscriptionUnblockTest.getSubscription_status();
		validity_days = subscriptionUnblockTest.getValidity_days();
		country = subscriptionUnblockTest.getCountry();
		user_sub_auth_key = "USERID_" + product_id + "_" + user_id;

		SUtils.generateBlockedUserTableData(subscription_id, blockTypeValue, created_on, end_date, product_id,
				partner_id, start_date, user_id, country);

		SUtils.generateUserSubscriptionTableData(subscription_id, created_on, activation_date, blacklisted,
				charged_billing_code, cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid,
				partner_id, product_id, start_date, sub_type, subscribable, subscription_billing_code,
				subscription_status, validity_days, user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueUserId(subscription_id, created_on, activation_date, blacklisted, charged_billing_code,
				cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid, partner_id, product_id,
				start_date, sub_type, subscribable, subscription_billing_code, subscription_status, validity_days,
				user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueForBlockUnblock(subscription_id, country, blockType, created_on, end_date, msisdn,
				partner_id, product_id, start_date, user_id);

		return user_sub_auth_key;

	}

	public static String createSubscriptionUnblockValExpiredTestData(SubscriptionUBData subscriptionUnblockTest)
			throws ParseException {
		subscription_id = subscriptionUnblockTest.getSubscription_id();
		created_on = subscriptionUnblockTest.getCreated_on();
		end_date = subscriptionUnblockTest.getEnd_date();
		partner_id = subscriptionUnblockTest.getPartner_id();
		product_id = subscriptionUnblockTest.getProduct_id();
		start_date = subscriptionUnblockTest.getStart_date();
		user_id = subscriptionUnblockTest.getUser_id();
		// aString blockType = subscriptionUnblockTest.getBlockType();
		// String flagType = subscriptionUnblockTest.getFlagType();
		int blockTypeValue = subscriptionUnblockTest.getBlockTypeValue();
		activation_date = subscriptionUnblockTest.getActivation_date();
		blacklisted = subscriptionUnblockTest.getBlacklisted();
		charged_billing_code = subscriptionUnblockTest.getCharged_billing_code();
		cooldowned = subscriptionUnblockTest.getCooldowned();
		deactivation_date = subscriptionUnblockTest.getDeactivation_date();
		msisdn = subscriptionUnblockTest.getMsisdn();
		next_billing_date = subscriptionUnblockTest.getNext_billing_date();
		paid = subscriptionUnblockTest.getPaid();
		sub_type = subscriptionUnblockTest.getSub_type();
		subscribable = subscriptionUnblockTest.getSubscribable();
		subscription_billing_code = subscriptionUnblockTest.getSubscription_billing_code();
		subscription_status = subscriptionUnblockTest.getSubscription_status();
		validity_days = subscriptionUnblockTest.getValidity_days();
		country = subscriptionUnblockTest.getCountry();
		user_sub_auth_key = "USERID_" + product_id + "_" + user_id;

		SUtils.generateBlockedUserTableData(subscription_id, blockTypeValue, created_on, end_date, product_id,
				partner_id, start_date, user_id, country);

		SUtils.generateUserSubscriptionTableData(subscription_id, created_on, activation_date, blacklisted,
				charged_billing_code, cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid,
				partner_id, product_id, start_date, sub_type, subscribable, subscription_billing_code,
				subscription_status, validity_days, user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueUserId(subscription_id, created_on, activation_date, blacklisted, charged_billing_code,
				cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid, partner_id, product_id,
				start_date, sub_type, subscribable, subscription_billing_code, subscription_status, validity_days,
				user_id, user_sub_auth_key, renewalAllowed);

		return user_sub_auth_key;

	}

	public static String createSubscriptionBlockUserStatus(VerifyBlockUserStatusData subscriptionUnblockTest)
			throws ParseException {
		subscription_id = subscriptionUnblockTest.getSubscription_id();
		created_on = subscriptionUnblockTest.getCreated_on();
		end_date = subscriptionUnblockTest.getEnd_date();
		partner_id = subscriptionUnblockTest.getPartner_id();
		product_id = subscriptionUnblockTest.getProduct_id();
		start_date = subscriptionUnblockTest.getStart_date();
		user_id = subscriptionUnblockTest.getUser_id();
		String blockType = subscriptionUnblockTest.getBlockType();
		// String flagType = subscriptionUnblockTest.getFlagType();
		int blockTypeValue = subscriptionUnblockTest.getBlockTypeValue();
		activation_date = subscriptionUnblockTest.getActivation_date();
		blacklisted = subscriptionUnblockTest.getBlacklisted();
		charged_billing_code = subscriptionUnblockTest.getCharged_billing_code();
		cooldowned = subscriptionUnblockTest.getCooldowned();
		deactivation_date = subscriptionUnblockTest.getDeactivation_date();
		msisdn = subscriptionUnblockTest.getMsisdn();
		next_billing_date = subscriptionUnblockTest.getNext_billing_date();
		paid = subscriptionUnblockTest.getPaid();
		sub_type = subscriptionUnblockTest.getSub_type();
		subscribable = subscriptionUnblockTest.getSubscribable();
		subscription_billing_code = subscriptionUnblockTest.getSubscription_billing_code();
		subscription_status = subscriptionUnblockTest.getSubscription_status();
		validity_days = subscriptionUnblockTest.getValidity_days();
		country = subscriptionUnblockTest.getCountry();
		user_sub_auth_key = "USERID_" + product_id + "_" + user_id;

		SUtils.generateBlockedUserTableData(subscription_id, blockTypeValue, created_on, end_date, product_id,
				partner_id, start_date, user_id, country);

		SUtils.generateUserSubscriptionTableData(subscription_id, created_on, activation_date, blacklisted,
				charged_billing_code, cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid,
				partner_id, product_id, start_date, sub_type, subscribable, subscription_billing_code,
				subscription_status, validity_days, user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueUserId(subscription_id, created_on, activation_date, blacklisted, charged_billing_code,
				cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid, partner_id, product_id,
				start_date, sub_type, subscribable, subscription_billing_code, subscription_status, validity_days,
				user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueForBlockUnblock(subscription_id, country, blockType, created_on, end_date, msisdn,
				partner_id, product_id, start_date, user_id);

		return user_sub_auth_key;

	}

	public static String subscriptionBlockTestForActivatedUser(SubscriptionUBData subscriptionBlockData)
			throws ParseException {
		subscription_id = subscriptionBlockData.getSubscription_id();
		created_on = subscriptionBlockData.getCreated_on();
		activation_date = subscriptionBlockData.getActivation_date();
		blacklisted = subscriptionBlockData.getBlacklisted();
		charged_billing_code = subscriptionBlockData.getCharged_billing_code();
		cooldowned = subscriptionBlockData.getCooldowned();
		deactivation_date = subscriptionBlockData.getDeactivation_date();
		end_date = subscriptionBlockData.getEnd_date();
		msisdn = subscriptionBlockData.getMsisdn();
		next_billing_date = subscriptionBlockData.getNext_billing_date();
		paid = subscriptionBlockData.getPaid();
		partner_id = subscriptionBlockData.getPartner_id();
		product_id = subscriptionBlockData.getProduct_id();
		start_date = subscriptionBlockData.getStart_date();
		sub_type = subscriptionBlockData.getSub_type();
		subscription_billing_code = subscriptionBlockData.getSubscription_billing_code();
		subscription_status = subscriptionBlockData.getSubscription_status();
		validity_days = subscriptionBlockData.getValidity_days();
		user_id = subscriptionBlockData.getUser_id();
		// String blockType = subscriptionBlockData.getBlockType();
		country = subscriptionBlockData.getCountry();
		String user_sub_auth_key;
		// String validityType = subscriptionBlockData.getValidityType();
		// String flagType = subscriptionBlockData.getFlagType();
		user_sub_auth_key = "USERID_" + product_id + "_" + user_id;

		SUtils.generateUserSubscriptionTableData(subscription_id, created_on, activation_date, blacklisted,
				charged_billing_code, cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid,
				partner_id, product_id, start_date, sub_type, subscribable, subscription_billing_code,
				subscription_status, validity_days, user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueUserId(subscription_id, created_on, activation_date, blacklisted, charged_billing_code,
				cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid, partner_id, product_id,
				start_date, sub_type, subscribable, subscription_billing_code, subscription_status, validity_days,
				user_id, user_sub_auth_key, renewalAllowed);

		return user_sub_auth_key;
	}

	public static String subscriptionBlockTestForDeactivatedUser(SubscriptionUBData subscriptionBlockData)
			throws ParseException {
		subscription_id = subscriptionBlockData.getSubscription_id();
		created_on = subscriptionBlockData.getCreated_on();
		activation_date = subscriptionBlockData.getActivation_date();
		blacklisted = subscriptionBlockData.getBlacklisted();
		charged_billing_code = subscriptionBlockData.getCharged_billing_code();
		cooldowned = subscriptionBlockData.getCooldowned();
		deactivation_date = subscriptionBlockData.getDeactivation_date();
		end_date = subscriptionBlockData.getEnd_date();
		msisdn = subscriptionBlockData.getMsisdn();
		next_billing_date = subscriptionBlockData.getNext_billing_date();
		paid = subscriptionBlockData.getPaid();
		partner_id = subscriptionBlockData.getPartner_id();
		product_id = subscriptionBlockData.getProduct_id();
		start_date = subscriptionBlockData.getStart_date();
		sub_type = subscriptionBlockData.getSub_type();
		subscription_billing_code = subscriptionBlockData.getSubscription_billing_code();
		subscription_status = subscriptionBlockData.getSubscription_status();
		validity_days = subscriptionBlockData.getValidity_days();
		user_id = subscriptionBlockData.getUser_id();
		// String blockType = subscriptionBlockData.getBlockType();
		country = subscriptionBlockData.getCountry();
		// String validityType = subscriptionBlockData.getValidityType();
		// String flagType = subscriptionBlockData.getFlagType();
		String user_sub_auth_key = "USERID_" + product_id + "_" + user_id;

		SUtils.generateUserSubscriptionTableData(subscription_id, created_on, activation_date, blacklisted,
				charged_billing_code, cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid,
				partner_id, product_id, start_date, sub_type, subscribable, subscription_billing_code,
				subscription_status, validity_days, user_id, user_sub_auth_key, renewalAllowed);

		return user_sub_auth_key;
	}

	/*
	 * public static void subscriptionBlacklistCooldownTest(
	 * BlockedUserDateForSubscriptionBlock subscriptionBlacklistCooldownTestData)
	 * throws ParseException { Long subscription_id =
	 * subscriptionBlacklistCooldownTestData.getSubscription_id(); String created_on
	 * = subscriptionBlacklistCooldownTestData.getCreated_on(); String end_date =
	 * subscriptionBlacklistCooldownTestData.getEnd_date(); int partner_id =
	 * subscriptionBlacklistCooldownTestData.getPartner_id(); int product_id =
	 * subscriptionBlacklistCooldownTestData.getProduct_id(); String start_date =
	 * subscriptionBlacklistCooldownTestData.getStart_date(); String user_id =
	 * subscriptionBlacklistCooldownTestData.getUser_id(); int blockTypeValue =
	 * subscriptionBlacklistCooldownTestData.getBlockTypeValue();
	 * 
	 * SSUtils.generateBlockedUserTableData(subscription_id, blockTypeValue,
	 * created_on, end_date, product_id, partner_id, start_date, user_id); }
	 */
	public static String createUserStatusActivationTestData(GetUserStatusTestData getUserStatus) throws ParseException {
		subscription_id = getUserStatus.getSubscription_id();
		created_on = getUserStatus.getCreated_on();
		end_date = getUserStatus.getEnd_date();
		partner_id = getUserStatus.getPartner_id();
		product_id = getUserStatus.getProduct_id();
		start_date = getUserStatus.getStart_date();
		user_id = getUserStatus.getUser_id();
		activation_date = getUserStatus.getActivation_date();
		blacklisted = getUserStatus.getBlacklisted();
		charged_billing_code = getUserStatus.getCharged_billing_code();
		cooldowned = getUserStatus.getCooldowned();
		deactivation_date = getUserStatus.getDeactivation_date();
		msisdn = getUserStatus.getMsisdn();
		next_billing_date = getUserStatus.getNext_billing_date();
		paid = getUserStatus.getPaid();
		sub_type = getUserStatus.getSub_type();
		subscribable = getUserStatus.getSubscribable();
		subscription_billing_code = getUserStatus.getSubscription_billing_code();
		subscription_status = getUserStatus.getSubscription_status();
		validity_days = getUserStatus.getValidity_days();
		renewalAllowed = getUserStatus.getRenewalAllowed();
		user_sub_auth_key = "USERID_" + product_id + "_" + user_id;

		SUtils.generateUserSubscriptionTableData(subscription_id, created_on, activation_date, blacklisted,
				charged_billing_code, cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid,
				partner_id, product_id, start_date, sub_type, subscribable, subscription_billing_code,
				subscription_status, validity_days, user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueUserId(subscription_id, created_on, activation_date, blacklisted, charged_billing_code,
				cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid, partner_id, product_id,
				start_date, sub_type, subscribable, subscription_billing_code, subscription_status, validity_days,
				user_id, user_sub_auth_key, renewalAllowed);

		return user_sub_auth_key;
	}

	public static ConfirmSync getConfirmSyncSuccessTest(ConfirmSyncData confirmSuccess) {
		Integer productId = confirmSuccess.getProductId();
		Integer partnerId = confirmSuccess.getPartnerId();
		String subscriptionBillingCode = confirmSuccess.getSubscriptionBillingCode();
		String chargedBillingCode = confirmSuccess.getChargedBillingCode();
		String fallbackBillingCode = confirmSuccess.getFallbackBillingCode();
		Integer actionId = confirmSuccess.getActionId();
		Integer activityId = confirmSuccess.getActivityId();
		Integer transactionStateId = confirmSuccess.getTransactionStateId();
		String actionResult = confirmSuccess.getActionResult();
		Long subscriptionId = confirmSuccess.getSubscriptionId();
		String userId = confirmSuccess.getUserId();
		Boolean closed = confirmSuccess.getClosed();
		Long nextBillingDate = confirmSuccess.getNextBillingDate();

		return SUtils.generateConfirmCallValues(productId, partnerId, subscriptionBillingCode, chargedBillingCode,
				fallbackBillingCode, actionId, activityId, transactionStateId, actionResult, subscriptionId, userId,
				closed, nextBillingDate);
	}

	public static String createStateTransitionTestDate(StateTransitionData getUserStatus) throws Exception {
		subscription_id = getUserStatus.getSubscription_id();
		created_on = getUserStatus.getCreated_on();
		end_date = getUserStatus.getEnd_date();
		partner_id = getUserStatus.getPartner_id();
		product_id = getUserStatus.getProduct_id();
		start_date = getUserStatus.getStart_date();
		user_id = getUserStatus.getUser_id();
		activation_date = getUserStatus.getActivation_date();
		blacklisted = getUserStatus.getBlacklisted();
		charged_billing_code = getUserStatus.getCharged_billing_code();
		cooldowned = getUserStatus.getCooldowned();
		deactivation_date = getUserStatus.getDeactivation_date();
		msisdn = getUserStatus.getMsisdn();
		next_billing_date = getUserStatus.getNext_billing_date();
		paid = getUserStatus.getPaid();
		sub_type = getUserStatus.getSub_type();
		subscribable = getUserStatus.getSubscribable();
		subscription_billing_code = getUserStatus.getSubscription_billing_code();
		subscription_status = getUserStatus.getSubscription_status();
		validity_days = getUserStatus.getValidity_days();
		renewalAllowed = getUserStatus.getRenewalAllowed();
		user_sub_auth_key = "USERID_" + product_id + "_" + user_id;

		SUtils.generateUserSubscriptionTableData(subscription_id, created_on, activation_date, blacklisted,
				charged_billing_code, cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid,
				partner_id, product_id, start_date, sub_type, subscribable, subscription_billing_code,
				subscription_status, validity_days, user_id, user_sub_auth_key, renewalAllowed);

		SUtils.generateRedisValueUserId(subscription_id, created_on, activation_date, blacklisted, charged_billing_code,
				cooldowned, deactivation_date, end_date, msisdn, next_billing_date, paid, partner_id, product_id,
				start_date, sub_type, subscribable, subscription_billing_code, subscription_status, validity_days,
				user_id, user_sub_auth_key, renewalAllowed);

		String transactionKey = "TRANSACTION_KEY" + RandomUtils.nextInt(10000, 90000);

		SUtils.generateRabbitMQValues(getUserStatus.getActivity_Id(), partner_id, product_id, subscription_billing_code,
				charged_billing_code, getUserStatus.getAction_Id(), getUserStatus.getTransaction_StateId(),
				subscription_id, getUserStatus.getClosed(), next_billing_date, getUserStatus.getActionResult(),
				activation_date, transactionKey, user_id);

		return user_sub_auth_key;
	}

	public static ConfirmSyncQueue getConfirmSyncQueueData(ConfirmSyncData confirmSuccess) {
		Integer productId = confirmSuccess.getProductId();
		Integer partnerId = confirmSuccess.getPartnerId();
		String subscriptionBillingCode = confirmSuccess.getSubscriptionBillingCode();
		String chargedBillingCode = confirmSuccess.getChargedBillingCode();
		String fallbackBillingCode = confirmSuccess.getFallbackBillingCode();
		Integer actionId = confirmSuccess.getActionId();
		Integer activityId = confirmSuccess.getActivityId();
		Integer transactionStateId = confirmSuccess.getTransactionStateId();
		String actionResult = confirmSuccess.getActionResult();
		Long subscriptionId = confirmSuccess.getSubscriptionId();
		String userId = confirmSuccess.getUserId();
		Boolean closed = confirmSuccess.getClosed();
		Long nextBillingDate = confirmSuccess.getNextBillingDate();

		return SUtils.generateConfirmDataForQueue(productId, partnerId, subscriptionBillingCode, chargedBillingCode,
				fallbackBillingCode, actionId, activityId, transactionStateId, actionResult, subscriptionId, userId,
				closed, nextBillingDate);
	}
}
