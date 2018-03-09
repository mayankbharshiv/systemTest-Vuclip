package com.vuclip.premiumengg.automation.utils;

import com.vuclip.premiumengg.automation.services.vuconnect.common.RedisEntryValueDetailVO;
import redis.clients.jedis.JedisCluster;

public class RedisUtil {

    /**
     * @param key
     */
    public void deleteKey(String key, JedisCluster jedisCluster) {
        try {
            if (jedisCluster.exists(key)) {
                System.out.println("Key Found in Cache: " + key);
                System.out.println("Deleting");
                jedisCluster.del(key);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param key
     * @return
     */
    public boolean checkKey(String key, JedisCluster jedisCluster) {
        try {
            if (jedisCluster.exists(key)) {
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

    /**
     * @param msisdn
     * @param providerId
     * @param jedisCluster
     */
    public void clearCacheVuconnect(String msisdn, String providerId, JedisCluster jedisCluster) {
        String redissKeyDoubleCharging = msisdn + "_" + providerId;
        System.out.println("Rediss Key: " + redissKeyDoubleCharging);
        deleteKey(redissKeyDoubleCharging, jedisCluster);
    }

    /**
     * @param msisdn
     * @param providerId
     * @param jedisCluster
     */
    public boolean checkCacheVuconnect(String msisdn, String providerId, JedisCluster jedisCluster) {
        String redissKeyDoubleCharging = msisdn + "_" + providerId;
        System.out.println("Rediss Key: " + redissKeyDoubleCharging);
        return checkKey(redissKeyDoubleCharging, jedisCluster);
    }

    public void insertRequestEntryInRedis(String msisdn, String providerId,
                                          RedisEntryValueDetailVO redisEntryValueDetailVO, int ttlInSeconds, JedisCluster jedisCluster) {
        String requestCacheKey = msisdn + "_" + providerId;
        System.out.println("Rediss Key: " + requestCacheKey);
        if (jedisCluster.exists(requestCacheKey)) {
            System.out.println("redisEntryValueDetailVO already exist");
        } else {
            System.out.println("Adding redisEntryValueDetailVO to redis");
            jedisCluster.set(requestCacheKey, redisEntryValueDetailVO.marshal());
        }


    }


}
