package br.com.adotappet.adotappetapi.service;

import br.com.adotappet.adotappetapi.dto.PetDTO;
import br.com.adotappet.adotappetapi.entity.Pet;
import br.com.adotappet.adotappetapi.repository.PetRepository;
import br.com.adotappet.adotappetapi.util.JsonLoader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PetService(PetRepository petRepository, ModelMapper modelMapper) {
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
    }

    public void populatePets() {
        JsonLoader jsonLoader = new JsonLoader();
        List<Pet> pets = List.of(jsonLoader.loadBeanFromJson(Pet[].class, "pets.json"));
        pets.forEach(pet -> pet.setDisponivel(true));
        petRepository.saveAll(pets);
    }

    @Transactional(readOnly = true)
    public List<PetDTO> findAllDisponiveis() {
        return toCollectionDTO(petRepository.findAllDisponiveis());
    }

    @Transactional(readOnly = true)
    public PetDTO findById(Long id) {
        return toDTO(petRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet {" + id + "} not found")));
    }

    private PetDTO toDTO(Pet pet) {
        return modelMapper.map(pet, PetDTO.class);
    }

    private List<PetDTO> toCollectionDTO(List<Pet> pet) {
        return pet.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
