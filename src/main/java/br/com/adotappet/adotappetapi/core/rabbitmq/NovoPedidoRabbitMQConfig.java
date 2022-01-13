package br.com.adotappet.adotappetapi.core.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NovoPedidoRabbitMQConfig extends AbstractRabbitMQConfig {
    public final static String NOVO_PEDIDO_MESSAGE_QUEUE = "novo-pedido-message-queue";

    @Bean
    Queue novoPedidoQueue() {
        return new Queue(NOVO_PEDIDO_MESSAGE_QUEUE, false);
    }

    @Bean
    Binding novoPedidoBinding(TopicExchange exchange) {
        return BindingBuilder.bind(novoPedidoQueue()).to(exchange).with(NOVO_PEDIDO_MESSAGE_QUEUE);
    }
}
