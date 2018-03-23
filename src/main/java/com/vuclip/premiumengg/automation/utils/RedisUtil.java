package com.vuclip.premiumengg.automation.utils;

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
}
