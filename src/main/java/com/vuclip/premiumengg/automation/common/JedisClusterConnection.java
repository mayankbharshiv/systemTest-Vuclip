package com.vuclip.premiumengg.automation.common;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kohitij_Das on 10/04/17.
 */
public class JedisClusterConnection {

    private static JedisCluster redisConnection;

    private JedisClusterConnection() {
    }

    public static JedisCluster getRedisConnection() {
        if (redisConnection == null) {
            String redisHosts = Configuration.redisServers;
            System.out.println("Redis Hosts: " + redisHosts);
            String[] servers = redisHosts.split(",");
            Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
            for (String server : servers) {
                String[] hosts = server.split(":");
                String host = hosts[0];
                String port = hosts[1];
                jedisClusterNodes.add(new HostAndPort(host, Integer.parseInt(port)));
            }
            JedisClusterConnection.redisConnection = new JedisCluster(jedisClusterNodes);
        }
        return redisConnection;
    }
}
