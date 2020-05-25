package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

import java.util.List;

/**
 * VO resposável por transferir as informaçoes entre outras camadas do sistema.
 */
public class EspecificacaoPedido {
    private String cpfCliente;
    private List<EspecificacaoItemPedido> especificacaoItemPedido;

    public EspecificacaoPedido(String cpfCliente, List<EspecificacaoItemPedido> especificacaoItemPedido) {
        this.cpfCliente = cpfCliente;
        this.especificacaoItemPedido = especificacaoItemPedido;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public List<EspecificacaoItemPedido> getEspecificacaoItemPedido() {
        return especificacaoItemPedido;
    }

    public void setEspecificacaoItemPedido(List<EspecificacaoItemPedido> especificacaoItemPedido) {
        this.especificacaoItemPedido = especificacaoItemPedido;
    }
}
