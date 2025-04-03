package com.tarefas.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tarefas.api.constants.StatusTarefa;
import com.tarefas.api.dto.ProjetoDTO;
import com.tarefas.api.model.Projeto;
import com.tarefas.api.model.Tarefa;
import com.tarefas.api.repository.ProjetoRepository;
import com.tarefas.api.repository.TarefaRepository;

@Service
public class ProjetoService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Page<Projeto> listarProjetos(Pageable paginacao) {
        return projetoRepository.findAll(paginacao);
    }

    public ProjetoDTO buscarProjetoPeloId(Long id) {
        Optional<Projeto> projetoOpt = projetoRepository.findById(id);

        if (projetoOpt.isPresent()) {
            return _toDTO(projetoOpt.get());
        }

        return null;
    }

    public List<ProjetoDTO> buscarProjetoPeloResponsavelId(Long id) {
        List<Projeto> projetos = projetoRepository.findByResponsavel_Id(id);
        return projetos.stream().map(p -> _toDTO(p)).toList();

    }

    public void deletarProjeto(Long id) {
        projetoRepository.deleteById(id);
    }

    public Projeto atualizarProjeto(Long id, Projeto dadosProjeto) {
        Optional<Projeto> projetoOpt = projetoRepository.findById(id);

        if (projetoOpt.isPresent()) {
            Projeto projeto = projetoOpt.get();

            projeto.setNome(dadosProjeto.getNome());
            projeto.setDescricao(dadosProjeto.getDescricao());
            projeto.setResponsavel(dadosProjeto.getResponsavel());

            return projetoRepository.save(projeto);
        }

        return null;
    }

    private ProjetoDTO _toDTO(Projeto projeto) {
        ProjetoDTO dto = new ProjetoDTO();

        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setResponsavel(projeto.getResponsavel());

        List<Tarefa> tarefas = tarefaRepository.findByProjeto_Id(projeto.getId());
        dto.setTarefas(tarefas);

        List<Tarefa> pendentes = tarefas.stream()
                .filter(tarefa -> StatusTarefa.PENDENTE.equals(tarefa.getStatus()))
                .collect(Collectors.toList());

        List<Tarefa> emAndamento = tarefas.stream()
                .filter(tarefa -> StatusTarefa.FAZENDO.equals(tarefa.getStatus()))
                .collect(Collectors.toList());

        List<Tarefa> finalizadas = tarefas.stream()
                .filter(tarefa -> StatusTarefa.FINALIZADA.equals(tarefa.getStatus()))
                .collect(Collectors.toList());

        dto.setQtdTarefasPendentes(pendentes.size());
        dto.setQtdTarefasEmAndamento(emAndamento.size());
        dto.setQtdTarefasFinalizadas(finalizadas.size());

        return dto;
    }

}
