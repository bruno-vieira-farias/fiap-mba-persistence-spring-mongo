package br.com.fiap.mba.persistence.spring.persistence.domain.cliente;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, String> {

    Cliente findByCpf(String cpf);

    void removeByCpf(String cpf);

}

