package br.edu.fag.catalogservice.service;

import br.edu.fag.catalogservice.controller.ProductNotFoundException;
import br.edu.fag.catalogservice.repository.IProdutoRepository;
import br.edu.fag.catalogservice.repository.ProdutoRepository;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.service.mapper.ProdutoEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements IProdutoService{

    private final IProdutoRepository produtoRepository;

    public ProdutoService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ProdutoDomain criar (ProdutoDomain produtoDomain){

        produtoDomain.conferirDescricaoPorValor();
        produtoDomain.conferirTamanhoDoNome();

        ProdutoEntity produtoEntity = ProdutoEntityMapper.toEntity(produtoDomain);
        return produtoRepository.criar(produtoEntity);
    }

    @Override
    public ProdutoDomain consultarId (Integer id){

        ProdutoEntity produtoEntity = produtoRepository.consultarId(id);

        if(produtoEntity == null){
            throw new ProductNotFoundException(id.longValue());
        }

        return ProdutoEntityMapper.toDomain(produtoEntity);
    }

    @Override
    public List<ProdutoDomain> consultaAtivos(Boolean ativo){

        List<ProdutoEntity> produtos = produtoRepository.consultaAtivos(ativo);

        return produtos.stream().map(ProdutoEntityMapper::toDomain).toList();

    }

    @Override
    public ProdutoDomain desativaId(Integer id){

        ProdutoEntity produtoDesativado = produtoRepository.desativaId(id);

        if(produtoDesativado == null){
            throw new ProductNotFoundException(id.longValue());
        }

        return ProdutoEntityMapper.toDomain(produtoDesativado);
    }

}
