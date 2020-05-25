package br.com.fiap.mba.persistence.spring.persistence.domain.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException(String codigo) {
        super("Produto de código " + codigo + " não encontrado");
    }
}
