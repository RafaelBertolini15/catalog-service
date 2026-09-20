package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.controller.mapper.ProdutoDtoMapper;
import br.edu.fag.catalogservice.service.IProdutoService;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos/{id}/desativar")
public class DesativaProdutoController {

        private final IProdutoService desativaId;

        public DesativaProdutoController(IProdutoService desativaId) {
            this.desativaId = desativaId;
        }

        @PatchMapping
        public ResponseEntity<ProdutoDTO> desativaId(@PathVariable Integer id) {

            if(id == null || id < 1){
                throw new ProductNotFoundException(id.longValue());
            }

            ProdutoDomain produto = desativaId.desativaId(id);

            ProdutoDTO resposta = ProdutoDtoMapper.toDto(produto);
            return ResponseEntity.ok(resposta);
        }

    }

