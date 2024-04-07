package com.apispringsecurity.Controller;


import com.apispringsecurity.Controller.dto.request.InserirTarefaRequest;
import com.apispringsecurity.Controller.dto.response.TarefaResponse;
import com.apispringsecurity.Service.TarefaService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> retornarTarefas(
            @RequestHeader(name = "Authorization") String token // Bearer ahsdjkahkjdahjksd
    ){
        String tokenReal = token.split(" ")[1];
        return ResponseEntity.ok(tarefaService.retornaTarefas(token.substring(7)));
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> retornarTarefas(
            @RequestHeader(name = "Authorization") String token, // Bearer ahsdjkahkjdahjksd
            @RequestBody InserirTarefaRequest incluiTarefaRequest
    ){
        return ResponseEntity.ok(tarefaService.salvaTarefa(incluiTarefaRequest,token.substring(7)));
    }
}
