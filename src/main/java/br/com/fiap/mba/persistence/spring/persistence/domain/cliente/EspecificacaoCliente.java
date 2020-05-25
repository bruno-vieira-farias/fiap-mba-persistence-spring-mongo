package br.com.fiap.mba.persistence.spring.persistence.domain.cliente;

import java.util.List;

/**
 * VO resposável por transferir as informaçoes entre outras camadas do sistema.
 */
public class EspecificacaoCliente {
    private String nome;
    private String cpf;
    private List<EspecificacaoEndereco> especificacaoEnderecos;

    public EspecificacaoCliente(String nome, String cpf, List<EspecificacaoEndereco> especificacaoEnderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.especificacaoEnderecos = especificacaoEnderecos;
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

    public List<EspecificacaoEndereco> getEspecificacaoEnderecos() {
        return especificacaoEnderecos;
    }

    public void setEspecificacaoEnderecos(List<EspecificacaoEndereco> especificacaoEnderecos) {
        this.especificacaoEnderecos = especificacaoEnderecos;
    }
}
