package br.com.fiap.mba.persistence.spring.persistence.entrypoints.estoque;

import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.Estoque;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.ProdutoJaPossuiEstoqueException;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.ProdutoNaoEncontradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.ProdutoSemEstoqueException;
import br.com.fiap.mba.persistence.spring.persistence.domain.estoque.EstoqueService;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.estoque.dto.EstoqueDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/estoque-produto")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping()
    @ApiOperation(value="Realiza o cadastro de registro no estoque de um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Estoque do produto cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Produto já possui estoque cadastrado"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraProdutoEstoque(@RequestBody EstoqueDto estoqueDto) {
        try {
            estoqueService.cadastraEstoque(estoqueDto.getCodigoProduto(), estoqueDto.getQuantidade());
        } catch (ProdutoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ProdutoJaPossuiEstoqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("{codigoProduto}")
    @ApiOperation(value="Realiza a consulta do estoque de um produto através do código do produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estoque obtido com sucesso"),
            @ApiResponse(code = 404, message = "Produto não possui estoque cadastrado"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    public EstoqueDto buscaEstoqueDoProduto(@PathVariable String codigoProduto) {

        try {
            Estoque estoque = estoqueService.buscaEstoqueDoProduto(codigoProduto);

            if (estoque == null){
                return null;
            }

            return new EstoqueDto(
                    estoque.getProduto().getCodigo(),
                    estoque.getQuantidade());

        } catch (ProdutoSemEstoqueException | ProdutoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping()
    @ApiOperation(value="Realiza a alteração da quantidade de um produto no estoque")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Quantidade em estoque do produto alterada com sucesso"),
            @ApiResponse(code = 404, message = "Produto não possui estoque cadastrado"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    public void alteraQuantidadeProdutoEstoque(@RequestBody EstoqueDto estoqueDto) {
        try {
            estoqueService.alteraQuantidadeEstoque(estoqueDto.getCodigoProduto(), estoqueDto.getQuantidade());
        } catch (ProdutoSemEstoqueException | ProdutoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("{codigoProduto}")
    @ApiOperation(value="Remove o estoque de um produto identificado pelo código do produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estoque do produto removido com sucesso"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    public void removeProdutoEstoque(@PathVariable String codigoProduto) {
        try {
            estoqueService.removeItemEstoque(codigoProduto);
        } catch (ProdutoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping()
    @ApiOperation(value="Realiza a consulta dos estoques de todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estoques dos produtos obtidos com sucesso")
            })
    public List<EstoqueDto> buscaEstoqueDoProduto() {
        List<Estoque> listaEstoque = estoqueService.buscaEstoque();

        if (listaEstoque.isEmpty()){
            return null;
        }

        return listaEstoque.stream().map(estoque ->
                        new EstoqueDto(
                                estoque.getProduto().getCodigo(),
                                estoque.getQuantidade())
                ).collect(Collectors.toList());
    }
}