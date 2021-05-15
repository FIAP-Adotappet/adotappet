package br.com.adotappet.adotappetapi.repository;

import br.com.adotappet.adotappetapi.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value="select p from Pet p where disponivel = true")
    List<Pet> findAllDisponiveis();
}
