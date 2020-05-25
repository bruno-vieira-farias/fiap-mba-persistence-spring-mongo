package br.com.fiap.mba.persistence.spring.persistence.domain.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProdutoJaCadastradoException extends Exception {
    public ProdutoJaCadastradoException(String codigo) {
        super("O produto de código" + codigo + "já esta cadastrado.");
    }
}
