package br.com.fiap.mba.persistence.spring.persistence.entrypoints.demo;

import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.Cliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.ClienteJaExisteException;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.ClienteService;
import br.com.fiap.mba.persistence.spring.persistence.domain.cliente.EspecificacaoCliente;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.EstoqueService;
import br.com.fiap.mba.persistence.spring.persistence.domain.pedido.*;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.*;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.demo.dto.ResultadoDemonstracaoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Collections;

@RestController
@RequestMapping("/demonstracao")
public class DemonstracaoController {
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final EstoqueService estoqueService;
    private final PedidoService pedidoService;

    private Cliente cliente;
    private Produto produto;
    private Estoque estoque;
    private Pedido pedido;

    public DemonstracaoController(ClienteService clienteService, ProdutoService produtoService, EstoqueService estoqueService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.estoqueService = estoqueService;
        this.pedidoService = pedidoService;
    }

    @GetMapping()
    @ApiOperation(value="Realiza um demonstração com as principais operações da API.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Demonstraçãp realizada com sucesso"),
            @ApiResponse(code = 500, message = "Erro ao tentar realizar a demonstração")})
    public ResultadoDemonstracaoDto handle() {
        ResultadoDemonstracaoDto resultado = null;

        try {
            try {
                cliente = cadastraClienteDemonstracao();
                produto = cadastraProdutoDemostracao();
                estoque = cadastraEstoqueDemonstracao(produto);
                pedido = emitePedidoDemonstracao(cliente, produto);

                resultado = new ResultadoDemonstracaoDto(cliente, produto, estoque, pedido);

            } catch (ClienteJaExisteException e) {
                cliente = clienteService.buscaCliente("12345678910");
                produto = produtoService.buscaProduto("5485");
                estoque = estoqueService.buscaEstoqueDoProduto(produto);
                pedido = pedidoService.consultaPedidoDoCliente(cliente).get(0);

                resultado = new ResultadoDemonstracaoDto(cliente, produto, estoque, pedido);
            }
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro ao tentar realizar a demonstração.");
        }

        return resultado;
    }

    private Pedido emitePedidoDemonstracao(Cliente cliente, Produto produto) throws PedidoInvalidoException {
        return pedidoService.emitePedido(new EspecificacaoPedido(
                cliente.getCpf(),
                Collections.singletonList(new EspecificacaoItemPedido(produto.getCodigo(), 2))));
    }

    private Estoque cadastraEstoqueDemonstracao(Produto produto) throws ProdutoNaoEncontradoException, ProdutoJaPossuiEstoqueException {
        return estoqueService.cadastraEstoque(produto.getCodigo(), 100);
    }

    private Produto cadastraProdutoDemostracao() throws ProdutoJaCadastradoException {
        return produtoService.cadastraProduto(
                "5485",
                "Kindle 10a. geração com iluminação embutida – Cor Preta",
                BigDecimal.valueOf(349));
    }

    private Cliente cadastraClienteDemonstracao() throws ClienteJaExisteException {
        return clienteService.cadastraCliente(
                new EspecificacaoCliente(
                        "Raimundo da Silva", "12345678910", "Rua do sucesso", 10, null, "032212222", "São Paulo", "SP"));
    }

}