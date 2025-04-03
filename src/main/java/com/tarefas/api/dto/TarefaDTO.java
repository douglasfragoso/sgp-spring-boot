package com.tarefas.api.dto;

import java.time.LocalDate;

import com.tarefas.api.constants.Prioridade;
import com.tarefas.api.constants.StatusTarefa;
import com.tarefas.api.model.Projeto;
import com.tarefas.api.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    private Long id;
    private String titulo;
    private Projeto projeto;
    private Usuario usuario;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private int qtdeDiasTrabalhados;
    private Prioridade prioridade;
    private StatusTarefa status;

}
