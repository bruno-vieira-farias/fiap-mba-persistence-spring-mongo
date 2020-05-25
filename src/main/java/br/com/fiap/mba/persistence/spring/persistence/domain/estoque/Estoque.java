package br.com.fiap.mba.persistence.spring.persistence.domain.estoque;

import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 *  Representa cada produto no estoque.
 */
public class Estoque {
    @Id
    private String id;
    @DBRef
    private Produto produto;
    private Integer quantidade;

    public Estoque() {
    }

    public Estoque(Produto produto, Integer quantidade) {
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

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("O estoque de um produto não pode ser negativo.");
        }

        this.quantidade = quantidade;
    }
}
