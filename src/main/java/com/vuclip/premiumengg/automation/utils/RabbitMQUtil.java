package com.vuclip.premiumengg.automation.utils;

import com.vuclip.premiumengg.automation.common.Configuration;
import com.vuclip.premiumengg.automation.common.RabbitMQConnection;
import org.springframework.amqp.core.Message;

import java.nio.charset.StandardCharsets;

/**
 * @author Sanjib Pramanick
 */
public class RabbitMQUtil {

    public static void sendMessageToQueueByExchange(String exchange, String message) {
        RabbitMQConnection.getRabbitTemplate().convertAndSend(exchange, "", message);
    }

    public static Message getMessageFromQueue(String queueName) {
        return getMessageFromQueue(queueName, Configuration.defaultTimeOutMillisForQueue);
    }

    public static String getMessageBody(Message message) {
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

    public static Message getMessageFromQueue(String queueName, long timeoutMillis) {
        return getMessageFromQueue(queueName, timeoutMillis, false);
    }

    public static Message getMessageFromQueue(String queueName, long timeoutMillis, boolean isNeedToCaps) {
        if (isNeedToCaps)
            queueName = queueName.toUpperCase();
        return RabbitMQConnection.getRabbitTemplate().receive(queueName, timeoutMillis);
    }

}
