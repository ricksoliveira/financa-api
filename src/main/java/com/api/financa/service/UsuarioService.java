package com.api.financa.service;

import com.api.financa.dto.UsuarioDto;
import com.api.financa.model.entity.Usuario;
import com.api.financa.repository.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    public static final String TOKEN_SENHA = "b959d86d-0112-4bdb-826f-8ffef40f5552";

    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Map<String, Object> authUser(UsuarioDto usuarioDto){

        log.debug("Autenticando usuário [{}]", usuarioDto.getUsername());
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(usuarioDto.getUsername());

        Map<String, Object> authUserResponse = new HashMap<>();

        if(optionalUsuario.isEmpty()){
            log.warn("Usuário [{}] não encontrado", usuarioDto.getUsername());
            authUserResponse.put("status", HttpStatus.NOT_FOUND);
            authUserResponse.put("response", "Usuário não encontrado!");
            return authUserResponse;
        }

        log.warn("Usuário [{}] encontrado", usuarioDto.getUsername());
        boolean valid = encoder.matches(usuarioDto.getSenha(), optionalUsuario.get().getSenha());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        log.info("Autenticando usuário [{}]", usuarioDto.getUsername());
        if(valid){
            String token = JWT.create()
                    .withSubject(usuarioDto.getUsername())
                    .sign(Algorithm.HMAC512(TOKEN_SENHA));

            log.info("Usuário [{}] autenticado, iniciando login", usuarioDto.getUsername());
            authUserResponse.put("status", status);
            authUserResponse.put("response", token);
            return authUserResponse;
        }
        else{
            log.info("Usuário [{}] não autenticado. Senha incorreta", usuarioDto.getUsername());
            authUserResponse.put("status", status);
            authUserResponse.put("response", "Senha incorreta!");
            return authUserResponse;
        }
    }

}
