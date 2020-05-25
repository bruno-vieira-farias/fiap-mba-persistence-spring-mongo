package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto;

public class EmissaoItemPedidoDto {
    private String codigoProduto;
    private Integer quantidade;

    public EmissaoItemPedidoDto() {
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
