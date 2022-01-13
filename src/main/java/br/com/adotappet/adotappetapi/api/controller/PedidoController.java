package br.com.adotappet.adotappetapi.api.controller;

import br.com.adotappet.adotappetapi.core.rabbitmq.NovoPedidoRabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    RabbitTemplate rabbitTemplate;

    public PedidoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping("/novo/{petId}/{usuarioId}")
    public ResponseEntity<String> criaPedido(@PathVariable Long petId, @PathVariable Long usuarioId) {
        Map<String, Long> actionMap = Map.of("pet_id", petId, "usuario_id", usuarioId);
        rabbitTemplate.convertAndSend(NovoPedidoRabbitMQConfig.NOVO_PEDIDO_MESSAGE_QUEUE, actionMap);
        return ResponseEntity.accepted().body("Pedido criado para avaliação");
    }
}
