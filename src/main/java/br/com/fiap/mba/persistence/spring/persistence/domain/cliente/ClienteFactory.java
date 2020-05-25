package br.com.fiap.mba.persistence.spring.persistence.domain.cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteFactory {

    public Cliente cria(EspecificacaoCliente especificacaoCliente) {
        Endereco endereco = new Endereco(
                especificacaoCliente.getLogradouro(),
                especificacaoCliente.getNumero(),
                especificacaoCliente.getComplemento(),
                especificacaoCliente.getCep(),
                especificacaoCliente.getCidade(),
                especificacaoCliente.getEstado()
        );

        return new Cliente(
                especificacaoCliente.getNome(),
                especificacaoCliente.getCpf(),
                endereco
        );
    }
}
