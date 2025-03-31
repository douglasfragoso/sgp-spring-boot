package com.tarefas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tarefas.api.repository.UsuarioRepository;


//Usado implicitamente pelo Spring Security para autenticação - Used implicitly by Spring Security for authentication
@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    //Permite a busca de um usuário por email - Allows the search for a user by email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
