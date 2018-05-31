package com.vuclip.premiumengg.automation.utils;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.vuclip.premiumengg.automation.common.Log4J;

public class AppAssert {

	static Logger logger = Log4J.getLogger("AssertLogger");

	public static void assertEqual(String actVal, String exVal, String message) {
		if (message != null) {
			logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
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
			logger.info(message + " Condition :[" + condition + "]");
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
			logger.info(message + " Actual :[" + actVal + "], expected :[" + exVal + "]");
			Assert.assertEquals(actVal, exVal, message);
		} else {
			logger.info("Actual :[" + actVal + "], expected :[" + exVal + "]");
			Assert.assertEquals(actVal, exVal);
		}
	}

}
