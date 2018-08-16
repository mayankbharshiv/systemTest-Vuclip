package com.vuclip.premiumengg.automation.subscription_service.common.utils;

import com.vuclip.premiumengg.automation.common.JedisConnection;
import com.vuclip.premiumengg.automation.utils.RedisUtil;

import redis.clients.jedis.Jedis;

public class SRedisUtils {

	static RedisUtil util = new RedisUtil();
	/*
	 * public static boolean keyPresent(String object) { boolean keyFound = false;
	 * int lookupCount = 0; while (!keyFound && lookupCount < 1) { try {
	 * Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
	 * if (util.keys(getKeyPattern(object), RedisConnection.getRedisConnection()) !=
	 * null) { keyFound = true; break; } lookupCount++; } return keyFound; }
	 * 
	 * public static boolean keyNotPresent(String object) { return
	 * keyPresent(object); // return true; }
	 */

	// TODO
	/*
	 * public static insertKey(String object) { util.insert(key, value,
	 * RedisConnection.getRedisConnection()); }
	 */

	public static boolean checkKey(String key) {
		Jedis jedis=JedisConnection.getRedisConnection();
		try {

			if (jedis.exists(key)) {
				System.out.println("Key Found in Cache: " + key);
				return true;
			} else {
				System.out.println("Key Not Found in Cache: " + key);
				return false;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public static void insertIntoRedis(String key, String redisValue) {

		JedisConnection.getRedisConnection().set(key, redisValue);
	}

}
