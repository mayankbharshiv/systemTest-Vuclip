package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class InsertDatabaseRecord {

    private static Logger logger = Log4J.getLogger("SSUtils");

    public static void insertIntoUserSubscriptionTable(Long subscription_id, String created_on, String activation_date,
                                                       int blacklisted, String charged_billing_code, Double charged_price, String circle_code, int cooldowned,
                                                       String country, String customer_transaction_id, String deactivation_date, String end_date, int item_id,
                                                       int item_type_id, String mode, String msisdn, String next_billing_date, int paid, String partner,
                                                       int partner_id, String plan_cycle, int product_id, String start_date, String sub_type, int subscribable,
                                                       String subscription_billing_code, String subscription_status, int validity_days, String user_id,
                                                       String user_preffered_language, String user_source, String user_sub_auth_key) {
        Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

        logger.info("Insert Record Into User Subscription Table");

        insertRecordinDatabase.put("subscription_id", subscription_id);
        insertRecordinDatabase.put("created_on", created_on);
        insertRecordinDatabase.put("activation_date", activation_date);
        insertRecordinDatabase.put("blacklisted", blacklisted);
        insertRecordinDatabase.put("charged_billing_code", charged_billing_code);
        insertRecordinDatabase.put("charged_price", charged_price);
        insertRecordinDatabase.put("circle_code", circle_code);
        insertRecordinDatabase.put("cooldowned", cooldowned);
        insertRecordinDatabase.put("country", country);
        insertRecordinDatabase.put("customer_transaction_id", customer_transaction_id);
        insertRecordinDatabase.put("deactivation_date", deactivation_date);
        insertRecordinDatabase.put("end_date", end_date);
        insertRecordinDatabase.put("item_id", item_id);
        insertRecordinDatabase.put("item_type_id", item_type_id);
        insertRecordinDatabase.put("mode", mode);
        insertRecordinDatabase.put("msisdn", msisdn);
        insertRecordinDatabase.put("next_billing_date", next_billing_date);
        insertRecordinDatabase.put("paid", paid);
        insertRecordinDatabase.put("partner", partner);
        insertRecordinDatabase.put("partner_id", partner_id);
        insertRecordinDatabase.put("plan_cycle", plan_cycle);
        insertRecordinDatabase.put("product_id", product_id);
        insertRecordinDatabase.put("start_date", start_date);
        insertRecordinDatabase.put("sub_type", sub_type);
        insertRecordinDatabase.put("subscribable", subscribable);
        insertRecordinDatabase.put("subscription_billing_code", subscription_billing_code);
        insertRecordinDatabase.put("subscription_status", subscription_status);
        insertRecordinDatabase.put("validity_days", validity_days);
        insertRecordinDatabase.put("user_id", user_id);
        insertRecordinDatabase.put("user_preffered_language", user_preffered_language);
        insertRecordinDatabase.put("user_source", user_source);
        insertRecordinDatabase.put("user_sub_auth_key", user_sub_auth_key);
        SDBHelper.addRecordInTable("user_subscription", insertRecordinDatabase);

    }

    public static void insertIntoBlockedUserTable(Integer id, Integer block_type, String created_date, String end_date,
                                                  Integer product_id, Integer partner_id, String start_date, String user_id)

    {
        Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

        logger.info("Insert Record In Blocked User Table");

        insertRecordinDatabase.put("id", id);
        insertRecordinDatabase.put("block_type", block_type);
        insertRecordinDatabase.put("created_date", created_date);
        insertRecordinDatabase.put("end_date", end_date);
        insertRecordinDatabase.put("partner_id", partner_id);
        insertRecordinDatabase.put("product_id", product_id);
        insertRecordinDatabase.put("start_date", start_date);
        insertRecordinDatabase.put("user_id", user_id);
        SDBHelper.addRecordInTable("blocked_user", insertRecordinDatabase);
    }

    public static void insertIntoFreeTrialHistory(Integer id, String created_on, String last_free_trial_date,
                                                  Integer availed_free_trial_count, String last_free_trial_billing_code, Integer product_id,
                                                  Integer partner_id, String user_id)

    {
        Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

        logger.info("Insert Record In Free Trial History Table");

        insertRecordinDatabase.put("id", id);
        insertRecordinDatabase.put("created_on", created_on);
        insertRecordinDatabase.put("last_free_trial_date", last_free_trial_date);
        insertRecordinDatabase.put("partner_id", partner_id);
        insertRecordinDatabase.put("product_id", product_id);
        insertRecordinDatabase.put("availed_free_trial_count", availed_free_trial_count);
        insertRecordinDatabase.put("last_free_trial_billing_code", last_free_trial_billing_code);
        insertRecordinDatabase.put("user_id", user_id);
        SDBHelper.addRecordInTable("free_trial_history", insertRecordinDatabase);
    }

    public static void insertIntoProductPartner(int id, int allowed_free_trial_count, int blacklist_applicable,
                                                int blacklist_validity, int cooldown_applicable, int cooldown_validity, int partner_id, int product_id,
                                                int step_up_charging_applicable, int time_unit, int validity_from_partner)

    {
        Map<String, Object> insertRecordinDatabase = new HashMap<String, Object>();

        logger.info("Insert Record In Product Partner Table");

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
        SDBHelper.addRecordInTable("product_partner_mapping", insertRecordinDatabase);
    }

}
