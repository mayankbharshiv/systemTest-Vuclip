package com.vuclip.premiumengg.automation.common;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author Sanjib Pramanick
 */
public class RabbitMQConnection {

    private static RabbitTemplate rabbitTemplate;

    private RabbitMQConnection() {
    }

    public static RabbitTemplate getRabbitTemplate() {
        if (rabbitTemplate == null) {
            System.out.println("========== Creating Rabbit MQ Template ============");
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory(Configuration.rabbitMQServer);
            connectionFactory.setUsername(Configuration.rabbitMQUser);
            connectionFactory.setPassword(Configuration.rabbitMQPassword);
            connectionFactory.setPort(Integer.parseInt(Configuration.rabbitMQPort));
            rabbitTemplate = new RabbitTemplate(connectionFactory);
            System.out.println("========== Created Rabbit MQ Template ============");

        }
        return rabbitTemplate;
    }

    public static void closeAllConnection() {
        System.out.println("======== Closing Rabbit MQ Connections =========");
        if (rabbitTemplate != null)
            if (rabbitTemplate.getConnectionFactory() != null)
                rabbitTemplate.stop();
    }
}
