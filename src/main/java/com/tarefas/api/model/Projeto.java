package com.tarefas.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_RESP")
    private Usuario responsavel;

}
