package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

public class PedidoNaoEncontradoException extends Exception {
    public PedidoNaoEncontradoException(String id) {
        super("Pedido com o código " + id + "não encontrado.");
    }
}
