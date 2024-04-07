package com.apispringsecurity.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TesteController {

    @GetMapping
    public ResponseEntity testeGet(){
        return ResponseEntity.ok("TESTE");
    }
    @PostMapping
    public ResponseEntity testePost(){
        return ResponseEntity.ok("TESTE");
    }
    @DeleteMapping
    public ResponseEntity testeDelete(){
        return ResponseEntity.ok("TESTE");
    }
    @PutMapping
    public ResponseEntity testePut(){
        return ResponseEntity.ok("TESTE");
    }
}