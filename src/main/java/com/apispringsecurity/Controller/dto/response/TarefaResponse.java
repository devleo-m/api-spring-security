package com.apispringsecurity.Controller.dto.response;

public record TarefaResponse(Long id,
                             String titulo,
                             String descricao,
                             Boolean finalizada
) {
}
