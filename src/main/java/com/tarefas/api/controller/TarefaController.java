package com.tarefas.api.controller;

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

import com.tarefas.api.dto.TarefaDTO;
import com.tarefas.api.model.Tarefa;
import com.tarefas.api.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<Page<Tarefa>> listarTarefas(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.listarTarefas(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> consultarTarefa(@PathVariable("id") Long id) {
        TarefaDTO tarefa = tarefaService.buscarTarefaPeloId(id);

        if (Objects.isNull(tarefa)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable("id") Long id) {
        TarefaDTO tarefa = tarefaService.buscarTarefaPeloId(id);

        if (Objects.isNull(tarefa)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        tarefaService.deletarTarefa(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable("id") Long id, @RequestBody Tarefa dadosTarefa) {
        TarefaDTO tarefa = tarefaService.buscarTarefaPeloId(id);

        if (Objects.isNull(tarefa)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.atualizarTarefa(id, dadosTarefa));
    }

}
