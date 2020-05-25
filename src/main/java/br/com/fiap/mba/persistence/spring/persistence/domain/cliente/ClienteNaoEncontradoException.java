package br.com.fiap.mba.persistence.spring.persistence.domain.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends Exception {
    public ClienteNaoEncontradoException(String cpf) {
        super("O cliente de cpf " + cpf + "não foi encontrado.");
    }
}
