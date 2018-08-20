package com.vuclip.premiumengg.automation.common;

import redis.clients.jedis.Jedis;

/**
 * Created by Kohitij_Das on 10/04/17.
 */
public class JedisConnection {

    static String host;
    static String port;
    private static Jedis redisConnectionSingleNode;
    private JedisConnection() {
    }

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
            JedisConnection.redisConnectionSingleNode = new Jedis(host, Integer.parseInt(port));
        }
        return redisConnectionSingleNode;
    }
}
