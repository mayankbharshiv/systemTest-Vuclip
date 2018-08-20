package com.vuclip.premiumengg.automation.configuration_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerRequestVO;
import com.vuclip.premiumengg.automation.configuration_service.common.models.PartnerResponseVO;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PartnerValidationHelper {
    private static Logger logger = Log4J.getLogger("PartnerValidationHelper");

    public static void validatePartnerInDB(PartnerRequestVO request) throws SQLException {

        logger.info("Verify Partner Updated in Database");
        Map<String, Object> expectedRecords = new HashMap<String, Object>();

        expectedRecords.put("has_mo_activations", request.isHasMoActivations());
        expectedRecords.put("has_mo_deactivations", request.isHasMoDeactivations());
        expectedRecords.put("is_auto_renewal_applicable", request.isAutoRenewalApplicable());
        expectedRecords.put("is_balance_check_required", request.isBalanceCheckRequired());
        expectedRecords.put("step_up_charging", request.isStepUpCharging());
        expectedRecords.put("user_identifier", request.getUserIdentifier());
        expectedRecords.put("type", request.getType());
        expectedRecords.put("activation_managed_by", request.getActivationManagedBy());
        expectedRecords.put("deactivation_managed_by", request.getDeactivationManagedBy());
        expectedRecords.put("description", request.getDescription());
        expectedRecords.put("partner_activation_consent_initiation_url",
                request.getPartnerActivationConsentInitiationUrl());
        expectedRecords.put("partner_name", request.getPartnerName());
        expectedRecords.put("renewal_managed_by", request.getRenewalManagedBy());
        expectedRecords.put("status", request.getStatus());
        expectedRecords.put("partner_activation_consent_initiation_url",
                request.getPartnerActivationConsentInitiationUrl());
        expectedRecords.put("partner_activation_consent_parser_url", request.getPartnerActivationConsentParserUrl());
        expectedRecords.put("partner_deactivation_consent_initiation_url",
                request.getPartnerDeactivationConsentInitiationUrl());
        expectedRecords.put("partner_deactivation_consent_parser_url",
                request.getPartnerDeactivationConsentParserUrl());

        CSValidationHelper.validateTableRecord(
                DBUtils.getRecord("partner", "partner_name = '" + request.getPartnerName() + "'"), expectedRecords);
    }

    public static void validateResponse(PartnerResponseVO actual, PartnerResponseVO expected) {
        if (actual.getPartner().getOperationType() != null) {

            AppAssert.assertEqual(actual.getMessage(), expected.getMessage(), "Verification for message");
            AppAssert.assertEqual(actual.getResponseCode(), expected.getResponseCode(),
                    "verification for respose code");
            AppAssert.assertEqual(actual.getSuccessful(), expected.getSuccessful());
            AppAssert.assertEqual(actual.getPartner().getPartnerName(), expected.getPartner().getPartnerName());
            AppAssert.assertEqual(actual.getPartner().getDescription(), expected.getPartner().getDescription());
            AppAssert.assertEqual(actual.getPartner().getStatus(), expected.getPartner().getStatus());
            AppAssert.assertEqual(actual.getPartner().getAutoRenewalApplicable(),
                    expected.getPartner().getAutoRenewalApplicable());
            AppAssert.assertEqual(actual.getPartner().getType(), expected.getPartner().getType());
            AppAssert.assertEqual(actual.getPartner().getStepUpCharging(), expected.getPartner().getStepUpCharging());
            AppAssert.assertEqual(actual.getPartner().getUserIdentifier(), expected.getPartner().getUserIdentifier());
            AppAssert.assertEqual(actual.getPartner().getBalanceCheckRequired(),
                    expected.getPartner().getBalanceCheckRequired());
            AppAssert.assertEqual(actual.getPartner().getActivationManagedBy(),
                    expected.getPartner().getActivationManagedBy());
            AppAssert.assertEqual(actual.getPartner().getHasMoDeactivations(),
                    expected.getPartner().getHasMoDeactivations());
            AppAssert.assertEqual(actual.getPartner().getOperationType(), expected.getPartner().getOperationType());

        } else {
            AppAssert.assertEqual(actual.getMessage(), expected.getMessage(), "Verification for message");
            AppAssert.assertEqual(actual.getResponseCode(), expected.getResponseCode(),
                    "verification for respose code");
            AppAssert.assertEqual(actual.getSuccessful(), expected.getSuccessful());
            AppAssert.assertEqual(actual.getPartner().getPartnerName(), expected.getPartner().getPartnerName());
            AppAssert.assertEqual(actual.getPartner().getDescription(), expected.getPartner().getDescription());
            AppAssert.assertEqual(actual.getPartner().getStatus(), expected.getPartner().getStatus());
            AppAssert.assertEqual(actual.getPartner().getAutoRenewalApplicable(),
                    expected.getPartner().getAutoRenewalApplicable());
            AppAssert.assertEqual(actual.getPartner().getType(), expected.getPartner().getType());
            AppAssert.assertEqual(actual.getPartner().getStepUpCharging(), expected.getPartner().getStepUpCharging());
            AppAssert.assertEqual(actual.getPartner().getUserIdentifier(), expected.getPartner().getUserIdentifier());
            AppAssert.assertEqual(actual.getPartner().getBalanceCheckRequired(),
                    expected.getPartner().getBalanceCheckRequired());
            AppAssert.assertEqual(actual.getPartner().getActivationManagedBy(),
                    expected.getPartner().getActivationManagedBy());
            AppAssert.assertEqual(actual.getPartner().getHasMoDeactivations(),
                    expected.getPartner().getHasMoDeactivations());
        }

    }

}
