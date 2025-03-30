package com.tarefas.api.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tarefas.api.constants.Prioridade;
import com.tarefas.api.constants.StatusTarefa;
import com.tarefas.api.dto.TarefaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TAREFA")
    private Long id;

    @Column(name = "TITULO_TAREFA", nullable = false)
    private String titulo;

    @Column(name = "DESC_TAREFA", columnDefinition = "TEXT")
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_CRIACAO_TAREFA", nullable = false)
    private LocalDate dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_CONCLUSAO_TAREFA")
    private LocalDate dataConclusao;

    @Column(name = "PRIORIDADE_TAREFA", nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "STATUS_TAREFA", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Projeto projeto;

    public TarefaDTO toDTO() {
        TarefaDTO dto = new TarefaDTO();

        dto.setId(id);
        dto.setTitulo(titulo);
        dto.setDescricao(descricao);
        dto.setDataCriacao(dataCriacao);
        dto.setDataConclusao(dataConclusao);
        dto.setProjeto(projeto);

        Period periodo = null;

        if (Objects.isNull(dataConclusao)) {
            periodo = Period.between(dataCriacao, LocalDate.now());
        } else {
            periodo = Period.between(dataCriacao, dataConclusao);
        }

        int qtdeDiasTrabalhados = periodo.getDays() + periodo.getMonths() * 30 + periodo.getYears() * 365;

        dto.setQtdeDiasTrabalhados(qtdeDiasTrabalhados);
        dto.setPrioridade(prioridade);
        dto.setStatus(status);

        return dto;
    }

}
