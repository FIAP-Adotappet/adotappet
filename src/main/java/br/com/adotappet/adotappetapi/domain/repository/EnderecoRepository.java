package br.com.adotappet.adotappetapi.domain.repository;

import br.com.adotappet.adotappetapi.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
