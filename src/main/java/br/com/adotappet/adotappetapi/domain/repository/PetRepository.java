package br.com.adotappet.adotappetapi.domain.repository;

import br.com.adotappet.adotappetapi.domain.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value="select p from Pet p where disponivel = true")
    List<Pet> findAllDisponiveis();

    Optional<Pet> findByExternalId(String externalId);
}
