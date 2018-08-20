package com.vuclip.premiumengg.automation.subscription_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.subscription_service.common.models.BlockStatusResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionUBData;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class SubscriptionBlockTests {

    private static Logger logger = Log4J.getLogger(SubscriptionBlockTests.class.toGenericString());
    Map<String, String> query = new HashMap<String, String>();

    @DataProvider(name = "subscriptionBlockTestDataActivated")
    public Iterator<Object[]> subscriptionBlockTestDataActivated() {

        ArrayList<SubscriptionUBData> subscriptionBlockTestData = new ArrayList<SubscriptionUBData>();
        subscriptionBlockTestData.add(SubscriptionUBData.ACTIVATEDBLACKLISTWOUSERID);
        subscriptionBlockTestData.add(SubscriptionUBData.ACTIVATEDBLACKLIST);
        subscriptionBlockTestData.add(SubscriptionUBData.ACTIVATEDBLACKLISTWOMSISDN);

        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (SubscriptionUBData sbData : subscriptionBlockTestData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    /**
     * Subscription Block Tests for Blacklisting/Cooldown User Test
     */

    @Test(dataProvider = "subscriptionBlockTestDataActivated", groups = {"positive"}, priority = 1)
    public void subscriptionBlockTest(SubscriptionUBData SubscriptionUBData) throws Exception {

        logger.info("Starting ================> Subscription Block Tests for BlackList and Cooldown=====>"
                + SubscriptionUBData.toString());

        String user_sub_auth_key = TestDataCreator.subscriptionBlockTestForActivatedUser(SubscriptionUBData);
        String blockedUSerKey;

        logger.info("Check Key Present in Redis");
        AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));

        Response response = SHelper.subscriptionBlock(SUtils.generateSubscriptionBlockValues(
                SubscriptionUBData.getUser_id(), SubscriptionUBData.getPartner_id(), SubscriptionUBData.getProduct_id(),
                SubscriptionUBData.getCountry(), SubscriptionUBData.getMsisdn()));
        SValidationHelper.validate_ss_api_response(response);
        SValidationHelper.validate_ss_jsonBody(response, "blockedUserData.blockType",
                SubscriptionUBData.getBlockType());

        if (SubscriptionUBData.getUser_id() != "") {
            AppAssert.assertEqual(
                    SDBHelper.validateDates("blocked_user", "user_id=" + SubscriptionUBData.getUser_id(), "start_date",
                            "end_date"),
                    SDBHelper.getValidityDaysFromProductPartner(SubscriptionUBData.getValidityType()),
                    SubscriptionUBData.getValidityType() + " days is added to the required user");

            blockedUSerKey = "USERID_" + SubscriptionUBData.getProduct_id() + "_" + SubscriptionUBData.getPartner_id()
                    + "_" + SubscriptionUBData.getCountry() + "_" + SubscriptionUBData.getUser_id();
        } else {
            AppAssert.assertEqual(
                    SDBHelper.validateDates("blocked_user", "msisdn=" + SubscriptionUBData.getMsisdn(), "start_date",
                            "end_date"),
                    SDBHelper.getValidityDaysFromProductPartner(SubscriptionUBData.getValidityType()),
                    SubscriptionUBData.getValidityType() + " days is added to the required user");

            blockedUSerKey = "USERID_" + SubscriptionUBData.getProduct_id() + "_" + SubscriptionUBData.getPartner_id()
                    + "_" + SubscriptionUBData.getCountry() + "_" + SubscriptionUBData.getMsisdn();
        }

        logger.info("Check Key Present in Redis for Blocked User");
        AppAssert.assertTrue(SRedisUtils.checkKey(blockedUSerKey));

        logger.info("Validate Blacklist User Status");
        query.put("userId", SubscriptionUBData.getUser_id());
        query.put("msisdn", SubscriptionUBData.getMsisdn());
        query.put("productId", String.valueOf(SubscriptionUBData.getProduct_id()));
        query.put("partnerId", String.valueOf(SubscriptionUBData.getPartner_id()));
        query.put("country", SubscriptionUBData.getCountry());
        Response blockStatusResponse = SHelper.subscriptionBlockStatus(query);
        BlockStatusResponse blockStatus = blockStatusResponse.getBody().as(BlockStatusResponse.class);

        AppAssert.assertEqual(
                (int) DateUtils.getDaysBetweenJavaDates(
                        DateUtils.convertLongToStringDate(blockStatus.getBlockedUserData().getStartDate()).toString(),
                        DateUtils.convertLongToStringDate(blockStatus.getBlockedUserData().getEndDate()).toString()),
                SDBHelper.getValidityDaysFromProductPartner(SubscriptionUBData.getValidityType()),
                "Verify Blocked User End Date is updated to today");
        AppAssert.assertEqual(blockStatus.getBlockSummary(), SubscriptionUBData.getBlockListSummary());
    }

    @DataProvider(name = "subscriptionBlockTestDataDeactivated")
    public Iterator<Object[]> subscriptionBlockTestDataDeactivated() {

        ArrayList<SubscriptionUBData> subscriptionBlockTestData = new ArrayList<SubscriptionUBData>();
        subscriptionBlockTestData.add(SubscriptionUBData.DEACTIVATEDBLACKLIST);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (SubscriptionUBData sbData : subscriptionBlockTestData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    /**
     * Subscription Block Tests for Blacklisting/Cooldown User Test
     */

    @Test(dataProvider = "subscriptionBlockTestDataDeactivated", groups = {"positive"}, priority = 2)
    public void subscriptionBlockTestDeactivation(SubscriptionUBData SubscriptionUBData) throws Exception {

        logger.info("Starting ================> Subscription Block Tests for Deactivated Blacklist===>"
                + SubscriptionUBData.toString());

        String user_sub_auth_key = TestDataCreator.subscriptionBlockTestForActivatedUser(SubscriptionUBData);

        logger.info("Check Key Present in Redis");
        AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));

        Response response = SHelper.subscriptionBlock(SUtils.generateSubscriptionBlockValues(
                SubscriptionUBData.getUser_id(), SubscriptionUBData.getPartner_id(), SubscriptionUBData.getProduct_id(),
                SubscriptionUBData.getCountry(), SubscriptionUBData.getMsisdn()));
        SValidationHelper.validate_ss_api_response(response);
        SValidationHelper.validate_ss_jsonBody(response, "blockedUserData.blockType",
                SubscriptionUBData.getBlockType());

        AppAssert.assertEqual(
                SDBHelper.validateDates("blocked_user", "user_id=" + SubscriptionUBData.getUser_id(), "start_date",
                        "end_date"),
                SDBHelper.getValidityDaysFromProductPartner(SubscriptionUBData.getValidityType()),
                SubscriptionUBData.getValidityType() + " days is added to the required user");

        logger.info("Check Key Present in Redis for Blocked User");
        String blockedUSerKey = "USERID_" + SubscriptionUBData.getProduct_id() + "_"
                + SubscriptionUBData.getPartner_id() + "_" + SubscriptionUBData.getCountry() + "_"
                + SubscriptionUBData.getUser_id();
        AppAssert.assertTrue(SRedisUtils.checkKey(blockedUSerKey));

        logger.info("Validate Blacklist User Status");
        query.put("userId", SubscriptionUBData.getUser_id());
        query.put("productId", String.valueOf(SubscriptionUBData.getProduct_id()));
        query.put("partnerId", String.valueOf(SubscriptionUBData.getPartner_id()));
        query.put("country", SubscriptionUBData.getCountry());
        Response blockStatusResponse = SHelper.subscriptionBlockStatus(query);
        BlockStatusResponse blockStatus = blockStatusResponse.getBody().as(BlockStatusResponse.class);

        AppAssert.assertEqual(
                (int) DateUtils.getDaysBetweenJavaDates(
                        DateUtils.convertLongToStringDate(blockStatus.getBlockedUserData().getStartDate()).toString(),
                        DateUtils.convertLongToStringDate(blockStatus.getBlockedUserData().getEndDate()).toString()),
                SDBHelper.getValidityDaysFromProductPartner(SubscriptionUBData.getValidityType()),
                "Verify Blocked User End Date is updated to today");
        AppAssert.assertEqual(blockStatus.getBlockSummary(), SubscriptionUBData.getBlockListSummary());

    }

    /**
     * Subscription Block Tests for Blacklisting User with userID as blank
     */

    @DataProvider(name = "subscriptionBlacklistNegativeTestData")
    public Iterator<Object[]> subscriptionBlacklistNegativeTestData() {

        ArrayList<SubscriptionUBData> subscriptionBlockTestData = new ArrayList<SubscriptionUBData>();
        subscriptionBlockTestData.add(SubscriptionUBData.ACTIVATEDBLACKLISTNEGATIVE);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (SubscriptionUBData sbData : subscriptionBlockTestData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "subscriptionBlacklistNegativeTestData", groups = {"positive", "bug"}, priority = 3)
    public void userSubscriptionBlacklistNegativeTest(SubscriptionUBData subscriptionBlockData) throws Exception {

        logger.info("Starting ================> Subscription Block Tests for Blacklist Negative Test====>"
                + subscriptionBlockData.toString());
        Response response = null;

        String user_sub_auth_key = TestDataCreator.subscriptionBlockTestForActivatedUser(subscriptionBlockData);
        logger.info("Check Key Present in Redis");
        AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));
        response = SHelper
                .subscriptionBlock(SUtils.generateSubscriptionBlockValues("", subscriptionBlockData.getPartner_id(),
                        subscriptionBlockData.getProduct_id(), subscriptionBlockData.getCountry(), ""));
        response.then().assertThat().statusCode(400);
        Long actualDate = subscriptionBlockData.getEnd_date();
        Long expectedDate = (Long) SDBHelper.getColumnValueUsingUserId("blocked_user", "end_date",
                "user_id=" + subscriptionBlockData.getUser_id());

        AppAssert
                .assertEqual(
                        (int) DateUtils.getDaysBetweenJavaDates(DateUtils.convertLongToStringDate(actualDate),
                                DateUtils.convertLongToStringDate(expectedDate)),
                        0, "Verify Other User is not affected");
    }

    @DataProvider(name = "subscriptionBlacklistNewTestData")
    public Iterator<Object[]> subscriptionBlacklistNewTestData() {

        ArrayList<SubscriptionUBData> subscriptionBlockTestData = new ArrayList<SubscriptionUBData>();
        subscriptionBlockTestData.add(SubscriptionUBData.ACTIVATEDBLACKLISTNEWUSER);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (SubscriptionUBData sbData : subscriptionBlockTestData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    /**
     * Subscription Block Tests for Blacklisting a new User Test
     */
    @Test(dataProvider = "subscriptionBlacklistNewTestData", groups = {"positive"}, priority = 4)
    public void subscriptionBlockNewUserTest(SubscriptionUBData subscriptionBlockData) throws Exception {

        logger.info("Starting ================> Subscription Block Tests For New User====>"
                + subscriptionBlockData.toString());

        Response response = SHelper
                .subscriptionBlock(SUtils.generateSubscriptionBlockValues(subscriptionBlockData.getUser_id(),
                        SUtils.productId, SUtils.productId, "MY", subscriptionBlockData.getMsisdn()));
        SValidationHelper.validate_ss_api_response(response);
        SValidationHelper.validate_ss_jsonBody(response, "blockedUserData.blockType",
                subscriptionBlockData.getBlockType());

        AppAssert.assertEqual(
                SDBHelper.validateDates("blocked_user", "user_id=" + subscriptionBlockData.getUser_id(), "start_date",
                        "end_date"),
                SDBHelper.getValidityDaysFromProductPartner(subscriptionBlockData.getValidityType()),
                "Validity Days is added to the required user");

        SDBHelper.verifyNoActivityRecordPresent("user_subscription", subscriptionBlockData.getProduct_id(),
                subscriptionBlockData.getProduct_id(), subscriptionBlockData.getUser_id());

        logger.info("Validate Blacklist User Status");
        query.put("userId", subscriptionBlockData.getUser_id());
        query.put("productId", String.valueOf(subscriptionBlockData.getProduct_id()));
        query.put("partnerId", String.valueOf(subscriptionBlockData.getPartner_id()));
        query.put("country", subscriptionBlockData.getCountry());
        Response blockStatusResponse = SHelper.subscriptionBlockStatus(query);
        BlockStatusResponse blockStatus = blockStatusResponse.getBody().as(BlockStatusResponse.class);

        AppAssert.assertEqual(
                (int) DateUtils.getDaysBetweenJavaDates(
                        DateUtils.convertLongToStringDate(blockStatus.getBlockedUserData().getStartDate()).toString(),
                        DateUtils.convertLongToStringDate(blockStatus.getBlockedUserData().getEndDate()).toString()),
                SDBHelper.getValidityDaysFromProductPartner(subscriptionBlockData.getValidityType()),
                "Verify Blocked User End Date is updated to today");
        AppAssert.assertEqual(blockStatus.getBlockSummary(), subscriptionBlockData.getBlockListSummary());
    }

    @BeforeClass(alwaysRun = true)
    public void cleanUp() {
        DBUtils.cleanTable("user_subscription",
                "product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);
        DBUtils.cleanTable("blocked_user", "product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);

    }

}
