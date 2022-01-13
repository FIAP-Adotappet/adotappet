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

    public void receiveMessage(Map<String, String> message) {
        log.info("Received <" + message + ">");
        Long petId = Long.valueOf(message.get("pet_id"));
        Boolean aceito = Boolean.valueOf(message.get("aceito"));
        petService.changePetDisponivel(petId, !aceito);
        log.info("Message processed...");
    }
}
