package com.vuclip.premiumengg.automation.subscription_service.tests;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RabbitAdminConnection;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import com.vuclip.premiumengg.automation.subscription_service.common.models.GetUserStatusResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.models.RabbitMessageResponse;
import com.vuclip.premiumengg.automation.subscription_service.common.models.StateTransitionData;
import com.vuclip.premiumengg.automation.subscription_service.common.utils.*;
import com.vuclip.premiumengg.automation.utils.AppAssert;
import com.vuclip.premiumengg.automation.utils.DBUtils;
import com.vuclip.premiumengg.automation.utils.ObjectMapperUtils;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class StateTransitionTests {
    private static Logger logger = Log4J.getLogger("StateTransitionTests");
    Map<String, String> query = new HashMap<String, String>();

    @DataProvider(name = "StateTransitionData")
    public Iterator<Object[]> StateTransitionData() {

        ArrayList<StateTransitionData> stateTransitionData = new ArrayList<StateTransitionData>();
        stateTransitionData.add(StateTransitionData.ACTIVATION_SUCESS);
        stateTransitionData.add(StateTransitionData.ACTIVATION_LOWBALANCE);
        stateTransitionData.add(StateTransitionData.ACTIVATION_REGISTRATION_CLOSED);
        stateTransitionData.add(StateTransitionData.ACTIVATION_REGISTRATION_OPEN);
        stateTransitionData.add(StateTransitionData.ACTIVATION_REGISTRATION_ERROR);
        stateTransitionData.add(StateTransitionData.ACTIVATION_REGISTRATION_FAILURE);
        stateTransitionData.add(StateTransitionData.ACTIVATION_PARKING_NOVALIDITY);

        stateTransitionData.add(StateTransitionData.RETRYACTIVATIONSUCESS);
        stateTransitionData.add(StateTransitionData.RETRYACTIVATIONREGISTRATION);
        stateTransitionData.add(StateTransitionData.RETRYACTIVATIONREGISTRATIONOPEN);
        stateTransitionData.add(StateTransitionData.RETRYREGISTRATIONERROR);
        stateTransitionData.add(StateTransitionData.RETRYREGISTRATIONFAILURE);
        stateTransitionData.add(StateTransitionData.RETRYACTIVATIONPARKING);
        stateTransitionData.add(StateTransitionData.RETRYACTIVATIONPARKINGWOVALIDITY);

        stateTransitionData.add(StateTransitionData.WINBACKLOWBAL);
        stateTransitionData.add(StateTransitionData.WINBACKLOWERROR);
        stateTransitionData.add(StateTransitionData.WINBACKSUCESS);

        stateTransitionData.add(StateTransitionData.RENEWALINITSUCCESS);
        stateTransitionData.add(StateTransitionData.RENEWALINITLOWB);
        stateTransitionData.add(StateTransitionData.RENEWALINITFAILURE);
        stateTransitionData.add(StateTransitionData.RENEWALACTSUCCESS);
        stateTransitionData.add(StateTransitionData.RENEWALACTERRSUCCESS);
        stateTransitionData.add(StateTransitionData.RENEWALACTSUSPENDLOWB);
        stateTransitionData.add(StateTransitionData.RENEWALACTSUSPENDFAIL);
        stateTransitionData.add(StateTransitionData.RENEWALSUSPENDFAIL);
        stateTransitionData.add(StateTransitionData.RENEWALSUSPENDERR);
        stateTransitionData.add(StateTransitionData.RENEWALSUSPENDLOWB);
        stateTransitionData.add(StateTransitionData.RENEWALSUSSUCCESS);

        stateTransitionData.add(StateTransitionData.DEACTIVATIONRETRYERR);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONRETRYFAIL);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONRETRYSUCCESS);

        stateTransitionData.add(StateTransitionData.CHURNINITSUCCESS);
        stateTransitionData.add(StateTransitionData.CHURNINITFAIL);
        stateTransitionData.add(StateTransitionData.CHURNINITNPROGRESS);
        stateTransitionData.add(StateTransitionData.CHURNINITERR);
        stateTransitionData.add(StateTransitionData.CHURNSUSSUCCESS);
        stateTransitionData.add(StateTransitionData.CHURNSUSFAIL);
        stateTransitionData.add(StateTransitionData.CHURNSUSNPROGRESS);
        stateTransitionData.add(StateTransitionData.CHURNSUSERR);
        stateTransitionData.add(StateTransitionData.CHURNPRKSUCCESS);
        stateTransitionData.add(StateTransitionData.CHURNPRKFAIL);
        stateTransitionData.add(StateTransitionData.CHURNPRKINPROGRESS);
        stateTransitionData.add(StateTransitionData.CHURNPRKERR);
        stateTransitionData.add(StateTransitionData.CHURNACTSUCCESS);
        stateTransitionData.add(StateTransitionData.CHURNACTFAIL);
        stateTransitionData.add(StateTransitionData.CHURNACTNPROGRESS);
        stateTransitionData.add(StateTransitionData.CHURNACTERR);

        stateTransitionData.add(StateTransitionData.DEACTIVATIONINITCNFFALSE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONINITCNFTRUE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONINITSUCCESS);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONINITFAIL);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONINITERR);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONSUSCNFFALSE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONSUSCNFTRUE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONSUSSUCCESS);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONSUSFAIL);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONSUSERR);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONPRKCNFFALSE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONPRKCNFTRUE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONPRKSUCCESS);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONPRKFAIL);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONPRKERR);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONACTCNFFALSE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONACTCNFTRUE);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONACTSUCCESS);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONACTFAIL);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONACTERR);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONDNITSUCCESS);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONDINITFAIL);
        stateTransitionData.add(StateTransitionData.DEACTIVATIONDINITERR);

        /***** NEED TO CHECK FOR BELOW IF THESE ARE INCLUDED IN STATE TRANSITION */
        // stateTransitionData.add(StateTransitionData.DEACTIVATIONACTPROGRESS);
        // stateTransitionData.add(StateTransitionData.DEACTIVATIONSUSPROGRESS);
        // stateTransitionData.add(StateTransitionData.DEACTIVATIONINITPROGRESS);
        // stateTransitionData.add(StateTransitionData.DEACTIVATIONPRKPROGRESS);
        // stateTransitionData.add(StateTransitionData.ACTIVATION_CONSENT_CLOSE);
        // stateTransitionData.add(StateTransitionData.ACTIVATION_CONSENT_OPEN);
        // stateTransitionData.add(StateTransitionData.DEACTIVATIONRETRYPROCESSBLACKLISTSUCCESS);
        // stateTransitionData.add(StateTransitionData.DEACTIVATIONRETRYPROCESSBLACKLISTFAIL);
        // stateTransitionData.add(StateTransitionData.DEACTIVATIONRETRYPROCESSBLACKLISTERR);

        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (StateTransitionData sbData : stateTransitionData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "StateTransitionData", groups = {"positive"})
    public void actInitStateTransitionTests(StateTransitionData stateTransitionData) {
        logger.info("Starting ================> State Transition Test==> " + stateTransitionData.toString());
        try {
            Log4J.getLogger("setting up test data into DB, Redis and RabbitMQ");

            RabbitAdminConnection.getRabbitAdminConnection().purgeQueue(SUtils.ssFanOut, false);

            String userSubAuthKey = TestDataCreator.createStateTransitionTestDate(stateTransitionData);

            Message message = RabbitMQConnection.getRabbitTemplate().receive(SUtils.ssFanOut, 25000);
            if (message == null)
                AppAssert.assertTrue(false, "Not able to fetch message from ST_SUBSCRIPTION_SCHEDULED_ACTIVITY");
            RabbitMessageResponse queueResponse = ObjectMapperUtils.readValueFromString(new String(message.getBody()),
                    RabbitMessageResponse.class);
            Log4J.getLogger().info("RECEIVED FROM QUEUE ST_SUBSCRIPTION_SCHEDULED_ACTIVITY "
                    + ObjectMapperUtils.writeValueAsString(queueResponse));

            logger.info("DB Validation");
            SDBHelper.validateTable("user_subscription", stateTransitionData.getUser_id(),
                    stateTransitionData.getProduct_id(), stateTransitionData.getPartner_id(),
                    queueResponse.getSubscriptionInfo().getNextBillingDate(), userSubAuthKey,
                    stateTransitionData.getSubscriptionStatusNew());

            AppAssert.assertEqual(
                    SDBHelper.validateDates("user_subscription", "user_id=" + stateTransitionData.getUser_id(),
                            stateTransitionData.getStartUpdateColumn(), stateTransitionData.getEndDateColumn()),
                    SDBHelper.getValidityDaysFromBillingPackage(stateTransitionData.getValidityColumn(),
                            stateTransitionData.getSubscription_billing_code()),
                    stateTransitionData.getValidityColumn() + " days is added to the required user");

            Long endDate = (Long) SDBHelper.getColumnValueUsingUserId("user_subscription",
                    stateTransitionData.getEndDateColumn(), "user_id=" + stateTransitionData.getUser_id());

            logger.info("RabbitMQ Validation");
            SValidationHelper.validateQueueMessage(queueResponse, stateTransitionData.getAction_Id(),
                    stateTransitionData.getActivity_Id(), stateTransitionData.getSubscription_status(),
                    stateTransitionData.getSubscriptionStatusNew(), stateTransitionData.getActionResult(), endDate);

            logger.info("Redis Validation");
            query.put("userid", stateTransitionData.getUser_id());
            Response getUserResponse = SHelper.getUserStatus(query);
            SValidationHelper.validate_ss_api_response(getUserResponse);
            GetUserStatusResponse getUserStatusResponse = getUserResponse.getBody().as(GetUserStatusResponse.class);
            SValidationHelper.validateGetUserStatus(getUserStatusResponse,
                    queueResponse.getSubscriptionInfo().getStartDate(),
                    queueResponse.getSubscriptionInfo().getEndDate(),
                    queueResponse.getSubscriptionInfo().getNextBillingDate(), stateTransitionData.getChargedPrice(),
                    stateTransitionData.getActivation_date(), queueResponse.getSubscriptionInfo().getDeactivationDate(),
                    stateTransitionData.getSummary(), stateTransitionData.getSubscriptionStatusNew(),
                    stateTransitionData.getSubscription_billing_code(), stateTransitionData.getCharged_billing_code(),
                    stateTransitionData.getCountry(), stateTransitionData.getUserSource(),
                    stateTransitionData.getMode(), queueResponse.getSubscriptionInfo().getPaid());
        } catch (Exception e) {
            e.printStackTrace();
            AppAssert.assertTrue(false);
        }
    }

    @DataProvider(name = "errorFailureNoStateTransitionData")
    public Iterator<Object[]> errorFailureNoStateTransitionData() {

        ArrayList<StateTransitionData> stateTransitionData = new ArrayList<StateTransitionData>();
        stateTransitionData.add(StateTransitionData.ACTIVATION_FAILURE);
        stateTransitionData.add(StateTransitionData.ACTIVATION_ERROR);
        stateTransitionData.add(StateTransitionData.RETRYACTIVATIONFAILURE);
        stateTransitionData.add(StateTransitionData.RETRYACTIVATIONERROR);
        stateTransitionData.add(StateTransitionData.RENEWALINITERROR);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (StateTransitionData sbData : stateTransitionData) {
            dp.add(new Object[]{sbData});
        }
        return dp.iterator();
    }

    @Test(dataProvider = "errorFailureNoStateTransitionData", groups = {"positive"})
    public void errorFailureNoStateTransitionTests(StateTransitionData stateTransitionData) {
        logger.info("Starting ================> State Transition Test==> " + stateTransitionData.toString());
        try {
            Log4J.getLogger("setting up test data into DB, Redis and RabbitMQ");

            RabbitAdminConnection.getRabbitAdminConnection().purgeQueue(SUtils.ssFanOut, false);

            String userSubAuthKey = TestDataCreator.createStateTransitionTestDate(stateTransitionData);

            Message message = RabbitMQConnection.getRabbitTemplate().receive(SUtils.ssFanOut, 25000);
            if (message == null)
                AppAssert.assertTrue(false, "Not able to fetch message from ST_SUBSCRIPTION_SCHEDULED_ACTIVITY");
            RabbitMessageResponse queueResponse = ObjectMapperUtils.readValueFromString(new String(message.getBody()),
                    RabbitMessageResponse.class);
            Log4J.getLogger().info("RECEIVED FROM QUEUE ST_SUBSCRIPTION_SCHEDULED_ACTIVITY "
                    + ObjectMapperUtils.writeValueAsString(queueResponse));

            logger.info("DB Validation");
            SDBHelper.validateTable("user_subscription", stateTransitionData.getUser_id(),
                    stateTransitionData.getProduct_id(), stateTransitionData.getPartner_id(),
                    queueResponse.getSubscriptionInfo().getNextBillingDate(), userSubAuthKey,
                    stateTransitionData.getSubscriptionStatusNew());

            Long endDate = (Long) SDBHelper.getColumnValueUsingUserId("user_subscription",
                    stateTransitionData.getEndDateColumn(), "user_id=" + stateTransitionData.getUser_id());

            logger.info("RabbitMQ Validation");
            SValidationHelper.validateQueueMessage(queueResponse, stateTransitionData.getAction_Id(),
                    stateTransitionData.getActivity_Id(), stateTransitionData.getSubscription_status(),
                    stateTransitionData.getSubscriptionStatusNew(), stateTransitionData.getActionResult(), endDate);

            logger.info("Redis Validation");
            query.put("userid", stateTransitionData.getUser_id());
            Response getUserResponse = SHelper.getUserStatus(query);
            SValidationHelper.validate_ss_api_response(getUserResponse);
            GetUserStatusResponse getUserStatusResponse = getUserResponse.getBody().as(GetUserStatusResponse.class);
            SValidationHelper.validateGetUserStatus(getUserStatusResponse,
                    queueResponse.getSubscriptionInfo().getStartDate(),
                    queueResponse.getSubscriptionInfo().getEndDate(),
                    queueResponse.getSubscriptionInfo().getNextBillingDate(), stateTransitionData.getChargedPrice(),
                    stateTransitionData.getActivation_date(), queueResponse.getSubscriptionInfo().getDeactivationDate(),
                    stateTransitionData.getSummary(), stateTransitionData.getSubscriptionStatusNew(),
                    stateTransitionData.getSubscription_billing_code(), stateTransitionData.getCharged_billing_code(),
                    stateTransitionData.getCountry(), stateTransitionData.getUserSource(),
                    stateTransitionData.getMode(), queueResponse.getSubscriptionInfo().getPaid());
        } catch (Exception e) {
            e.printStackTrace();
            AppAssert.assertTrue(false);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void cleanUp() {
        DBUtils.cleanTable("user_subscription",
                "product_id =" + SUtils.productId + " and partner_id =" + SUtils.productId);

    }

}
