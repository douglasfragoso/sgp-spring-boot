package com.tarefas.api.model;

import java.util.List;
import java.util.stream.Collectors;

import com.tarefas.api.constants.StatusTarefa;
import com.tarefas.api.dto.ProjetoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROJETO")
    private Long id;

    @Column(name = "NOME_PROJETO", nullable = false)
    private String nome;

    @Column(name = "DESC_PROJETO", columnDefinition = "TEXT")
    private String descricao;

    @OneToMany
    @JoinTable(name = "tb_projetos_tarefas", joinColumns = @JoinColumn(name = "ID_PROJETO"), inverseJoinColumns = @JoinColumn(name = "ID_TAREFA"))
    private List<Tarefa> tarefas;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_RESP")
    private Usuario responsavel;

    public ProjetoDTO toDTO() {
        ProjetoDTO dto = new ProjetoDTO();

        dto.setId(id);
        dto.setNome(nome);
        dto.setDescricao(descricao);
        dto.setResponsavel(responsavel);
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
