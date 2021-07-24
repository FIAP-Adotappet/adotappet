package br.com.adotappet.adotappetapi.domain.repository;

import br.com.adotappet.adotappetapi.domain.entity.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PetRepository petRepository;

    private Pet pet;

    @BeforeEach
    public void setup() {
        pet = new Pet();
    }

    @Test
    public void deveSalvarUmPet(){
        petRepository.save(pet);
        assertNotNull(pet.getId());
    }

    @Test
    public void deveBuscarUmPet() {
        petRepository.save(pet);
        Optional<Pet> petDb = petRepository.findById(pet.getId());
        assertTrue(petDb.isPresent());
    }

    @Test
    public void deveDeletarUmPet() {
        petRepository.save(pet);
        assertFalse(petRepository.findAll().isEmpty());
        petRepository.delete(pet);
        assertTrue(petRepository.findAll().isEmpty());
    }

    @Test
    public void deveAtualizarUmPet() {
        petRepository.save(pet);
        Pet.Porte portePet = Pet.Porte.GRANDE;
        pet.setPorte(portePet);
        petRepository.save(pet);
        Optional<Pet> petDb = petRepository.findById(pet.getId());
        assertTrue(petDb.isPresent());
        assertEquals(portePet, petDb.get().getPorte());
    }
}
