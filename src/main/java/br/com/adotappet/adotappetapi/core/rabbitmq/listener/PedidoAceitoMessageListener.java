package br.com.adotappet.adotappetapi.core.rabbitmq.listener;

import br.com.adotappet.adotappetapi.domain.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PedidoAceitoMessageListener {
    private final PetService petService;
    private final Logger log = LoggerFactory.getLogger(PedidoAceitoMessageListener.class);

    public PedidoAceitoMessageListener(PetService petService) {
        this.petService = petService;
    }

    public void receiveMessage(Map<String, Object> message) {
        log.info("Received <" + message + ">");
        Long pedidoId = Long.valueOf(message.get("pedido_id").toString());
        Long petId = Long.valueOf(message.get("pet_id").toString());
        boolean aprovado = Boolean.parseBoolean(message.get("aprovado").toString());
        petService.changePetDisponivel(petId, !aprovado);
        log.info(String.format("Pet id: %d, do pedido %d, que foi %s atualizado com suceso", petId, pedidoId, aprovado ? "aceito" : "rejeitado"));
    }
}
