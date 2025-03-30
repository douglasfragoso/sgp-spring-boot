package com.tarefas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.dto.LoginDTO;
import com.tarefas.api.dto.UsuarioLoginDTO;
import com.tarefas.api.model.Usuario;
import com.tarefas.api.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class LoginController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private TokenService tokenService;

        @PostMapping("/v1/login")
        ResponseEntity<UsuarioLoginDTO> clientLogin(@Valid @RequestBody LoginDTO authenticationDTO) {
                // Autenticando o usuário
                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                authenticationDTO.getEmail(),
                                                authenticationDTO.getSenha()));

                // Gerando o token
                Usuario loggedUser = (Usuario) authentication.getPrincipal();

                String token = tokenService.generateToken(loggedUser);

                UsuarioLoginDTO userDTO = new UsuarioLoginDTO(loggedUser.getNome(), token);

                return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }

}
