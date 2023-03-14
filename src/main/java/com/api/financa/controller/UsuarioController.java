package com.api.financa.controller;

import com.api.financa.dto.UsuarioDto;
import com.api.financa.model.entity.Usuario;
import com.api.financa.repository.UsuarioRepository;
import com.api.financa.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> readAllUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuarioDto.setSenha(encoder.encode(usuarioDto.getSenha()));
        BeanUtils.copyProperties(usuarioDto, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.createUsuario(usuario));
    }

    @PostMapping("/authUser")
    public ResponseEntity<Object> authUser(@RequestBody @Valid UsuarioDto usuarioDto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.authUser(usuarioDto));

    }

}
