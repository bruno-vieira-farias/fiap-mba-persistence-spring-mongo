package br.com.fiap.mba.persistence.spring.persistence.domain.produto;


import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Objects;


public class Produto {
    @Id
    private String id;
    private String codigo;
    private String descricao;
    private BigDecimal valor;

    public Produto() {
    }

    public Produto( String codigo, String descricao, BigDecimal valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produto(String id, String codigo, String descricao, BigDecimal valor) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produto(String descricao, BigDecimal valor) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }
}
