package br.com.fiap.mba.persistence.spring.persistence.domain.estoque;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProdutoSemEstoqueException extends Exception {
    public ProdutoSemEstoqueException(String codigo) {
        super("O Produto de código " + codigo + " não possui estoque cadastrado");
    }
}
