package br.com.fiap.mba.persistence.spring.persistence.domain.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PedidoInvalidoException extends Exception {
    public PedidoInvalidoException(String message) {
        super(message);
    }
}
