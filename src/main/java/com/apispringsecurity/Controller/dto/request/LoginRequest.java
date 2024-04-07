package com.apispringsecurity.Controller.dto.request;

public record LoginRequest (
        String nomeUsuario,
        String senha
){
}
