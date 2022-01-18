package br.com.adotappet.adotappetapi.core.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NovoPedidoRabbitMQConfig {
    public final static String NOVO_PEDIDO_MESSAGE_QUEUE = "novo-pedido-message-queue";

    @Bean
    Queue novoPedidoQueue() {
        return new Queue(NOVO_PEDIDO_MESSAGE_QUEUE, false);
    }
}
