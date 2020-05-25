package br.com.fiap.mba.persistence.spring.persistence.entrypoints.demo.dto;

import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;

public class ResultadoDemonstracaoDto {
    private Cliente cliente;
    private Produto produto;
    private Estoque estoque;
    private Pedido pedido;

    public ResultadoDemonstracaoDto(Cliente cliente, Produto produto, Estoque estoque, Pedido pedido) {
        this.cliente = cliente;
        this.produto = produto;
        this.estoque = estoque;
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
