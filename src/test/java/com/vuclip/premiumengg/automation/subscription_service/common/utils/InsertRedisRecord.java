//package com.vuclip.premiumengg.automation.subscription_service.common.utils;
//
//import org.apache.log4j.Logger;
//
//import com.vuclip.premiumengg.automation.common.Log4J;
//import com.vuclip.premiumengg.automation.subscription_service.common.models.RedisEntryValueDetail;
//import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
//
//public class InsertRedisRecord {
//
//	private static Logger logger = Log4J.getLogger("InsertRedisRecord");
//
//	public static void insertIntoRedis(Long subscription_id, String created_on, String activation_date, int blacklisted,
//			String charged_billing_code, Double charged_price, String circle_code, int cooldowned, String country,
//			String customer_transaction_id, String deactivation_date, String end_date, int item_id, int item_type_id,
//			String mode, String msisdn, String next_billing_date, int paid, String partner, int partner_id,
//			String plan_cycle, int product_id, String start_date, String sub_type, int subscribable,
//			String subscription_billing_code, String subscription_status, int validity_days, String user_id,
//			String user_preffered_language, String user_source, String user_sub_auth_key) {
//
//		logger.info("Insert Record Into Redis");
//
//		RedisEntryValueDetail redisEntryValueDetail;
//		
//	    redisEntryValueDetail = SSUtils.loadJson("redis.json", RedisEntryValueDetail.class);
//		redisEntryValueDetail.setSubscriptionId(subscription_id);
//		redisEntryValueDetail.setCreatedOn(created_on.replaceAll("'", ""));
//		redisEntryValueDetail.setActivationDate(activation_date.replaceAll("'", ""));
//		redisEntryValueDetail.setBlacklisted(blacklisted);
//		redisEntryValueDetail.setChargedBillingCode(charged_billing_code.replaceAll("'", ""));
//		redisEntryValueDetail.setChargedPrice(charged_price);
//		redisEntryValueDetail.setCooldowned(cooldowned);
//		redisEntryValueDetail.setCountry( country.replaceAll("'",""));
//		redisEntryValueDetail.setCustomerTransactionId(customer_transaction_id.replaceAll("'",""));
//		redisEntryValueDetail.setDeactivationDate("");
//		redisEntryValueDetail.setEndDate( end_date.replaceAll("'",""));
//		redisEntryValueDetail.setItemId(item_id);
//		redisEntryValueDetail.setItemTypeId(item_type_id);
//		redisEntryValueDetail.setMode(mode.replaceAll("'",""));
//		redisEntryValueDetail.setMsisdn( msisdn.replaceAll("'",""));
//		redisEntryValueDetail.setNextBillingDate( next_billing_date.replaceAll("'",""));
//		redisEntryValueDetail.setPaid(paid);
//		redisEntryValueDetail.setPartner(partner.replaceAll("'",""));
//		redisEntryValueDetail.setPartnerId(partner_id);
//		redisEntryValueDetail.setPlanCycle( "WEEKLY");
//		redisEntryValueDetail.setProductId(product_id);
//		redisEntryValueDetail.setStartDate( start_date.replaceAll("'",""));
//		redisEntryValueDetail.setSubType( "");
//		redisEntryValueDetail.setSubscribeable(subscribable);
//		redisEntryValueDetail.setSubscriptionBillingCode( subscription_billing_code.replaceAll("'",""));
//		redisEntryValueDetail.setSubscriptionStatus( subscription_status.replaceAll("'",""));
//		redisEntryValueDetail.setSubscriptionValidityDays( String.valueOf(validity_days));
//		redisEntryValueDetail.setUserId(user_id.replaceAll("'",""));
//		redisEntryValueDetail.setUserPreferredLanguage( user_preffered_language.replaceAll("'",""));
//		redisEntryValueDetail.setUserSource( user_source.replaceAll("'",""));
//		redisEntryValueDetail.setUserSubAuthKey( user_sub_auth_key.replaceAll("'",""));
//		redisEntryValueDetail.setClientUserId( "viu-xyt-84873");
//		redisEntryValueDetail.setRenewalAllowed(0);
//		
//		String redisKey=SSUtils.productId +"_"+user_id.replaceAll("'", "");
//		String redisValue=ObjectMapperUtils.marshal(redisEntryValueDetail);
//		SSRedisUtils.insertIntoRedis(redisKey,redisValue );
//		
//		
//	}
//}
