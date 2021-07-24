package br.com.adotappet.adotappetapi.domain.repository;

import br.com.adotappet.adotappetapi.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
