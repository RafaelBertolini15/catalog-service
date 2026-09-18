package br.edu.fag.catalogservice.service.domain;

import br.edu.fag.catalogservice.controller.ProductNotFoundException;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;

public class ProdutoDomain {

    private Integer id;

    private String nome;

    private Double preco;

    private String descricao;

    private Boolean ativo;

    private static final double PRECO_DE_PRODUTO_DE_ALTO_VALOR = 1000.00;

    public void conferirTamanhoDoNome(){
        if (nome.length() < 3) {
        throw new RuntimeException("O nome do produto deve ter pelo menos 3 caracteres.");
        }
    }

    public void conferirDescricaoPorValor (){
        if (preco >= PRECO_DE_PRODUTO_DE_ALTO_VALOR && (descricao == null || descricao.isBlank())) {
        throw new RuntimeException("Produtos a partir de R$ 1.000,00 devem possuir uma descrição.");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
