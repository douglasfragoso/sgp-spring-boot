package com.tarefas.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tarefas.api.dto.ProjetoDTO;
import com.tarefas.api.model.Projeto;
import com.tarefas.api.repository.ProjetoRepository;

@Service
public class ProjetoService {

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
            return projetoOpt.get().toDTO();
        }

        return null;
    }

    public List<ProjetoDTO> buscarProjetoPeloResponsavelId(Long id) {
        List<Projeto> projetos = projetoRepository.findByResponsavel_Id(id);
        return projetos.stream().map(Projeto::toDTO).toList();
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
            projeto.setTarefas(dadosProjeto.getTarefas());

            return projetoRepository.save(projeto);
        }

        return null;
    }

}
