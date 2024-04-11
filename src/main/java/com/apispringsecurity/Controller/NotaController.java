package com.apispringsecurity.Controller;

import com.apispringsecurity.Service.NotaService;
import com.apispringsecurity.datasource.Entity.NotaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    @GetMapping("/caderno/{id}")
    public ResponseEntity<List<NotaEntity>> getAll(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long idCaderno
    ){
        return ResponseEntity.status(HttpStatus.OK).body(notaService.getEntities(token,idCaderno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaEntity> getOne(@RequestHeader(name = "Authorization") String token,
                                             @PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(notaService.getEntity(token,id));
    }

    @PostMapping()
    public ResponseEntity<NotaEntity> create(@RequestHeader(name = "Authorization") String token,
                                             @RequestBody NotaEntity entity) {
        try {
            NotaEntity notaEntity = notaService.create(token, entity);
            return ResponseEntity.status(HttpStatus.OK).body(notaEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaEntity> update(@RequestHeader(name = "Authorization") String token,
                                             @PathVariable(name = "id") Long id,
                                             @RequestBody NotaEntity entity){
        return ResponseEntity.status(HttpStatus.OK).body(notaService.update(token,id,entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(name = "Authorization") String token,
                                       @PathVariable(name = "id") Long id){
        notaService.delete(token,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}