package com.vuclip.premiumengg.automation.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by rahul s
 */
public class RedisTemplateConnection {
    private static RedisTemplate<String, Object> template = null;

    private RedisTemplateConnection() {
    }

    @SuppressWarnings("deprecation")
    public static RedisTemplate<String, Object> getRedisConnection() {
        if (template == null) {
            Log4J.getLogger().info("Setting up Redis Template");
            template = new RedisTemplate<>();
            String config[] = Configuration.redisServers.split(":");

            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setHostName(config[0]);
            jedisConnectionFactory.setPort(Integer.parseInt(config[1]));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

            @SuppressWarnings({"rawtypes", "unchecked"})
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

            template.setValueSerializer(jackson2JsonRedisSerializer);
            template.setKeySerializer(new StringRedisSerializer());
            template.setConnectionFactory(jedisConnectionFactory);
            template.afterPropertiesSet();

        }
        Log4J.getLogger().info("Redis Template is set");

        return template;
    }

    public static void closeConnection() {
        if (template != null)
            if (template.getConnectionFactory() != null)
                if (template.getConnectionFactory().getConnection() != null)
                    template.getConnectionFactory().getConnection().close();
    }

    public static ValueOperations<String, Object> getValueOperation() {
        return getRedisConnection().opsForValue();
    }

}
