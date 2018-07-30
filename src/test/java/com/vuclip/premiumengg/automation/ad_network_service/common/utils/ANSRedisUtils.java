package com.vuclip.premiumengg.automation.ad_network_service.common.utils;

import com.vuclip.premiumengg.automation.common.Log4J;
import com.vuclip.premiumengg.automation.common.RedisTemplateConnection;
import com.vuclip.premiumengg.automation.utils.RedisUtil;

import java.util.Set;

public class ANSRedisUtils {
	static RedisUtil util = new RedisUtil();

	public static boolean keyPresent(String transactionId) {
		Log4J.getLogger("REDIS KEYS CHECK ").info("CHECKING FOR KEY" + getKeyPattern(transactionId));
		boolean isFound = false;
		int count =1;
		Set<String> s=null;
		try {
			while (!isFound && count<=3) {
				Thread.sleep(1000);
			 s = RedisTemplateConnection.getRedisConnection().keys(getKeyPattern(transactionId));
				Log4J.getLogger("REDIS KEYS CHECK ").info(s.size());
				for (String string : s) {
					Log4J.getLogger("REDIS KEYS CHECK ").info(string);
				}
				if (s!=null || (s!=null && s.size()>=1)) {
					isFound=true;
				}
				count++;
			}
			if (s.isEmpty())
				return false;
			else
				return true;
		} catch (Exception e) {
			Log4J.getLogger("REDIS KEYS CHECK ").info("GOT Exception" + e.getMessage());
			e.printStackTrace();
		}
		return false;
		// boolean keyFound = false;
		// int lookupCount = 0;
		// while (!keyFound && lookupCount < 1) {
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// if (util.keys(getKeyPattern(transactionId),
		// RedisConnection.getRedisConnection()) != null) {
		// keyFound = true;
		// break;
		// }
		// lookupCount++;
		// }
		// return keyFound;
	}

	public static boolean keyNotPresent(String transactionId) {
		return keyPresent(transactionId);
		// return true;
	}

	private static String getKeyPattern(String transactionId) {
		StringBuilder key = new StringBuilder("*ADNETWORK_DETAILS");
		if (org.springframework.util.StringUtils.hasText(transactionId)) {
			key.append("_").append(transactionId);
			return key.append("*").toString();
		}
		return null;
	}

	private static String getkey(String transactionId) {
		StringBuilder key = new StringBuilder("ADNETWORK_DETAILS");
		if (org.springframework.util.StringUtils.hasText(transactionId)) {
			key.append("_").append(transactionId);
			return key.toString();
		}
		return null;
	}

}
