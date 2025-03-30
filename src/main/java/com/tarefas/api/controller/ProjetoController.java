package com.tarefas.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.dto.ProjetoDTO;
import com.tarefas.api.model.Projeto;
import com.tarefas.api.service.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<Projeto> cadastrarProjeto(@RequestBody Projeto projeto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.salvarProjeto(projeto));
    }

    @GetMapping
    public ResponseEntity<Page<Projeto>> listarProjetos(Pageable paginacao) {
        return ResponseEntity.ok().body(projetoService.listarProjetos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> buscarProjetoPeloId(@PathVariable("id") Long id) {
        ProjetoDTO projeto = projetoService.buscarProjetoPeloId(id);

        if (Objects.isNull(projeto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(projeto);
    }

    @GetMapping("/responsavel/{id}")
    public ResponseEntity<List<ProjetoDTO>> buscarProjetoPeloResponsavelId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(projetoService.buscarProjetoPeloResponsavelId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable("id") Long id) {
        ProjetoDTO projeto = projetoService.buscarProjetoPeloId(id);

        if (Objects.isNull(projeto)) {
            return ResponseEntity.notFound().build();
        }

        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable("id") Long id, @RequestBody Projeto dadosProjetos) {
        ProjetoDTO projeto = projetoService.buscarProjetoPeloId(id);

        if (Objects.isNull(projeto)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(projetoService.atualizarProjeto(id, dadosProjetos));
    }

}
