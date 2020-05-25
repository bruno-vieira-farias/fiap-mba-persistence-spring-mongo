package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

/**
 * VO resposável por transferir as informaçoes entre outras camadas do sistema.
 */
public class EspecificacaoItemPedido {
    private String codigoProduto;
    private Integer quantidade;

    public EspecificacaoItemPedido(String codigoProduto, Integer quantidade) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
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
