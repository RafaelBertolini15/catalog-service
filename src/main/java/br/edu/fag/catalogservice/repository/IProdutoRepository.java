package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

import java.util.List;

public interface IProdutoRepository {

    ProdutoDomain criar (ProdutoEntity produtoEntity);

    ProdutoEntity consultarId (Integer id);

    List<ProdutoEntity> consultaAtivos (Boolean ativo);
}
