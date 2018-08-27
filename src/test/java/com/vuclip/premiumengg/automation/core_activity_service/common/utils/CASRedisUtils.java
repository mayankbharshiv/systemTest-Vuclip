package com.vuclip.premiumengg.automation.core_activity_service.common.utils;

import com.vuclip.premiumengg.automation.common.JedisConnection;
import com.vuclip.premiumengg.automation.utils.RedisUtil;

import redis.clients.jedis.Jedis;

public class CASRedisUtils {

	static RedisUtil util = new RedisUtil();

	public static boolean checkKey(String key) {
		Jedis jedis = JedisConnection.getRedisConnection();
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
