package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;

import java.util.List;

public interface IProdutoService {

    ProdutoDomain criar (ProdutoDomain produtoDomain);

    ProdutoDomain consultarId (Integer id);

    List<ProdutoDomain> consultaAtivos (Boolean ativo);

    ProdutoDomain desativaId (Integer id);
}
