package br.com.fiap.mba.persistence.spring.persistence.domain.produto;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto, String> {

    Produto findByCodigo(String codigo);

    List<Produto> findAll();
}

