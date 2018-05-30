package com.vuclip.premiumengg.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class AppAssert {

    static Logger logger = LoggerFactory.getLogger("AssertLogger");

    public static void assertEqual(String actVal, String exVal, String message) {
        if (message != null) {
            logger.info(message + "Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal, message);
        } else {
            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
            Assert.assertEquals(actVal, exVal);
        }
    }

    public static void assertEqual(String actVal, String exVal) {
        assertEqual(actVal, exVal, null);
    }

    public static void assertTrue(Boolean condition, String message) {
        if (message != null) {
            logger.info(message + "Condition :[" + condition + "]");
            Assert.assertTrue(condition, message);
        } else {
            logger.info("Condition" + condition + "]");
            Assert.assertTrue(condition);
        }
    }

    public static void assertTrue(Boolean condition) {
        assertTrue(condition, null);
    }

	public static void assertEqual(int actVal, int exVal, String message) {
		  if (message != null) {
	            logger.info(message + "Actual :[" + actVal + "], expected :[" + exVal + "]");
	            Assert.assertEquals(actVal, exVal, message);
	        } else {
	            logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
	            Assert.assertEquals(actVal, exVal);
	        }
	}
    
}
