package br.com.adotappet.adotappetapi.api.controller;

import br.com.adotappet.adotappetapi.api.dto.NovoPedidoDTO;
import br.com.adotappet.adotappetapi.core.rabbitmq.NovoPedidoRabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    RabbitTemplate rabbitTemplate;

    public PedidoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/novo")
    public ResponseEntity<String> criaPedido(@Valid @RequestBody NovoPedidoDTO novoPedidoDTO) {
        Map<String, Object> actionMap = Map.of("pet_id", novoPedidoDTO.getPetId(), "usuario_id", novoPedidoDTO.getUsuarioId());
        rabbitTemplate.convertAndSend(NovoPedidoRabbitMQConfig.NOVO_PEDIDO_MESSAGE_QUEUE, actionMap);
        return ResponseEntity.accepted().body("Pedido criado para avaliação");
    }
}
