package br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido;

import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.Pedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.PedidoInvalidoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.PedidoNaoEncontradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.EspecificacaoItemPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.EspecificacaoPedido;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.PedidoService;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto.ClienteDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto.EnderecoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto.ConsultaItemPedidoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto.ConsultaPedidoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.pedido.dto.EmissaoPedidoDto;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto.dto.ProdutoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping()
    @ApiOperation(value="Realiza a emissão de um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido gerado com sucesso"),
            @ApiResponse(code = 400, message = "Pedido possui dados incorretos")})
    @ResponseStatus(HttpStatus.CREATED)
    public void emitePedido(@RequestBody EmissaoPedidoDto emissaoPedidoDto) {
        EspecificacaoPedido especificacaoPedido = new EspecificacaoPedido(
                emissaoPedidoDto.getCpfCliente(),
                emissaoPedidoDto.getItensPedido().stream()
                        .map(item -> new EspecificacaoItemPedido(
                                        item.getCodigoProduto(),
                                        item.getQuantidade()
                                )
                        ).collect(Collectors.toList())
        );

        try {
            pedidoService.emitePedido(especificacaoPedido);
        } catch (PedidoInvalidoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Realiza a consulta de um pedido através do código de identificação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Quantidade em estoque do produto alterada com sucesso"),
            @ApiResponse(code = 404, message = "Pedido não encontrado")})
    public ConsultaPedidoDto consultaPedido(@PathVariable String id) {

        try {
            Pedido pedido = pedidoService.consultaPedido(id);
            ClienteDto clienteDto = new ClienteDto(
                    pedido.getCliente().getNome(),
                    pedido.getCliente().getCpf(),
                    new EnderecoDto(
                            pedido.getCliente().getEndereco().getLogradouro(),
                            pedido.getCliente().getEndereco().getNumero(),
                            pedido.getCliente().getEndereco().getComplemento(),
                            pedido.getCliente().getEndereco().getCep(),
                            pedido.getCliente().getEndereco().getCidade(),
                            pedido.getCliente().getEndereco().getEstado()
                    )
            );

            List<ConsultaItemPedidoDto> itensPedidoDto = pedido.getItens().stream().map(item ->
                    new ConsultaItemPedidoDto(
                            new ProdutoDto(
                                    item.getProduto().getCodigo(),
                                    item.getProduto().getDescricao(),
                                    item.getProduto().getValor()
                            ),
                            item.getQuantidade()
                    )
            ).collect(Collectors.toList());

            return new ConsultaPedidoDto(
                    pedido.getId(),
                    clienteDto,
                    itensPedidoDto
            );
        } catch (PedidoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}