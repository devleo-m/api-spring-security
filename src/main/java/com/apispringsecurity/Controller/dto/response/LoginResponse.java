package com.apispringsecurity.Controller.dto.response;

public record LoginResponse (String valorJWT, long tempoExpiracao) {
}
