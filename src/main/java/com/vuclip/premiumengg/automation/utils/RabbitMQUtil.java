package com.vuclip.premiumengg.automation.utils;

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
        return RabbitMQConnection.getRabbitTemplate().receive(queueName);
    }

    public static String getMessageBody(Message message) {
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

}
