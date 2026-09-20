package br.edu.fag.catalogservice.controller;

import br.edu.fag.catalogservice.controller.dto.ProdutoDTO;
import br.edu.fag.catalogservice.controller.mapper.ProdutoDtoMapper;
import br.edu.fag.catalogservice.service.IProdutoService;
import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/produtos/{id}")
    public class ConsultaIdController {
        private final IProdutoService consultaService;

        public ConsultaIdController(IProdutoService consultaService) {
            this.consultaService = consultaService;
        }

        @GetMapping
        public ResponseEntity<ProdutoDTO> consultar(@PathVariable Integer id) {

            if (id == null || id < 1){
                throw new ProductNotFoundException(id.longValue());
            }

            ProdutoDomain produto = consultaService.consultarId(id);

            ProdutoDTO resposta = ProdutoDtoMapper.toDto(produto);
            return ResponseEntity.ok(resposta);
        }
    }

