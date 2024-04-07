package com.apispringsecurity.Controller;

import com.apispringsecurity.Service.CadernoService;
import com.apispringsecurity.datasource.Entity.CadernoEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadernos")
public class CadernoController {

    public CadernoController(CadernoService service) {
        this.service = service;
    }

    private final CadernoService service;

    @GetMapping()
    public ResponseEntity<List<CadernoEntity>> getAll(@RequestHeader(name = "Authorization") String token){
        return ResponseEntity.status(HttpStatus.OK).body(service.getEntities(token));
    }

    @PostMapping()
    public ResponseEntity<CadernoEntity> create(@RequestHeader(name = "Authorization") String token,
                                                @RequestBody CadernoEntity entity){
        return ResponseEntity.status(HttpStatus.OK).body(service.create(token,entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadernoEntity> getOne(@RequestHeader(name = "Authorization") String token,
                                                @PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getEntity(token,id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadernoEntity> update(@RequestHeader(name = "Authorization") String token,
                                                @PathVariable(name = "id") Long id,
                                                @RequestBody CadernoEntity entity){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(token,id,entity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(name = "Authorization") String token,
                                       @PathVariable(name = "id") Long id){
        service.delete(token,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
