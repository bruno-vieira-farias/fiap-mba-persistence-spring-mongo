package br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto;

import br.com.fiap.mba.persistence.spring.persistence.domain.produto.Produto;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.ProdutoJaCadastradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.ProdutoNaoEncontradoException;
import br.com.fiap.mba.persistence.spring.persistence.domain.produto.ProdutoService;
import br.com.fiap.mba.persistence.spring.persistence.entrypoints.produto.dto.ProdutoDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
@Api(description = "Entrypoint para manipulação de produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping()
    @ApiOperation(value="Realiza o cadastro de um novo produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Produto já cadastrado")})
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraProduto(@RequestBody ProdutoDto produtoDto) {
        try {
            produtoService.cadastraProduto(
                    produtoDto.getCodigo(),
                    produtoDto.getDescricao(),
                    produtoDto.getValor()
            );
        } catch (ProdutoJaCadastradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping()
    @ApiOperation(value="Realiza a consulta de todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos obtidos com sucesso")})
    public List<ProdutoDto> buscaProdutos() {
        return produtoService.buscaProdutos().stream()
                .map(produto ->
                        new ProdutoDto(
                                produto.getCodigo(),
                                produto.getDescricao(),
                                produto.getValor())
                ).collect(Collectors.toList());
    }

    @GetMapping("{codigo}")
    @ApiOperation(value="Realiza a consulta de um produto através do código de identificação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto obtido com sucesso"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    public ProdutoDto buscaProduto(@PathVariable String codigo) {

        try {
            Produto produto = produtoService.buscaProduto(codigo);

            return new ProdutoDto(
                    produto.getCodigo(),
                    produto.getDescricao(),
                    produto.getValor());
        } catch (ProdutoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping()
    @ApiOperation(value="Realiza a alteração de um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto alterado com sucesso"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    public void alteraProduto(@RequestBody ProdutoDto produtoDto) {
        try{
            produtoService.alteraProduto(produtoDto.getCodigo(), produtoDto.getDescricao(), produtoDto.getValor());
        } catch (ProdutoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("{codigo}")
    @ApiOperation(value="Remove um produto identificado pelo código do produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto removido com sucesso"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    public void apagaProduto(@PathVariable String codigo) {
        try{
            produtoService.apagaProduto(codigo);
        } catch (ProdutoNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}