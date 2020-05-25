package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto;

import br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto.dto.ProdutoDto;

public class ConsultaItemPedidoDto {
    private ProdutoDto item;
    private Integer quantidade;

    public ConsultaItemPedidoDto(ProdutoDto item, Integer quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ProdutoDto getItem() {
        return item;
    }

    public void setItem(ProdutoDto item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
