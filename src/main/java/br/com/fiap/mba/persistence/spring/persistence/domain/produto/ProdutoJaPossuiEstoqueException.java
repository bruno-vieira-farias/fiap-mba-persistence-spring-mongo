package br.com.fiap.mba.persistence.spring.persistence.domain.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProdutoJaPossuiEstoqueException extends Exception {
    public ProdutoJaPossuiEstoqueException(String codigo) {
        super("O cadastro do estoque não pode ser realizado porque o produto com o código " + codigo + " já possui estoque cadastrado.");
    }
}
