package br.com.adotappet.adotappetapi.core.rabbitmq;

import br.com.adotappet.adotappetapi.core.rabbitmq.listener.PedidoAceitoMessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PedidoAceitoRabbitMQConfig {
    public static final String PEDIDO_ACEITO_MESSAGE_QUEUE = "pedido-aceito-message-queue";
    private final PedidoAceitoMessageListener messageListener;

    public PedidoAceitoRabbitMQConfig(PedidoAceitoMessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Bean
    Queue pedidoAceitoQueue() {
        return new Queue(PEDIDO_ACEITO_MESSAGE_QUEUE, false);
    }

    @RabbitListener(queues = PEDIDO_ACEITO_MESSAGE_QUEUE)
    public void listen(Map<String, Object> message) {
        messageListener.receiveMessage(message);
    }
}
