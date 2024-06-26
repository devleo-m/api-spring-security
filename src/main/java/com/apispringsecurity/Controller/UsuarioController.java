package com.apispringsecurity.Controller;

import com.apispringsecurity.Service.UsuarioService;
import com.apispringsecurity.datasource.Entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getOne(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getEntity(token,id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> update(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id,
            @RequestBody UsuarioEntity entity
    ){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(token,id,entity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(name = "Authorization") String token,
                                       @PathVariable(name = "id") Long id){
        usuarioService.delete(token,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
