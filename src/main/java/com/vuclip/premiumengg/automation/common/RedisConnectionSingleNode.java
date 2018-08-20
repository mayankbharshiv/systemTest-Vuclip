package com.vuclip.premiumengg.automation.common;

import redis.clients.jedis.Jedis;

/**
 * Created by Kohitij_Das on 10/04/17.
 */
public class RedisConnectionSingleNode {

     private static Jedis redisConnectionSingleNode;

    private RedisConnectionSingleNode() {
    }

    static String host;
    static String port;
    public static Jedis getRedisConnection() {
        if (redisConnectionSingleNode == null) {
            String redisHosts = Configuration.redisServers;
            System.out.println("Redis Hosts: " + redisHosts);
            String[] servers = redisHosts.split(",");
            for (String server : servers) {
                String[] hosts = server.split(":");
                 host = hosts[0];
                 port = hosts[1];
            }
            RedisConnectionSingleNode.redisConnectionSingleNode = new Jedis(host,Integer.parseInt(port));
        }
        return redisConnectionSingleNode;
    }
}
