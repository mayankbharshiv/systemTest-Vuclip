package com.vuclip.premiumengg.automation.subscription_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.subscription_service.common.models.SubscriptionUBData;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SusbscriptionUnblockTest {

    private static Logger logger = Log4J.getLogger(SusbscriptionUnblockTest.class.toGenericString());

    @DataProvider(name = "subscriptionUnblockTestData")
    public Iterator<Object[]> subscriptionUnblockTestData() {

        ArrayList<SubscriptionUBData> subscriptionUnblockTestData = new ArrayList<SubscriptionUBData>();
        subscriptionUnblockTestData.add(SubscriptionUBData.UNBLOCKBLACKLIST);
        subscriptionUnblockTestData.add(SubscriptionUBData.UNBLOCKCOOLDOWN);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (SubscriptionUBData sbData : subscriptionUnblockTestData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "subscriptionUnblockTestData", groups = {"positive"})
    public void subscriptionUnblockTest(SubscriptionUBData subscriptionUnblockTest) throws Exception {

        logger.info("Starting ================> Subscription UnBlock Tests====>" + subscriptionUnblockTest.toString());

        String user_sub_auth_key = TestDataCreator.createSubscriptionUnblockTestData(subscriptionUnblockTest);

        logger.info("Check Key Present in Redis");
        AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));

        Response response = SHelper
                .subscriptionUnblock(SUtils.generateSubscriptionUnBlockValues(subscriptionUnblockTest.getUser_id(),
                        subscriptionUnblockTest.getBlockType(), subscriptionUnblockTest.getProduct_id(),
                        subscriptionUnblockTest.getPartner_id(), subscriptionUnblockTest.getCountry()));
        SValidationHelper.validate_ss_api_response(response);
        SValidationHelper.validate_ss_jsonBody(response, "response.message",
                subscriptionUnblockTest.getBlockListSummary());

        logger.info("Database Verification");
        String endDate = response.getHeaders().getValue("Date");
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
        String actualDate = DateUtils.extractDateTimeFromGMTTime(endDate, dateFormat);
        Long expectedDate = (Long) SDBHelper.getColumnValueUsingUserId("blocked_user", "end_date",
                "user_id=" + subscriptionUnblockTest.getUser_id());
        AppAssert.assertEqual(
                (int) DateUtils.getDaysBetweenJavaDates(actualDate,
                        DateUtils.convertLongToStringDate(expectedDate.longValue()).toString()),
                0, "Verify Blocked User End Date is updated to today");

        AppAssert.assertEqual(
                (Integer) SDBHelper.getColumnValueUsingUserId("blocked_user", "block_type",
                        "user_id=" + subscriptionUnblockTest.getUser_id()),
                0, "Verify User is updated in Blocked User Table");
    }

    @DataProvider(name = "subscriptionUnblockValExpiredTestData")
    public Iterator<Object[]> subscriptionUnblockValExpiredTestData() {

        ArrayList<SubscriptionUBData> subscriptionUnblockTestData = new ArrayList<SubscriptionUBData>();
        subscriptionUnblockTestData.add(SubscriptionUBData.UNBLOCKBLACKLISTVALEXP);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (SubscriptionUBData sbData : subscriptionUnblockTestData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "subscriptionUnblockValExpiredTestData", groups = {"positive", "pending"})
    public void subscriptionUnblockValExpiredTests(SubscriptionUBData subscriptionUnblockTest) throws Exception {

        logger.info("Starting ================> Subscription UnBlock Tests for Validity Expired====>"
                + subscriptionUnblockTest.toString());

        String user_sub_auth_key = TestDataCreator.createSubscriptionUnblockValExpiredTestData(subscriptionUnblockTest);

        logger.info("Check Key Present in Redis");
        AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));

        Response response = SHelper
                .subscriptionUnblock(SUtils.generateSubscriptionUnBlockValues(subscriptionUnblockTest.getUser_id(),
                        subscriptionUnblockTest.getBlockType(), subscriptionUnblockTest.getProduct_id(),
                        subscriptionUnblockTest.getPartner_id(), subscriptionUnblockTest.getCountry()));
        SValidationHelper.validate_ss_api_response(response);
        SValidationHelper.validate_ss_jsonBody(response, "response.message",
                subscriptionUnblockTest.getBlockListSummary());

        logger.info("Database Verification");
        String endDate = response.getHeaders().getValue("Date");
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
        String actualDate = DateUtils.extractDateTimeFromGMTTime(endDate, dateFormat);
        Long expectedDate = (Long) SDBHelper.getColumnValueUsingUserId("blocked_user", "end_date",
                "user_id=" + subscriptionUnblockTest.getUser_id());
        AppAssert.assertEqual(
                (int) DateUtils.getDaysBetweenJavaDates(actualDate,
                        DateUtils.convertLongToStringDate(expectedDate.longValue()).toString()),
                0, "Verify Blocked User End Date is updated to today");

        AppAssert.assertEqual(
                (Integer) SDBHelper.getColumnValueUsingUserId("blocked_user", "block_type",
                        "user_id=" + subscriptionUnblockTest.getUser_id()),
                0, "Verify User is updated in Blocked User Table");
    }

    @DataProvider(name = "subscriptionUnblockNegativeTestData")
    public Iterator<Object[]> subscriptionUnblockNegativeTestData() {

        ArrayList<SubscriptionUBData> subscriptionUnblockTestData = new ArrayList<SubscriptionUBData>();
        subscriptionUnblockTestData.add(SubscriptionUBData.SUBSCRIPTIONNEGATIVEUNBLOCK);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (SubscriptionUBData sbData : subscriptionUnblockTestData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "subscriptionUnblockNegativeTestData", groups = {"positive"})
    public void subscriptionUnblockNegativeTestData(SubscriptionUBData subscriptionUnblockTest) throws Exception {

        logger.info("Starting ================> subscription Unblock Negative Test====>"
                + subscriptionUnblockTest.toString());

        String user_sub_auth_key = TestDataCreator.createSubscriptionUnblockTestData(subscriptionUnblockTest);
        logger.info("Check Key Present in Redis");
        AppAssert.assertTrue(SRedisUtils.checkKey(user_sub_auth_key));

        Response response = SHelper.subscriptionUnblock(SUtils.generateSubscriptionUnBlockValues("",
                subscriptionUnblockTest.getBlockType(), subscriptionUnblockTest.getProduct_id(),
                subscriptionUnblockTest.getPartner_id(), subscriptionUnblockTest.getCountry()));
        SValidationHelper.validate_ss_api_response(response);

        logger.info("Database Verification");
        Long actualEndDate = subscriptionUnblockTest.getEnd_date();
        Long expectedDate = (Long) SDBHelper.getColumnValueUsingUserId("blocked_user", "end_date",
                "user_id=" + subscriptionUnblockTest.getUser_id());
        AppAssert.assertEqual(
                (int) DateUtils.getDaysBetweenJavaDates(
                        DateUtils.convertLongToStringDate(actualEndDate.longValue()).toString(),
                        DateUtils.convertLongToStringDate(expectedDate.longValue()).toString()),
                0, "Verify Other User is not affected");

    }

    @BeforeClass(alwaysRun = true)
    public void cleanUp() {
        DBUtils.cleanTable("user_subscription",
                "product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);
        DBUtils.cleanTable("blocked_user", "product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);
    }

}
