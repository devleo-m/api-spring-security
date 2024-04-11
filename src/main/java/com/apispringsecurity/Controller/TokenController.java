package com.apispringsecurity.Controller;

import com.apispringsecurity.Controller.dto.request.LoginRequest;
import com.apispringsecurity.datasource.Entity.UsuarioEntity;
import com.apispringsecurity.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder; //DECIFRAR SENHAS
    private final JwtEncoder jwtEncoder; //codificar um id
    private final UsuarioRepository usuarioRepository;

    private static long EXPIRATION_TIME = 60 * 60 * 24 * 7; //contante de tempo de expiracao

    @PostMapping("/login")
    public ResponseEntity<> gerarToken(@RequestBody LoginRequest loginRequest) throws Exception{

        UsuarioEntity usuarioEntity = usuarioRepository.findByNameUsuario(loginRequest.nomeUsuario());

    }

}
