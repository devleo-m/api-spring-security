package com.apispringsecurity.Controller;

import com.apispringsecurity.Service.UsuarioService;
import com.apispringsecurity.datasource.Entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AutenticacaoController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioEntity> registrar(@RequestBody UsuarioEntity dto){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.registrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioEntity dto){
        return  ResponseEntity.status(HttpStatus.OK).body(usuarioService.login(dto));
    }
}
