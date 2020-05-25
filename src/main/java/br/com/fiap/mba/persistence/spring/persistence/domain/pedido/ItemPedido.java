package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

public class ItemPedido {
    private String id;
    @DBRef
    private Produto produto;
    private Integer quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Integer quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto item) {
        this.produto = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
