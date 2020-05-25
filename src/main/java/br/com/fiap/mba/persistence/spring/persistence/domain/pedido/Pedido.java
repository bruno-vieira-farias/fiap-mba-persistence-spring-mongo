package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.Cliente;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.Objects;


public class Pedido {
    @Id
    private String id;
    @DBRef
    private Cliente cliente;
    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(Cliente cliente, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.itens = itens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}