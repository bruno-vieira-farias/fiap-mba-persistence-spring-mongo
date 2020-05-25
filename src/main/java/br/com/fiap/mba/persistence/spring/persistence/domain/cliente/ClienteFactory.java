package br.com.fiap.mba.persistence.spring.persistence.domain.cliente;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteFactory {

    public Cliente cria(EspecificacaoCliente especificacaoCliente) {
        List<Endereco> enderecos = especificacaoCliente.getEspecificacaoEnderecos().stream().map(
                especificacaoEndereco -> new Endereco(
                        especificacaoEndereco.getLogradouro(),
                        especificacaoEndereco.getNumero(),
                        especificacaoEndereco.getComplemento(),
                        especificacaoEndereco.getCep(),
                        especificacaoEndereco.getCidade(),
                        especificacaoEndereco.getEstado()
                )
        ).collect(Collectors.toList());


        return new Cliente(
                especificacaoCliente.getNome(),
                especificacaoCliente.getCpf(),
                enderecos
        );
    }
}
