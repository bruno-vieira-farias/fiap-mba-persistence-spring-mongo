package br.com.fiap.mba.persistence.spring.persistence.domain.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClienteJaExisteException extends Exception {
    public ClienteJaExisteException(String cpf) {
        super("O cliente com o cpf " + cpf + "já existe.");
    }
}
