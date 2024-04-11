package com.apispringsecurity.Controller;

import com.apispringsecurity.Service.CadernoService;
import com.apispringsecurity.datasource.Entity.CadernoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cadernos")
public class CadernoController {

    private final CadernoService cadernoService;

    @GetMapping()
    public ResponseEntity<List<CadernoEntity>> getAll(@RequestHeader(name = "Authorization") String token){
        return ResponseEntity.status(HttpStatus.OK).body(cadernoService.getEntities(token));
    }

    @PostMapping()
    public ResponseEntity<CadernoEntity> create(@RequestHeader(name = "Authorization") String token,
                                                @RequestBody CadernoEntity entity){
        return ResponseEntity.status(HttpStatus.OK).body(cadernoService.create(token,entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadernoEntity> getOne(@RequestHeader(name = "Authorization") String token,
                                                @PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cadernoService.getEntity(token,id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadernoEntity> update(@RequestHeader(name = "Authorization") String token,
                                                @PathVariable(name = "id") Long id,
                                                @RequestBody CadernoEntity entity){
        return ResponseEntity.status(HttpStatus.OK).body(cadernoService.update(token,id,entity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(name = "Authorization") String token,
                                       @PathVariable(name = "id") Long id){
        cadernoService.delete(token,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
