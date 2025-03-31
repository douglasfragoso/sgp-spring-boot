package com.tarefas.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "The field email is required")
    @Size(max = 50, message = "The field email must be up to 50 characters")
    @Email(message = "The field email must be a valid email")
    private String email;

    @NotBlank(message = "The field password is required")
    @Size(min = 6, max = 50, message = "The field password must be between 6 and 50 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

}
