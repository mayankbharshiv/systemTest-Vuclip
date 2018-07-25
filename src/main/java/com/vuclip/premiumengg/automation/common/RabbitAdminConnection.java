package com.vuclip.premiumengg.automation.common;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * @author rahul s
 */
public class RabbitAdminConnection {

    private static RabbitAdmin rabbitAdmin;

    private RabbitAdminConnection() {
    }

    public static RabbitAdmin getRabbitAdminConnection() {
        if (rabbitAdmin == null) {

            System.out.println("========== Creating Rabbit MQ Admin Connection ============");
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory(Configuration.rabbitMQServer);
            connectionFactory.setUsername(Configuration.rabbitMQUser);
            connectionFactory.setPassword(Configuration.rabbitMQPassword);
            connectionFactory.setPort(Integer.parseInt(Configuration.rabbitMQPort));

            rabbitAdmin = new RabbitAdmin(connectionFactory);

            System.out.println("========== Created Rabbit MQ admin ============");

        }
        return rabbitAdmin;
    }

    public static void closeAllConnection() {
        System.out.println("======== Closing Rabbit MQ Admin connection =========");
        if (rabbitAdmin != null)
            if (rabbitAdmin.getRabbitTemplate() != null)
                if (rabbitAdmin.getRabbitTemplate().getConnectionFactory() != null)
                    rabbitAdmin.getRabbitTemplate().stop();

    }
}
