package com.vuclip.premiumengg.automation.subscription_service.common.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StateTransitionData {

	ACTIVATION_LOWBALANCE(0, "MY", "D_KIM_87348", "WAP", 69232L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "1111111122", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACT_INIT", 30, "1111111122", "ACTIVE", 4, 1, 7, "CHARGING", "ACTIVATION", "LOW_BALANCE", true,
			"SUCCESS", "PARKING", "end_date", "start_date", "subscription_status", "parking_validity"),

	ACTIVATION_FAILURE(0, "MY", "D_KIM_87348", "WAP", 69226L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1544034600000L, "1111111116", 1535520606000L, 0, 8181, 8181, 1543602600000L, null, 0, "BC03",
			"ACT_INIT", 30, "1111111116", "ACTIVATION_IN_PROGRESS", 4, 1, 6, "CHARGING", "ACTIVATION", "FAILURE", true,
			"SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status", "act_init_validity"),

	ACTIVATION_ERROR(0, "MY", "D_KIM_87348", "WAP", 69227L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1111111117", 1535520606000L, 0, 8181, 8181, 1929724200000L, null, 0, "BC03",
			"ACT_INIT", 30, "1111111117", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "CHARGING", "ACTIVATION", "ERROR", true,
			"SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status", "act_init_validity"),

	ACTIVATION_SUCESS(0, "MY", "D_KIM_87348", "WAP", 69225L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "1111111115", 1535520606000L, 0, 8181, 8181, 1929724200000L, null, 0, "BC03",
			"ACT_INIT", 30, "1111111115", "ACTIVE", 4, 1, 5, "CHARGING", "ACTIVATION", "SUCCESS", true, "SUCCESS",
			"ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	ACTIVATION_CONSENT_OPEN(0, "MY", "D_KIM_87348", "WAP", 69225L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "1111111115", 1935520606000L, 0, 8181, 8181, 1835520606000L, null, 0,
			"BC03", "ACT_INIT", 30, "1111111115", "ACTIVE", 4, 1, 5, "CONSENT", "ACTIVATION", "CONFIRMED", true,
			"SUCCESS", "ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	ACTIVATION_CONSENT_CLOSE(0, "MY", "D_KIM_87348", "WAP", 69225L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "1111111115", 1935520606000L, 0, 8181, 8181, 1835520606000L, null, 0,
			"BC03", "ACT_INIT", 30, "1111111115", "ACTIVE", 4, 1, 5, "CONSENT", "ACTIVATION", "CONFIRMED", false,
			"SUCCESS", "ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	ACTIVATION_PARKING_NOVALIDITY(0, "MY", "D_KIM_87348", "WAP", 69234L, "2018-06-29 11:47:54", 1530253086000L, 0,
			"BC01", "BC01", 0, null, 0.0, 1930253086000L, "1111111124", 1544077086000L, 0, 8181, 8181, 1544077086000L,
			null, 0, "BC01", "ACT_INIT", 30, "1111111124", "ACTIVE_NO_VALIDITY", 4, 1, 7, "CHARGING", "ACTIVATION",
			"LOW_BALANCE", true, "SUCCESS", "PARKING", "end_date", "start_date", "subscription_status",
			"parking_validity"),

	ACTIVATION_REGISTRATION_CLOSED(0, "MY", "D_KIM_87348", "WAP", 69228L, "2018-06-29 11:47:54", 1530253086000L, 0,
			"BC03", "BC03", 0, null, 0.0, 1544077086000L, "1111111118", 1545805086000L, 0, 8181, 8181, 1543645086000L,
			null, 0, "BC03", "ACT_INIT", 30, "1111111118", "ACTIVE", 4, 1, 7, "REGISTRATION", "ACTIVATION", "SUCCESS",
			true, "SUCCESS", "ACTIVATED", "end_date", "start_date", "subscription_status", "act_init_validity"),

	ACTIVATION_REGISTRATION_OPEN(0, "MY", "D_KIM_87348", "WAP", 69229L, "2018-06-29 11:47:54", 1530253086000L, 0,
			"BC03", "BC03", 0, null, 0.0, 1544077086000L, "1111111119", 1545805086000L, 0, 8181, 8181, 1543645086000L,
			null, 0, "BC03", "ACT_INIT", 30, "1111111119", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "REGISTRATION",
			"ACTIVATION", "SUCCESS", false, "SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status",
			"act_init_validity"),

	ACTIVATION_REGISTRATION_ERROR(0, "MY", "D_KIM_87348", "WAP", 69230L, "2018-06-29 11:47:54", 1530253086000L, 0,
			"BC03", "BC03", 0, null, 0.0, 1544077086000L, "1111111120", 1545805086000L, 0, 8181, 8181, 1543645086000L,
			null, 0, "BC03", "ACT_INIT", 30, "1111111120", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "REGISTRATION",
			"ACTIVATION", "ERROR", true, "SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status",
			"act_init_validity"),

	ACTIVATION_REGISTRATION_FAILURE(0, "MY", "D_KIM_87348", "WAP", 69231L, "2018-06-29 11:47:54", 1530253086000L, 0,
			"BC03", "BC03", 0, null, 0.0, 1544077086000L, "1111111121", 1545805086000L, 0, 8181, 8181, 1543645086000L,
			null, 0, "BC03", "ACT_INIT", 30, "1111111121", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "REGISTRATION",
			"ACTIVATION", "FAILURE", true, "SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status",
			"act_init_validity"),

	RETRYACTIVATIONSUCESS(0, "MY", "D_KIM_87348", "WAP", 79225L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "7111111115", 1535520606000L, 0, 8181, 8181, 1530253086000L, null, 0,
			"BC03", "ACT_INIT", 30, "7111111115", "ACTIVE", 4, 1, 5, "CHARGING", "ACTIVATION_RETRY", "SUCCESS", true,
			"SUCCESS", "ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	RETRYACTIVATIONFAILURE(0, "MY", "D_KIM_87348", "WAP", 79226L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "7111111116", 1535520606000L, 0, 8181, 8181, 1530253086000L, null, 0,
			"BC03", "ACT_INIT", 30, "7111111116", "ACTIVATION_IN_PROGRESS", 4, 1, 6, "CHARGING", "ACTIVATION_RETRY",
			"FAILURE", true, "SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status",
			"act_init_validity"),

	RETRYACTIVATIONERROR(0, "MY", "D_KIM_87348", "WAP", 79227L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "7111111117", 1535520606000L, 0, 8181, 8181, 1530253086000L, null, 0,
			"BC03", "ACT_INIT", 30, "7111111117", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "CHARGING", "ACTIVATION_RETRY",
			"ERROR", true, "SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status", "act_init_validity"),

	RETRYACTIVATIONREGISTRATION(0, "MY", "D_KIM_87348", "WAP", 79228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1544077086000L, "7111111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACT_INIT", 30, "7111111118", "ACTIVE", 4, 1, 7, "REGISTRATION", "ACTIVATION_RETRY", "SUCCESS",
			true, "SUCCESS", "ACTIVATED", "end_date", "start_date", "subscription_status", "act_init_validity"),

	RETRYACTIVATIONREGISTRATIONOPEN(0, "MY", "D_KIM_87348", "WAP", 79229L, "2018-06-29 11:47:54", 1530253086000L, 0,
			"BC03", "BC03", 0, null, 0.0, 1544077086000L, "7111111119", 1545805086000L, 0, 8181, 8181, 1543645086000L,
			null, 0, "BC03", "ACT_INIT", 30, "7111111119", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "REGISTRATION",
			"ACTIVATION_RETRY", "SUCCESS", false, "SUCCESS", "ACT_INIT", "end_date", "start_date",
			"subscription_status", "act_init_validity"),

	RETRYREGISTRATIONERROR(0, "MY", "D_KIM_87348", "WAP", 79230L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1544077086000L, "7111111120", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACT_INIT", 30, "7111111120", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "REGISTRATION", "ACTIVATION_RETRY",
			"ERROR", true, "SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status", "act_init_validity"),

	RETRYACTIVATIONPARKING(0, "MY", "D_KIM_87348", "WAP", 79232L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "7111111122", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACT_INIT", 30, "7111111122", "ACTIVE", 4, 1, 7, "CHARGING", "ACTIVATION_RETRY", "LOW_BALANCE",
			true, "SUCCESS", "PARKING", "end_date", "start_date", "subscription_status", "parking_validity"),

	RETRYACTIVATIONPARKINGWOVALIDITY(0, "MY", "D_KIM_87348", "WAP", 79234L, "2018-06-29 11:47:54", 1530253086000L, 0,
			"BC01", "BC01", 0, null, 0.0, 1944077086000L, "7111111124", 1544077086000L, 0, 8181, 8181, 1544077086000L,
			null, 0, "BC01", "ACT_INIT", 30, "7111111124", "ACTIVE_NO_VALIDITY", 4, 1, 7, "CHARGING",
			"ACTIVATION_RETRY", "LOW_BALANCE", true, "SUCCESS", "PARKING", "end_date", "start_date",
			"subscription_status", "parking_validity"),

	RETRYREGISTRATIONFAILURE(0, "MY", "D_KIM_87348", "WAP", 79231L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1544077086000L, "7111111121", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACT_INIT", 30, "7111111121", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "REGISTRATION", "ACTIVATION_RETRY",
			"FAILURE", true, "SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status",
			"act_init_validity"),

	WINBACKSUCESS(0, "MY", "D_KIM_87348", "WAP", 74448L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1944077086000L, "8111111115", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"PARKING", 30, "8111111115", "ACTIVE", 4, 1, 5, "CHARGING", "WINBACK", "SUCCESS", true, "SUCCESS",
			"ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	WINBACKLOWBAL(0, "MY", "D_KIM_87348", "WAP", 748728L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1944077086000L, "8111111116", 1530253086000L, 0, 8181, 8181, 1530253086000L, null, 0, "BC03",
			"PARKING", 30, "8111111116", "ACTIVE", 4, 1, 5, "CHARGING", "WINBACK", "LOW_BALANCE", true, "SUCCESS",
			"PARKING", "end_date", "start_date", "subscription_status", "parking_validity"),

	WINBACKLOWERROR(0, "MY", "D_KIM_87348", "WAP", 798728L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1944077086000L, "8111111117", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"PARKING", 30, "8111111117", "ACTIVE", 4, 1, 5, "CHARGING", "WINBACK", "ERROR", true, "SUCCESS", "PARKING",
			"end_date", "start_date", "subscription_status", "parking_validity"),

	RENEWALINITSUCCESS(0, "MY", "D_KIM_87348", "WAP", 99228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1944077086000L, "9111111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"ACT_INIT", 30, "9111111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "SUCCESS", true, "SUCCESS",
			"ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	RENEWALINITLOWB(0, "MY", "D_KIM_87348", "WAP", 99229L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1944077086000L, "9111111119", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"ACT_INIT", 30, "9111111119", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "LOW_BALANCE", true, "SUCCESS",
			"SUSPEND", "end_date", "start_date", "subscription_status", "suspend_validity"),

	RENEWALINITERROR(0, "MY", "D_KIM_87348", "WAP", 99226L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1944077086000L, "9111131117", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"ACT_INIT", 30, "9113111117", "ACTIVATION_IN_PROGRESS", 4, 1, 7, "CHARGING", "RENEWAL", "ERROR", true,
			"SUCCESS", "ACT_INIT", "end_date", "start_date", "subscription_status", "act_init_validity"),

	RENEWALINITFAILURE(0, "MY", "D_KIM_87348", "WAP", 99227L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "9411111117", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"ACT_INIT", 30, "9411111117", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "FAILURE", true, "FAILURE",
			"SUSPEND", "end_date", "start_date", "subscription_status", "suspend_validity"),

	RENEWALACTSUCCESS(0, "MY", "D_KIM_87348", "WAP", 92228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1944077086000L, "9211111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"ACTIVATED", 30, "9211111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "SUCCESS", true, "SUCCESS",
			"ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	RENEWALACTERRSUCCESS(0, "MY", "D_KIM_87348", "WAP", 93228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1944077086000L, "9311111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACTIVATED", 30, "9311111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "ERROR", true, "SUCCESS",
			"ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	RENEWALACTSUSPENDLOWB(0, "MY", "D_KIM_87348", "WAP", 95228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1944077086000L, "9511111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACTIVATED", 30, "9511111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "LOW_BALANCE", true,
			"SUCCESS", "SUSPEND", "end_date", "start_date", "subscription_status", "suspend_validity"),

	RENEWALACTSUSPENDFAIL(0, "MY", "D_KIM_87348", "WAP", 96228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1944077086000L, "9611111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "ACTIVATED", 30, "9611111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "FAILURE", true, "SUCCESS",
			"SUSPEND", "end_date", "start_date", "subscription_status", "suspend_validity"),

	RENEWALSUSPENDFAIL(0, "MY", "D_KIM_87348", "WAP", 97228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1944077086000L, "9711111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"SUSPEND", 30, "9711111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "FAILURE", true, "SUCCESS",
			"SUSPEND", "end_date", "start_date", "subscription_status", "suspend_validity"),

	RENEWALSUSPENDERR(0, "MY", "D_KIM_87348", "WAP", 98228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1944077086000L, "9681111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"SUSPEND", 30, "9681111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "ERROR", true, "SUCCESS", "SUSPEND",
			"end_date", "start_date", "subscription_status", "suspend_validity"),

	RENEWALSUSPENDLOWB(0, "MY", "D_KIM_87348", "WAP", 992289L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1944077086000L, "9911111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"SUSPEND", 30, "9911111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "LOW_BALANCE", true, "SUCCESS",
			"SUSPEND", "end_date", "start_date", "subscription_status", "suspend_validity"),

	RENEWALSUSSUCCESS(0, "MY", "D_KIM_87348", "WAP", 94228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1944077086000L, "9411111118", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"SUSPEND", 30, "9411111118", "ACTIVE", 4, 1, 7, "CHARGING", "RENEWAL", "SUCCESS", true, "SUCCESS",
			"ACTIVATED", "end_date", "start_date", "subscription_status", "validity"),

	DEACTIVATIONRETRYERR(0, "MY", "D_KIM_87348", "WAP", 9090L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1544077086000L, "9421111117", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0, "BC03",
			"DCT_INIT", 30, "9421111117", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE",
			"DEACTIVATION_RETRY", "ERROR", true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONRETRYFAIL(0, "MY", "D_KIM_87348", "WAP", 9091L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1544077086000L, "9421111111", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "DCT_INIT", 30, "9421111111", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE",
			"DEACTIVATION_RETRY", "FAILURE", true, "SUCCESS", "DCT_INIT", "end_date", "start_date",
			"subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONRETRYSUCCESS(0, "MY", "D_KIM_87348", "WAP", 9092L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1544077086000L, "9421111112", 1545805086000L, 0, 8181, 8181, 1543645086000L, null, 0,
			"BC03", "DCT_INIT", 30, "9421111112", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE",
			"DEACTIVATION_RETRY", "SUCCESS", true, "SUCCESS", "DEACTIVATED", "end_date", "start_date",
			"subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONRETRYPROCESSBLACKLISTSUCCESS(0, "MY", "D_KIM_87348", "WAP", 90920L, "2018-06-29 11:47:54",
			1530253086000L, 0, "BC03", "BC03", 0, null, 0.0, 1544077086000L, "9421221112", 1545805086000L, 0, 8181,
			8181, 1543645086000L, null, 0, "BC03", "DCT_INIT", 30, "9421221112", "INACTIVE_WITH_VALIDITY", 4, 1, 7,
			"PROCESS_BLACKLIST", "DEACTIVATION_RETRY", "SUCCESS", true, "SUCCESS", "DEACTIVATED", "end_date",
			"start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONRETRYPROCESSBLACKLISTFAIL(0, "MY", "D_KIM_87348", "WAP", 90910L, "2018-06-29 11:47:54", 1530253086000L,
			0, "BC03", "BC03", 0, null, 0.0, 1544077086000L, "9421331112", 1545805086000L, 0, 8181, 8181,
			1543645086000L, null, 0, "BC03", "DCT_INIT", 30, "9421331112", "DEACTIVATION_IN_PROGRESS", 4, 1, 7,
			"PROCESS_BLACKLIST", "DEACTIVATION_RETRY", "FAILURE", true, "SUCCESS", "DCT_INIT", "end_date", "start_date",
			"subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONRETRYPROCESSBLACKLISTERR(0, "MY", "D_KIM_87348", "WAP", 909122L, "2018-06-29 11:47:54", 1530253086000L,
			0, "BC03", "BC03", 0, null, 0.0, 1544077086000L, "9421441112", 1545805086000L, 0, 8181, 8181,
			1543645086000L, null, 0, "BC03", "DCT_INIT", 30, "9421441112", "DEACTIVATION_IN_PROGRESS", 4, 1, 7,
			"PROCESS_BLACKLIST", "DEACTIVATION_RETRY", "ERROR", true, "SUCCESS", "DCT_INIT", "end_date", "start_date",
			"subscription_status", "deactivation_retry_validity"),

	CHURNINITSUCCESS(0, "MY", "D_KIM_87348", "WAP", 1237L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567893", 1530253086000L, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACT_INIT", 30, "1234567893", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "SUCCESS", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	CHURNINITFAIL(0, "MY", "D_KIM_87348", "WAP", 1234L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567890", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "ACT_INIT",
			30, "1234567890", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "FAILURE",
			true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNINITNPROGRESS(0, "MY", "D_KIM_87348", "WAP", 1236L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "1234567892", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACT_INIT", 30, "1234567892", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN",
			"IN_PROGRESS", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNINITERR(0, "MY", "D_KIM_87348", "WAP", 1235L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567891", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "ACT_INIT",
			30, "1234567891", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "ERROR", true,
			"SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	CHURNSUSSUCCESS(0, "MY", "D_KIM_87348", "WAP", 1238L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567894", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "SUSPEND",
			30, "1234567894", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "SUCCESS", true, "SUCCESS",
			"DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	CHURNSUSFAIL(0, "MY", "D_KIM_87348", "WAP", 1239L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567895", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "SUSPEND",
			30, "1234567895", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "FAILURE",
			true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNSUSNPROGRESS(0, "MY", "D_KIM_87348", "WAP", 1240L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567896", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "SUSPEND",
			30, "1234567896", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "IN_PROGRESS",
			false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNSUSERR(0, "MY", "D_KIM_87348", "WAP", 1241L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0, null,
			0.0, 1930253086000L, "1234567897", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "SUSPEND", 30,
			"1234567897", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "ERROR", true,
			"SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	CHURNPRKSUCCESS(0, "MY", "D_KIM_87348", "WAP", 1242L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567814", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "PARKING",
			30, "1234567814", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "SUCCESS", true, "SUCCESS",
			"DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	CHURNPRKFAIL(0, "MY", "D_KIM_87348", "WAP", 1243L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567815", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "PARKING",
			30, "1234567815", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "FAILURE",
			true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNPRKINPROGRESS(0, "MY", "D_KIM_87348", "WAP", 1244L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "1234567816", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "PARKING",
			30, "1234567816", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "IN_PROGRESS",
			false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNPRKERR(0, "MY", "D_KIM_87348", "WAP", 1245L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0, null,
			0.0, 1930253086000L, "1234567817", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "PARKING", 30,
			"1234567817", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "ERROR", true,
			"SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	CHURNACTSUCCESS(0, "MY", "D_KIM_87348", "WAP", 1228L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567824", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "ACTIVATED",
			30, "1234567824", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "SUCCESS", true, "SUCCESS",
			"DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	CHURNACTFAIL(0, "MY", "D_KIM_87348", "WAP", 1229L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567825", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "ACTIVATED",
			30, "1234567825", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "FAILURE",
			true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNACTNPROGRESS(0, "MY", "D_KIM_87348", "WAP", 1220L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0,
			null, 0.0, 1930253086000L, "1234567826", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "ACTIVATED",
			30, "1234567826", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "IN_PROGRESS",
			false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	CHURNACTERR(0, "MY", "D_KIM_87348", "WAP", 1221L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03", 0, null,
			0.0, 1930253086000L, "1234567827", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "ACT_INIT", 30,
			"1234567827", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "SYSTEM_CHURN", "ERROR", true,
			"SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONINITCNFFALSE(0, "MY", "D_KIM_87348", "WAP", 2230L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "2234567890", 1530253086000L, 0, 8181, 8181, 1929810600000L, null, 0,
			"BC03", "ACT_INIT", 30, "1234567893", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "DEACTIVATE_CONSENT",
			"DEACTIVATION", "CONFIRMED", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONINITCNFTRUE(0, "MY", "D_KIM_87348", "WAP", 2231L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "2234567891", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACT_INIT", 30, "2234567891", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "DEACTIVATE_CONSENT", "DEACTIVATION", "CONFIRMED", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONINITSUCCESS(0, "MY", "D_KIM_87348", "WAP", 2233L, "2018-06-29 11:47:54", 1930253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "2234567892", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACT_INIT", 30, "2234567892", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "SUCCESS", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONINITPROGRESS(0, "MY", "D_KIM_87348", "WAP", 2234L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "2234567893", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACT_INIT", 30, "2234567893", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"IN_PROGRESS", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONINITFAIL(0, "MY", "D_KIM_87348", "WAP", 2235L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "2234567894", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACT_INIT", 30, "2234567894", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"FAILURE", true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONINITERR(0, "MY", "D_KIM_87348", "WAP", 2236L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "2234567895", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACT_INIT", 30, "2234567895", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"ERROR", true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONSUSCNFFALSE(0, "MY", "D_KIM_87348", "WAP", 3230L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "3234567890", 1530253086000L, 0, 8181, 8181, 1929810600000L, null, 0,
			"BC03", "SUSPEND", 30, "3234567893", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "DEACTIVATE_CONSENT",
			"DEACTIVATION", "CONFIRMED", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONSUSCNFTRUE(0, "MY", "D_KIM_87348", "WAP", 3231L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "3234567891", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"SUSPEND", 30, "3234567891", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "DEACTIVATE_CONSENT", "DEACTIVATION", "CONFIRMED", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONSUSSUCCESS(0, "MY", "D_KIM_87348", "WAP", 3233L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "3234567892", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"SUSPEND", 30, "3234567892", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "SUCCESS", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONSUSPROGRESS(0, "MY", "D_KIM_87348", "WAP", 3234L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "32345678931", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"SUSPEND", 30, "32345678931", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"IN_PROGRESS", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONSUSFAIL(0, "MY", "D_KIM_87348", "WAP", 3235L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "3234567894", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "SUSPEND",
			30, "3234567894", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "FAILURE",
			true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONSUSERR(0, "MY", "D_KIM_87348", "WAP", 3236L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "3234567895", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "SUSPEND",
			30, "3234567895", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "ERROR", true,
			"SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONPRKCNFFALSE(0, "MY", "D_KIM_87348", "WAP", 4230L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "4234567890", 1530253086000L, 0, 8181, 8181, 1929810600000L, null, 0,
			"BC03", "PARKING", 30, "4234567893", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "DEACTIVATE_CONSENT",
			"DEACTIVATION", "CONFIRMED", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONPRKCNFTRUE(0, "MY", "D_KIM_87348", "WAP", 4231L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "4234567891", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"PARKING", 30, "4234567891", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "DEACTIVATE_CONSENT", "DEACTIVATION", "CONFIRMED", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONPRKSUCCESS(0, "MY", "D_KIM_87348", "WAP", 4233L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "4234567892", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"PARKING", 30, "4234567892", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "SUCCESS", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONPRKPROGRESS(0, "MY", "D_KIM_87348", "WAP", 4234L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "42345678932", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"PARKING", 30, "42345678932", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"IN_PROGRESS", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONPRKFAIL(0, "MY", "D_KIM_87348", "WAP", 4235L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "4234567894", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "PARKING",
			30, "4234567894", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "FAILURE",
			true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONPRKERR(0, "MY", "D_KIM_87348", "WAP", 4236L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "4234567895", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03", "PARKING",
			30, "4234567895", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "ERROR", true,
			"SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONACTCNFFALSE(0, "MY", "D_KIM_87348", "WAP", 5230L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "5234567890", 1530253086000L, 0, 8181, 8181, 1929810600000L, null, 0,
			"BC03", "ACTIVATED", 30, "5234567893", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "DEACTIVATE_CONSENT",
			"DEACTIVATION", "CONFIRMED", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONACTCNFTRUE(0, "MY", "D_KIM_87348", "WAP", 5231L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "5234567891", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACTIVATED", 30, "5234567891", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "DEACTIVATE_CONSENT", "DEACTIVATION", "CONFIRMED", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONACTSUCCESS(0, "MY", "D_KIM_87348", "WAP", 5233L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "5234567892", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACTIVATED", 30, "5234567892", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "SUCCESS", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONACTPROGRESS(0, "MY", "D_KIM_87348", "WAP", 5234L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "52345678935", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACTIVATED", 30, "52345678935", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"IN_PROGRESS", false, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONACTFAIL(0, "MY", "D_KIM_87348", "WAP", 5235L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "5234567894", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACTIVATED", 30, "5234567894", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"FAILURE", true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONACTERR(0, "MY", "D_KIM_87348", "WAP", 5236L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "5234567895", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"ACTIVATED", 30, "5234567895", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"ERROR", true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONDNITSUCCESS(0, "MY", "D_KIM_87348", "WAP", 6233L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "6234567892", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"DCT_INIT", 30, "6234567892", "INACTIVE_WITH_VALIDITY", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION", "SUCCESS", true,
			"SUCCESS", "DEACTIVATED", "end_date", "start_date", "subscription_status", "deactivation_retry_validity"),

	DEACTIVATIONDINITFAIL(0, "MY", "D_KIM_87348", "WAP", 6235L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03",
			"BC03", 0, null, 0.0, 1930253086000L, "6234567894", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"DCT_INIT", 30, "6234567894", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"FAILURE", true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity"),

	DEACTIVATIONDINITERR(0, "MY", "D_KIM_87348", "WAP", 6236L, "2018-06-29 11:47:54", 1530253086000L, 0, "BC03", "BC03",
			0, null, 0.0, 1930253086000L, "6234567895", null, 0, 8181, 8181, 1929810600000L, null, 0, "BC03",
			"DCT_INIT", 30, "6234567895", "DEACTIVATION_IN_PROGRESS", 4, 1, 7, "PROCESS_DEACTIVATE", "DEACTIVATION",
			"ERROR", true, "SUCCESS", "DCT_INIT", "end_date", "start_date", "subscription_status",
			"deactivation_retry_validity");

	int renewalAllowed;
	String country;
	String userSource;
	String mode;
	Long subscription_id;
	String created_on;
	Long activation_date;
	int blacklisted;
	String charged_billing_code;
	String fallbackBillingCode;
	int cooldowned;
	Long deactivation_date;
	Double chargedPrice;
	Long end_date;
	String msisdn;
	Long next_billing_date;
	int paid;
	int partner_id;
	int product_id;
	Long start_date;
	String sub_type;
	int subscribable;
	String subscription_billing_code;
	String subscription_status;
	int validity_days;
	String user_id;
	String summary;
	Integer actionId;
	Integer activityId;
	Integer transactionStateId;
	String action_Id;
	String activity_Id;
	String transaction_StateId;
	Boolean closed;
	String actionResult;
	String subscriptionStatusNew;
	String endDateColumn;
	String startUpdateColumn;
	String statusColumn;
	String validityColumn;

}
