package br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente;

import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.*;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto.ClienteDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto.EnderecoDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    @ApiOperation(value = "Realiza o cadastro de um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Cliente já existe")}
            )
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraCliente(@RequestBody ClienteDto clienteDto) {
        try {
            EspecificacaoCliente especificacaoCliente = new EspecificacaoCliente(
                clienteDto.getNome(),
                clienteDto.getCpf(),
                clienteDto.getEnderecos().stream()
                        .map( enderecoDto -> new EspecificacaoEndereco(
                                enderecoDto.getLogradouro(),
                                enderecoDto.getNumero(),
                                enderecoDto.getComplemento(),
                                enderecoDto.getCep(),
                                enderecoDto.getCidade(),
                                enderecoDto.getEstado())
                ).collect(Collectors.toList())
            );

            clienteService.cadastraCliente(especificacaoCliente);
        } catch (ClienteJaExisteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    @ApiOperation(value = "Realiza a consulta de um cadastro de cliente identificado pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente obtido com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")})
    public ClienteDto buscaCliente(@PathVariable String cpf) {

        try {
            Cliente cliente = clienteService.buscaCliente(cpf);

            return new ClienteDto(
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getEnderecos().stream()
                            .map(endereco ->new EnderecoDto(
                                            endereco.getLogradouro(),
                                            endereco.getNumero(),
                                            endereco.getComplemento(),
                                            endereco.getCep(),
                                            endereco.getCidade(),
                                            endereco.getEstado())
                    ).collect(Collectors.toList())
            );
        } catch (ClienteNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping()
    @ApiOperation(value = "Altera os dados de um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente alterado com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")})
    public void alteraCliente(@RequestBody EspecificacaoCliente especificacaoCliente) {
        try {
            clienteService.alteraCliente(especificacaoCliente);
        } catch (ClienteNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{cpf}")
    @ApiOperation(value = "Remove um cadastro de cliente identificado pelo cpf")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente removido com sucesso"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")})
    public void removeCliente(@PathVariable String cpf) {
        try {
            clienteService.removeCliente(cpf);
        } catch (ClienteNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}