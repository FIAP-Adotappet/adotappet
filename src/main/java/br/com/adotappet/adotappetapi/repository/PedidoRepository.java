package br.com.adotappet.adotappetapi.repository;

import br.com.adotappet.adotappetapi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
