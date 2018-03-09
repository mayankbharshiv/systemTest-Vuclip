package com.vuclip.premiumengg.automation.vuconnect.base;

import com.vuclip.premiumengg.automation.common.RedisConnection;
import com.vuclip.premiumengg.automation.helpers.TestDataHelper;
import com.vuclip.premiumengg.automation.helpers.ValidationHelper;
import com.vuclip.premiumengg.automation.helpers.VuConnectHelper;
import com.vuclip.premiumengg.automation.utils.RedisUtil;
import redis.clients.jedis.JedisCluster;

/**
 * @author Kohitij_Das
 */
public class BillingTestBase {

    protected static JedisCluster redisConnection;

    protected TestDataHelper testDataHelper;
    protected VuConnectHelper vuConnectHelper;
    protected ValidationHelper validationHelper;

    protected RedisUtil redisUtil;

    /**
     * Get Redis Connection.
     */
    protected void setRedisConnection() {
        redisConnection = RedisConnection.getRedisConnection();
    }
}