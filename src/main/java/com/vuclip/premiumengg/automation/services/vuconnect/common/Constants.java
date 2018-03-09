package com.vuclip.premiumengg.automation.services.vuconnect.common;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;


public class Constants {

    public static Set DOUBLE_CHARGING_CHECK_REQUIRED_REQUEST_TYPES = Sets.newHashSet(RequestTypeEnum.ACTIVATION,
            RequestTypeEnum.CONFIRM,
            RequestTypeEnum.RENEWAL,
            RequestTypeEnum.RETRY,
            RequestTypeEnum.OTP_VERIFY);
    //Activity Result - Success Values
    public static Set SUCCESS_ACTIVITY_RESULT_SET = Sets.newHashSet(ChargingActivityResultEnum.Success_Free_Trial,
            ChargingActivityResultEnum.Success,
            ChargingActivityResultEnum.Success_Async);
    Set DEACTIVATION_REQUEST_TYPES = Sets.newHashSet(RequestTypeEnum.DEACTIVATION,
            RequestTypeEnum.AUTO_DEACTIVATE);

    public enum RequestTypeEnum {

        ACTIVATION(1),
        RENEWAL(2),
        DEACTIVATION(3),
        RETRY(4),
        FALLBACK(5),
        AUTO_DEACTIVATE(6),
        BASE_RECON(7),
        OTP_GEN(8),
        OTP_VERIFY(9),
        OTP_REGEN(10),
        CONFIRM(11);

        int requestTypeId;

        private RequestTypeEnum(int requestTypeId) {
            this.requestTypeId = requestTypeId;
        }


        public static RequestTypeEnum fromValue(int value) {
            for (RequestTypeEnum requestTypeEnum : RequestTypeEnum.values()) {
                if (requestTypeEnum.requestTypeId == value) {
                    return requestTypeEnum;
                }
            }
            throw new IllegalArgumentException(String.valueOf(value));
        }


    }


    public enum ChargingActivityResultEnum {


        Success("Success"),
        Failure("Failure"),
        Validity_Expired("Validity_Expired"),
        Low_Balance("Low_Balance"),
        Error("Error"),
        Error_Validity_Expired("Error_Validity_Expired"),
        Low_Balance_Fallback("Low_Balance_Fallback"),
        Force_Deactivate("Force_Deactivate"),
        Success_Free_Trial("Success_Free_Trial"),
        Success_Async("Success_Async"),
        Invalid_Dn("Invalid_Dn"),
        Auto_Deactivate("Auto_Deactivate"),
        Act_Low_Balance("Act_Low_Balance"),
        Ren_Low_Balance("Ren_Low_Balance"),
        Act_Error("Act_Error"),
        Dct_Error("Dct_Error"),
        Deactivation_Success("Deactivation_Success"),
        No_Action("No_Action"),
        Act_Failure("Act_Failure"),
        Cg_No_Action("Cg_No_Action"),
        Max_Attempts_Reached("Max_Attempts_Reached"),

        //Adding the following 2 new values "Carrier_Timeout" and "Internal_Failure" after discussion with Chirag
        Carrier_Timeout("Carrier_Timeout"),
        Internal_Failure("Internal_Failure");

        private String value;


        ChargingActivityResultEnum(String value) {
            this.value = value;
        }

        public static ChargingActivityResultEnum fromValue(String v) {
            for (ChargingActivityResultEnum activityResultEnum : ChargingActivityResultEnum.values()) {
                if (StringUtils.equalsIgnoreCase(activityResultEnum.value, v)) {
                    return activityResultEnum;
                }
            }
            throw new IllegalArgumentException(String.valueOf(v));
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }


    }


}
