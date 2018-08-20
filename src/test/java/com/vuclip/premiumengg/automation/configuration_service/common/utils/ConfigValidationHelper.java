package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.ConfigResponseVO;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConfigValidationHelper {
    private static Logger logger = Log4J.getLogger("PartnerValidationHelper");

    public static void validateDataInDB(ConfigResponseVO actual, ConfigResponseVO expected) throws SQLException {

        logger.info("Verify Database Tables");
        validateActionTable(actual, expected);
        validateActivityFlowsTable(actual, expected);
        validateAdNetworkNotificationTable(actual, expected);
        validateBlackoutTable(actual, expected);
        validateChurnNotificationTable(actual, expected);
        validateCriteriaTable(actual, expected);
        validateCriterionTable(actual, expected);
        validateDateComputationTable(actual, expected);
        validatePricePointTable(actual, expected);
        validateProductTable(actual, expected);
        validateProductCountryTable(actual, expected);
        validateProductPartnerTable(actual, expected);
        validateRetryTable(actual, expected);

    }

    private static void validateActivityFlowsTable(ConfigResponseVO actual, ConfigResponseVO expected)
            throws SQLException {
        logger.info("Verify Activity Flows table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("activity_flow_id", actual.getConfig().getActivityFlows().get(0).getActivityFlowId());
        expectedRecords.put("billing_code", expected.getConfig().getActivityFlows().get(0).getBillingCode());
        expectedRecords.put("mode", expected.getConfig().getActivityFlows().get(0).getMode());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("activity_flow",
                        "billing_code = '" + expected.getConfig().getActivityFlows().get(0).getBillingCode() + "'"),
                expectedRecords);
    }

    private static void validateAdNetworkNotificationTable(ConfigResponseVO actual, ConfigResponseVO expected)
            throws SQLException {
        logger.info("Verify Ad Network Notification Table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("ad_network_notification_id",
                actual.getConfig().getAdNetworkNotifications().get(0).getAdNetworkNotificationId());
        expectedRecords.put("price_point", expected.getConfig().getAdNetworkNotifications().get(0).getPricePoint());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("ad_network_notification", "ad_network_notification_id = '"
                        + actual.getConfig().getAdNetworkNotifications().get(0).getAdNetworkNotificationId() + "'"),
                expectedRecords);
    }

    private static void validateActionTable(ConfigResponseVO actual, ConfigResponseVO expected) throws SQLException {
        logger.info("Verify Action table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("action_id",
                actual.getConfig().getActivityFlows().get(0).getActions().get(0).getActionId());
        expectedRecords.put("action_name",
                expected.getConfig().getActivityFlows().get(0).getActions().get(0).getAction());
        expectedRecords.put("flow_type",
                expected.getConfig().getActivityFlows().get(0).getActions().get(0).getFlowType());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("action",
                        "action_name = '"
                                + expected.getConfig().getActivityFlows().get(0).getActions().get(0).getAction() + "'"),
                expectedRecords);
    }

    private static void validateBlackoutTable(ConfigResponseVO actual, ConfigResponseVO expected) throws SQLException {
        logger.info("Verify Blackout table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("blackout_id", actual.getConfig().getBlockouts().get(0).getBlackoutId());
        expectedRecords.put("product_id", actual.getConfig().getProduct().getProductId());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("blackout",
                        "blackout_id = '" + actual.getConfig().getBlockouts().get(0).getBlackoutId() + "'"),
                expectedRecords);
    }

    private static void validateChurnNotificationTable(ConfigResponseVO actual, ConfigResponseVO expected)
            throws SQLException {
        logger.info("Verify Churn table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("churn_notification_id",
                actual.getConfig().getChurnNotifications().get(0).getChurnNotificationId());
        expectedRecords.put("product_id", actual.getConfig().getProduct().getProductId());
        expectedRecords.put("type_of_churn", actual.getConfig().getChurnNotifications().get(0).getTypeOfChurn());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("churn_notification",
                        "churn_notification_id = '"
                                + actual.getConfig().getChurnNotifications().get(0).getChurnNotificationId() + "'"),
                expectedRecords);
    }

    private static void validateCriteriaTable(ConfigResponseVO actual, ConfigResponseVO expected) throws SQLException {
        logger.info("Verify Criteria table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("criteria_id",
                actual.getConfig().getSmsConfigs().get(0).getCriterias().get(0).getCriteriaId());
        expectedRecords.put("sms_text", expected.getConfig().getSmsConfigs().get(0).getCriterias().get(0).getSmsText());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("criteria", "criteria_id = '"
                        + actual.getConfig().getSmsConfigs().get(0).getCriterias().get(0).getCriteriaId() + "'"),
                expectedRecords);
    }

    private static void validateCriterionTable(ConfigResponseVO actual, ConfigResponseVO expected) throws SQLException {
        logger.info("Verify Criterion table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("criteria_id", actual.getConfig().getSmsConfigs().get(0).getCriterias().get(0)
                .getDateCoumputationCriterion().getDateComputationCriterionId());
        expectedRecords.put("name",
                expected.getConfig().getSmsConfigs().get(0).getCriterias().get(0).getCriterions().get(0).getName());
        expectedRecords.put("value",
                expected.getConfig().getSmsConfigs().get(0).getCriterias().get(0).getCriterions().get(0).getValue());
        expectedRecords.put("grouping_operator", expected.getConfig().getSmsConfigs().get(0).getCriterias().get(0)
                .getCriterions().get(0).getGroupingOperator());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("criterion",
                        "criteria_id = '" + actual.getConfig().getSmsConfigs().get(0).getCriterias().get(0)
                                .getDateCoumputationCriterion().getDateComputationCriterionId() + "'"),
                expectedRecords);
    }

    private static void validateDateComputationTable(ConfigResponseVO actual, ConfigResponseVO expected)
            throws SQLException {
        logger.info("Verify Date Computation table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("date_coumputation_criterion_id", actual.getConfig().getSmsConfigs().get(0).getCriterias()
                .get(0).getDateCoumputationCriterion().getDateComputationCriterionId());
        expectedRecords.put("name", expected.getConfig().getSmsConfigs().get(0).getCriterias().get(0)
                .getDateCoumputationCriterion().getName());
        expectedRecords.put("value", expected.getConfig().getSmsConfigs().get(0).getCriterias().get(0)
                .getDateCoumputationCriterion().getValue());
        expectedRecords.put("unit", expected.getConfig().getSmsConfigs().get(0).getCriterias().get(0)
                .getDateCoumputationCriterion().getUnit());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("date_coumputation_criterion",
                        "date_coumputation_criterion_id = '" + actual.getConfig().getSmsConfigs().get(0).getCriterias()
                                .get(0).getDateCoumputationCriterion().getDateComputationCriterionId() + "'"),
                expectedRecords);
    }

    private static void validatePricePointTable(ConfigResponseVO actual, ConfigResponseVO expected)
            throws SQLException {
        logger.info("Verify Price Point table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("description", expected.getConfig().getPricePoints().get(0).getDescription());
        expectedRecords.put("billing_code", actual.getConfig().getPricePoints().get(0).getBillingCode());
        expectedRecords.put("fallback_pp_billing_code",
                actual.getConfig().getPricePoints().get(0).getFallbackPpBillingCode());
        expectedRecords.put("free_trial_billing_code",
                actual.getConfig().getPricePoints().get(0).getFreeTrialBillingCode());
        expectedRecords.put("validity", expected.getConfig().getPricePoints().get(0).getValidity());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("price_point",
                        "description = '" + expected.getConfig().getPricePoints().get(0).getDescription() + "'"),
                expectedRecords);
    }

    private static void validateProductTable(ConfigResponseVO actual, ConfigResponseVO expected) throws SQLException {
        logger.info("Verify Product table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();
        expectedRecords.put("product_type", expected.getConfig().getProduct().getProductType());
        expectedRecords.put("product_name", expected.getConfig().getProduct().getProductName());
        expectedRecords.put("status", expected.getConfig().getProduct().getStatus());
        expectedRecords.put("store_type", expected.getConfig().getProduct().getStoreType());
        expectedRecords.put("product_id", actual.getConfig().getProduct().getProductId());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("product",
                        "product_name = '" + expected.getConfig().getProduct().getProductName() + "'"),
                expectedRecords);
    }

    private static void validateProductCountryTable(ConfigResponseVO actual, ConfigResponseVO expected)
            throws SQLException {
        logger.info("Verify Product table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();

        expectedRecords.put("product_id", actual.getConfig().getProduct().getProductId());
        CSValidationHelper.validateTableRecord(DBUtils.getRecord("product_country",
                "product_id = '" + actual.getConfig().getProduct().getProductId() + "'"), expectedRecords);
    }

    private static void validateProductPartnerTable(ConfigResponseVO actual, ConfigResponseVO expected)
            throws SQLException {
        logger.info("Verify Product Partner table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();

        expectedRecords.put("product_id", actual.getConfig().getProduct().getProductId());
        expectedRecords.put("partner_consent_parser_endpoint",
                expected.getConfig().getProductPartnerMappings().get(0).getPartnerConsentParserEndpoint());
        expectedRecords.put("partner_consent_url_generation_endpoint",
                expected.getConfig().getProductPartnerMappings().get(0).getPartnerConsentUrlGenerationEndpoint());
        expectedRecords.put("date_format", expected.getConfig().getProductPartnerMappings().get(0).getDateFormat());
        CSValidationHelper.validateTableRecord(DBUtils.getRecord("product_country",
                "product_id = '" + actual.getConfig().getProduct().getProductId() + "'"), expectedRecords);
    }

    private static void validateRetryTable(ConfigResponseVO actual, ConfigResponseVO expected) throws SQLException {
        logger.info("Verify Retry table Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();

        expectedRecords.put("activity_type", expected.getConfig().getRetry().get(0).getActivityType());
        expectedRecords.put("type_of_cycle", expected.getConfig().getRetry().get(0).getTypeOfCycle());
        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("retry", "retry_id = '" + actual.getConfig().getRetry().get(0).getRetryId() + "'"),
                expectedRecords);
    }

    public static void validateResponse(ConfigResponseVO actual, ConfigResponseVO expected) {
        AppAssert.assertEqual(actual.getMessage(), expected.getMessage(), "Verification for message");
        AppAssert.assertEqual(actual.getResponseCode(), expected.getResponseCode(), "Verification for respose code");
        AppAssert.assertEqual(actual.getSuccessful(), expected.getSuccessful(), "Verification for respose code");
        AppAssert.assertEqual(actual.getConfig().getProduct().getProductName(),
                expected.getConfig().getProduct().getProductName(), "Verify for Prodcut Name");
        AppAssert.assertEqual(actual.getConfig().getProduct().getStoreType(),
                expected.getConfig().getProduct().getStoreType(), "Verify for Store Type");
        AppAssert.assertEqual(actual.getConfig().getProduct().getStatus().toLowerCase(),
                expected.getConfig().getProduct().getStatus().toLowerCase(), "Verify for Status");
        AppAssert.assertEqual(actual.getConfig().getProduct().getErrorUrl(),
                expected.getConfig().getProduct().getErrorUrl(), "Verify for error url");
        AppAssert.assertEqual(actual.getConfig().getProduct().getCallbackUrl(),
                expected.getConfig().getProduct().getCallbackUrl(), "Verify for Call back URL");
        AppAssert.assertEqual(actual.getConfig().getProduct().getConsentCancelUrl(),
                expected.getConfig().getProduct().getConsentCancelUrl(), "Verify for consent cancel url");
        AppAssert.assertEqual(actual.getConfig().getProduct().getUrl(), expected.getConfig().getProduct().getUrl(),
                "Verify for url");
        AppAssert.assertEqual(actual.getConfig().getPricePoints().get(0).getDescription(),
                expected.getConfig().getPricePoints().get(0).getDescription(), "Verify for Description");
        AppAssert.assertEqual(actual.getConfig().getProductCountryMapping().getProductName(),
                expected.getConfig().getProductCountryMapping().getProductName(),
                "Verify for Product Name in product country mapping");
        AppAssert.assertEqual(actual.getConfig().getProductPartnerMappings().get(0).getProductName(),
                expected.getConfig().getProductPartnerMappings().get(0).getProductName(),
                "Verify for Product Name in product partner mapping");

    }

    public static void validateUpdateResponse(ConfigResponseVO actual, ConfigResponseVO expected) {
        AppAssert.assertEqual(actual.getMessage(), expected.getMessage(), "Verification for message");
        AppAssert.assertEqual(actual.getResponseCode(), expected.getResponseCode(), "Verification for respose code");
        AppAssert.assertEqual(actual.getSuccessful(), expected.getSuccessful(), "Verification for respose code");

    }
}
