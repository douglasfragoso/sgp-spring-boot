package com.tarefas.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginDTO {

    @Size(min = 3, max = 20, message = "The field firstname must be between 3 and 20 characters")
    private String nome;

    @Size(min = 10, max = 1000, message = "The field token must be between 10 and 1000 characters")
    private String token;
}
