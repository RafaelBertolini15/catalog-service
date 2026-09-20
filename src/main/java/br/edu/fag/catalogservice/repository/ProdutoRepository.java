package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.service.mapper.ProdutoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProdutoRepository implements IProdutoRepository {

    private final ProdutoRepositoryJpa produtoRepositoryJpa;

    public ProdutoRepository(ProdutoRepositoryJpa produtoRepositoryJpa) {
        this.produtoRepositoryJpa = produtoRepositoryJpa;
    };

    @Override
    public ProdutoDomain criar (ProdutoEntity produtoEntity){
        final ProdutoEntity produtoPersistido = produtoRepositoryJpa.save(produtoEntity);

        return ProdutoEntityMapper.toDomain(produtoPersistido);
    };

    @Override
    public ProdutoEntity consultarId(Integer id){

        return produtoRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public List<ProdutoEntity> consultaAtivos (Boolean ativo){

        return produtoRepositoryJpa.findAll()
                .stream()
                .filter(produto -> Boolean.TRUE.equals(produto.getAtivo()))
                .toList();
    }

    @Override
    public ProdutoEntity desativaId (Integer id){

        ProdutoEntity produtoEscolhido = produtoRepositoryJpa.findById(id).orElse(null);

        if(produtoEscolhido != null ){
            produtoEscolhido.setAtivo(false);
            return produtoRepositoryJpa.save(produtoEscolhido);
        }

        return null;
    }
};
