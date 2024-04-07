package com.apispringsecurity.Controller;

import com.apispringsecurity.Service.UsuarioService;
import com.apispringsecurity.datasource.Entity.UsuarioEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

    public AutenticacaoController(UsuarioService service) {
        this.service = service;
    }

    private final UsuarioService service;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioEntity> registrar(@RequestBody UsuarioEntity dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.registrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioEntity dto){
        return  ResponseEntity.status(HttpStatus.OK).body(service.login(dto));
    }
}
