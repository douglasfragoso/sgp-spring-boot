package com.tarefas.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tarefas.api.dto.TarefaDTO;
import com.tarefas.api.model.Tarefa;
import com.tarefas.api.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Page<Tarefa> listarTarefas(Pageable paginacao) {
        return tarefaRepository.findAll(paginacao);
    }

    public TarefaDTO buscarTarefaPeloId(Long id) {
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(id);

        if (tarefaOpt.isPresent()) {
            return tarefaOpt.get().toDTO();
        }

        return null;
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa dadosTarefa) {
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(id);

        if (tarefaOpt.isPresent()) {
            Tarefa tarefa = tarefaOpt.get();

            tarefa.setTitulo(dadosTarefa.getTitulo());
            tarefa.setDataCriacao(dadosTarefa.getDataCriacao());
            tarefa.setDataConclusao(dadosTarefa.getDataConclusao());
            tarefa.setPrioridade(dadosTarefa.getPrioridade());
            tarefa.setStatus(dadosTarefa.getStatus());

            return tarefaRepository.save(tarefa);
        }

        return null;
    }

}
