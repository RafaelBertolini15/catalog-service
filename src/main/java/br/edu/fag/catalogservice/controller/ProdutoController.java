package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.controller.mapper.ProdutoDtoMapper;
import br.edu.fag.catalogservice.service.IProdutoService;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final IProdutoService produtoService;

    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO produtoDTO) {

        String nome = produtoDTO.getNome();
        String descricao = produtoDTO.getDescricao();

        // Validações básicas dos dados recebidos
        if (nome == null || nome.isBlank()) {
            throw new RuntimeException("O nome do produto é obrigatório.");
        }

        if (produtoDTO.getPreco() == null) {
            throw new RuntimeException("O preço do produto deve ser um número válido.");
        }

        if (produtoDTO.getPreco() <= 0) {
            throw new RuntimeException("O preço do produto deve ser maior que zero.");
        }

        nome = nome.trim();
        descricao = descricao == null ? null : descricao.trim();
        produtoDTO.setNome(nome);
        produtoDTO.setDescricao(descricao);

        ProdutoDomain produtoDomain = ProdutoDtoMapper.toDomain(produtoDTO);
        ProdutoDomain produtoCriado = produtoService.criar(produtoDomain);

        ProdutoDTO resposta = ProdutoDtoMapper.toDto(produtoCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);

    }
}
