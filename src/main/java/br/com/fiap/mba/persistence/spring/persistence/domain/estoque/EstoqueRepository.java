package br.com.fiap.mba.persistence.spring.persistence.domain.estoque;

import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstoqueRepository extends CrudRepository<Estoque, String> {

    Estoque findByProduto(Produto produto);

    void removeItemEstoqueByProduto(Produto produto);

    List<Estoque> findAll();

}