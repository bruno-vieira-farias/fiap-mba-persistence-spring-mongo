package br.com.fiap.mba.persistence.spring.persistence.domain.cliente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteFactory clienteFactory;

    public ClienteService(ClienteRepository clienteRepository, ClienteFactory clienteFactory) {
        this.clienteRepository = clienteRepository;
        this.clienteFactory = clienteFactory;
    }

    @Transactional
    public Cliente buscaCliente(String cpf) throws ClienteNaoEncontradoException {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException(cpf);
        }
        return cliente;
    }

    @Transactional
    public Cliente cadastraCliente(EspecificacaoCliente especificacaoCliente) throws ClienteJaExisteException {
        certificaQueClientePodeSerCriado(especificacaoCliente);

        Cliente cliente = clienteFactory.cria(especificacaoCliente);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void alteraCliente(EspecificacaoCliente especificacaoCliente) throws ClienteNaoEncontradoException {
        Cliente cliente = buscaCliente(especificacaoCliente.getCpf());

        cliente.setNome(especificacaoCliente.getNome());
        cliente.getEndereco().setLogradouro(especificacaoCliente.getLogradouro());
        cliente.getEndereco().setNumero(especificacaoCliente.getNumero());
        cliente.getEndereco().setComplemento(especificacaoCliente.getComplemento());
        cliente.getEndereco().setCep(especificacaoCliente.getCep());
        cliente.getEndereco().setCidade(especificacaoCliente.getCidade());
        cliente.getEndereco().setEstado(especificacaoCliente.getEstado());

        clienteRepository.save(cliente);
    }

    @Transactional
    public void removeCliente(String cpf) throws ClienteNaoEncontradoException {
        buscaCliente(cpf);
        clienteRepository.removeByCpf(cpf);
    }

    private void certificaQueClientePodeSerCriado(EspecificacaoCliente especificacaoCliente) throws ClienteJaExisteException {
        if (clienteRepository.findByCpf(especificacaoCliente.getCpf()) != null) {
            throw new ClienteJaExisteException(especificacaoCliente.getCpf());
        }
    }
}