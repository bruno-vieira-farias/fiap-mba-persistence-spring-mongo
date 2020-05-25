package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoRepository extends CrudRepository<Pedido, String> {

    List<Pedido> findByCliente(Cliente cliente);
}
