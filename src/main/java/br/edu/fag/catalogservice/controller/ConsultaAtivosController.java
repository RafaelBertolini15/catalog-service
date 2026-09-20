package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.controller.mapper.ProdutoDtoMapper;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.service.IProdutoService;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ConsultaAtivosController {

    private final IProdutoService consultaAtivo;

    public ConsultaAtivosController(IProdutoService consultaAtivo) {
        this.consultaAtivo = consultaAtivo;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> consultaAtivo(@RequestParam ("ativo") Boolean ativo){

        List<ProdutoDomain> produtos = consultaAtivo.consultaAtivos(ativo);

        List<ProdutoDTO> resposta = produtos.stream().map(ProdutoDtoMapper::toDto).toList();

        return ResponseEntity.ok(resposta);
    }


}
