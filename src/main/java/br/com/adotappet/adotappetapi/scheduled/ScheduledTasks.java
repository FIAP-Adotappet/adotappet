package br.com.adotappet.adotappetapi.scheduled;

import br.com.adotappet.adotappetapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final PetService petService;

    @Autowired
    public ScheduledTasks(PetService petService) {
        this.petService = petService;
        this.syncPets();
    }

    @Scheduled(cron="0 0 0 * * *")
    public void syncPets() {
        this.petService.populatePets();
    }
}
