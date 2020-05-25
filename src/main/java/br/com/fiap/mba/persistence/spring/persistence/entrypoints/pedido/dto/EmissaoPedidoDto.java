package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto;

import java.util.List;

public class EmissaoPedidoDto {
    private String cpfCliente;
    private List<EmissaoItemPedidoDto> itensPedido;

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public List<EmissaoItemPedidoDto> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<EmissaoItemPedidoDto> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
