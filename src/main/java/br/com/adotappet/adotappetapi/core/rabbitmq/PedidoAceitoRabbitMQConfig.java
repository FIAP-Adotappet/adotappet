package br.com.adotappet.adotappetapi.core.rabbitmq;

import br.com.adotappet.adotappetapi.core.rabbitmq.listener.PedidoAceitoMessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

public class PedidoAceitoRabbitMQConfig extends AbstractRabbitMQConfig{
    public static final String PEDIDO_ACEITO_MESSAGE_QUEUE = "pedido-aceito-message-queue";

    @Bean
    Queue pedidoAceitoQueue() {
        return new Queue(PEDIDO_ACEITO_MESSAGE_QUEUE, false);
    }

    @Bean
    Binding pedidoAceitoBinding(TopicExchange exchange) {
        return BindingBuilder.bind(pedidoAceitoQueue()).to(exchange).with(PEDIDO_ACEITO_MESSAGE_QUEUE);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(PedidoAceitoMessageListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
