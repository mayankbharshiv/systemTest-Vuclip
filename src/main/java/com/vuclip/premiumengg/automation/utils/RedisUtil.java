package com.vuclip.premiumengg.automation.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

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
     */
    public int insert(String key, String value, JedisCluster jedisCluster) {
        try {
            if (jedisCluster.exists(key)) {
                System.out.println("Key Already Present in Cache: " + key);
                System.out.println("Value" + jedisCluster.get(key));

            } else {
                System.out.println("Inserting with key : " + key);
                jedisCluster.set(key, value);
                return 0;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public String fetch(String key, JedisCluster jedisCluster) {
        try {
            if (jedisCluster.exists(key)) {
                return jedisCluster.get(key);

            } else {
                System.out.println("Key not found : " + key);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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
     * Method to perform flushall.
     *
     * @param jedisCluster
     */
    public void flushAll(JedisCluster jedisCluster) {
        for (JedisPool pool : jedisCluster.getClusterNodes().values()) {
            Jedis jedis = pool.getResource();
            System.out.println("Redis Flush: " + jedis.flushAll());
        }
    }

    /**
     * Method to close all redis connections.
     *
     * @param jedisCluster
     */
    public void closeRedisConnections(JedisCluster jedisCluster) {
        for (JedisPool pool : jedisCluster.getClusterNodes().values()) {
            Jedis jedis = pool.getResource();
            if (jedis != null) {
                System.out.println("Closing Redis Connection ");
                jedis.close();
            }
            pool.destroy();
        }
    }
}
