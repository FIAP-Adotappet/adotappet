package br.com.adotappet.adotappetapi.controller;

import br.com.adotappet.adotappetapi.dto.PetDTO;
import br.com.adotappet.adotappetapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<PetDTO> findAll() {
        return petService.findAllDisponiveis();
    }

    @GetMapping("/{id}")
    public PetDTO findById(@PathVariable Long id) {
        return petService.findById(id);
    }


}
