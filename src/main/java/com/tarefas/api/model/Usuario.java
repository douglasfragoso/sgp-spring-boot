package com.tarefas.api.model;

import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tarefas.api.constants.StatusUsuario;
import com.tarefas.api.dto.UsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @NotNull(message = "Campo nome é obrigatório.")
    @Column(name = "NOME_USUARIO", nullable = false)
    private String nome;

    // @Min(value = 0)
    // @Max(value = 100)
    // private int idade;

    @NotNull(message = "Campo cpf é obrigatório.")
    @Column(name = "CPF_USUARIO", nullable = false, unique = true)
    private String cpf;

    @NotNull
    @Column(name = "EMAIL_USUARIO", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "SENHA_USUARIO", nullable = false)
    private String senha;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DT_NASC_USUARIO", nullable = false)
    private LocalDate dataNascimento;

    @NotNull
    @Column(name = "STATUS_USUARIO", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

    public UsuarioDTO toDTO() {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(id);
        dto.setNome(nome);
        dto.setEmail(email);
        dto.setCpf(cpf);
        dto.setDataNascimento(dataNascimento);
        dto.setSenha(senha);

        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dataAtual);

        dto.setIdade(periodo.getYears());
        dto.setStatus(status);

        return dto;
    }

}
