package com.vuclip.premiumengg.automation.helpers;

import com.vuclip.premiumengg.automation.services.vuconnect.steps.ValidateBillingResponse;
import com.vuclip.premiumengg.automation.utils.DateUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by Kohitij_Das on 11/06/17.
 */
public class ValidationHelper {

    private DateUtils dateUtils;

    /**
     * @param actualResponse
     * @param expectedResponse
     */
    public void validate_vuconnect_billing_response(Response actualResponse, ValidateBillingResponse expectedResponse) {
        Assert.assertEquals(actualResponse.getBody().xmlPath().getString("serviceResponse.responseCode"),
                expectedResponse.getResponseCode(), "statusCode mismatch for [" + expectedResponse.getMsisdn() + "]");
        Assert.assertEquals(actualResponse.getBody().xmlPath().getString("serviceResponse.billingActionId"),
                expectedResponse.getBillingActionId(), "billingActionId mismatch for [" + expectedResponse.getMsisdn() + "]");
        Assert.assertEquals(actualResponse.getBody().xmlPath().getString("serviceResponse.billingStatusId"),
                expectedResponse.getBillingStatusId(), "billingStatusId mismatch for [" + expectedResponse.getMsisdn() + "]");
    }

    /**
     * @param validity
     * @param actualNBD
     * @param nbdInfo
     * @throws ParseException
     */
    public void validateNBD(String validity, String actualNBD, Map<String, String> nbdInfo) throws ParseException {
        Reporter.log("validate_next_billing_date", false);
        dateUtils = new DateUtils(nbdInfo.get("timeZone"));
        dateUtils.setTimeZone(nbdInfo.get("timeZone"));
        SoftAssert softAssert = new SoftAssert();

        if ("success_validity".equalsIgnoreCase(nbdInfo.get("type"))) {
            String expectedNBD = dateUtils.addValidityGMT(validity, nbdInfo.get("timeUnit"));

            System.out.println("Expected NBD : " + expectedNBD);
            System.out.println("Actual NBD   : " + actualNBD);

            softAssert.assertEquals(dateUtils.compareDates(expectedNBD, actualNBD, 15), true);
        } else if ("success_window".equalsIgnoreCase(nbdInfo.get("type"))) {
            String expectedNBD = dateUtils.getGMTTimeFromLocalWindow(nbdInfo.get("window1"), nbdInfo.get("window2"),
                    dateUtils.addValidityLocal(validity, nbdInfo.get("timeUnit")), "success");

            System.out.println("Expected NBD : " + expectedNBD);
            System.out.println("Actual NBD   : " + actualNBD);

            softAssert.assertEquals(dateUtils.compareDates(expectedNBD, actualNBD, 5), true);
        }

        // for window size 3

        else if ("success_window_3".equalsIgnoreCase(nbdInfo.get("type"))) {

            String expectedNBD = dateUtils.getGMTTimeFromLocalWindow(nbdInfo.get("window1"), nbdInfo.get("window2"),
                    nbdInfo.get("window3"), dateUtils.addValidityLocal(validity, nbdInfo.get("timeUnit")), "success");

            System.out.println("Expected NBD : " + expectedNBD);
            System.out.println("Actual NBD   : " + actualNBD);

            softAssert.assertEquals(dateUtils.compareDates(expectedNBD, actualNBD, 5), true);
        } else if ("failure_validity".equalsIgnoreCase(nbdInfo.get("type"))) {
            String expectedNBD = dateUtils.addValidityGMT(nbdInfo.get("retryPeriod"), nbdInfo.get("timeUnit"));

            System.out.println("Expected NBD : " + expectedNBD);
            System.out.println("Actual NBD 	 : " + actualNBD);

            softAssert.assertEquals(dateUtils.compareDates(expectedNBD, actualNBD, 15), true);
        } else if ("failure_window".equalsIgnoreCase(nbdInfo.get("type"))) {
            String expectedNBD = dateUtils.getGMTTimeFromLocalWindow(nbdInfo.get("window1"), nbdInfo.get("window2"),
                    dateUtils.getCurrentLocalTime(), "failure");

            System.out.println("Expected NBD : " + expectedNBD);
            System.out.println("Actual NBD 	 : " + actualNBD);

            softAssert.assertEquals(dateUtils.compareDates(expectedNBD, actualNBD, 5), true);
        }

        // for window size 3
        else if ("failure_window_3".equalsIgnoreCase(nbdInfo.get("type"))) {

            String expectedNBD = dateUtils.getGMTTimeFromLocalWindow(nbdInfo.get("window1"), nbdInfo.get("window2"),
                    nbdInfo.get("window3"), dateUtils.getCurrentLocalTime(), "failure");

            System.out.println("Expected NBD : " + expectedNBD);
            System.out.println("Actual NBD 	 : " + actualNBD);

            softAssert.assertEquals(dateUtils.compareDates(expectedNBD, actualNBD, 5), true);
        }
    }
}
