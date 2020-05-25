package br.com.fiap.mba.persistence.spring.persistence.entrypoints.cliente.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ClienteDto {
    @ApiModelProperty(example = "Bruno Farias")
    private String nome;
    private String cpf;
    private List<EnderecoDto> enderecos;

    public ClienteDto(String nome, String cpf, List<EnderecoDto> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<EnderecoDto> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDto> enderecos) {
        this.enderecos = enderecos;
    }
}
