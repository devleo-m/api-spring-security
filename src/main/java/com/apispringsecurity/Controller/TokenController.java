package com.apispringsecurity.Controller;

import com.apispringsecurity.Controller.dto.request.LoginRequest;
import com.apispringsecurity.Controller.dto.response.LoginResponse;
import com.apispringsecurity.datasource.Entity.PerfilEntity;
import com.apispringsecurity.datasource.Entity.UsuarioEntity;
import com.apispringsecurity.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final BCryptPasswordEncoder bCryptEncoder;
    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;

    private static long TEMPO_EXPIRACAO = 36000L; //contante de tempo de expiração em milisegundos
            //Static?

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> token(@RequestBody LoginRequest loginRequest) throws Exception {
        UsuarioEntity usuarioEntity = usuarioRepository.findByNomeUsuario(loginRequest.nomeUsuario())
                .orElseThrow(() -> new Exception("Erro, usuário não existe")); //método sem parametros

        if (!usuarioEntity.senhaValida(loginRequest, bCryptEncoder)) { //valida se usuario existe e se esta com login correto
            throw new Exception("Erro ao Logar, senha incorreta");
        }

        Instant now = Instant.now(); // momento atual

        //escopo do token
        String scope = usuarioEntity.getPerfis() //papés do usuário - ou funções do usuário
                .stream()
                .map(PerfilEntity::getNomePerfil)// Nomes dos papéis do usuário
                .collect(Collectors.joining(" ")); //concatena cada item encontrado como uma string separada por " "

        // campos do token
        JwtClaimsSet claims = JwtClaimsSet.builder() // campos do JWT
                .issuer("aplicacaodemo") // emissor -> corpo JWT
                .issuedAt(now) // criado no momento atual
                .expiresAt(now.plusSeconds(TEMPO_EXPIRACAO)) // expira em 36000L milisegundos
                .subject(usuarioEntity.getId().toString()) //nome do usuário
                .claim("scope", scope) // cria um campo dentro do corpo do JWT
                .build();

        var valorJWT = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        //Cria um JWT em string com os campos definidos anteriormente
        return ResponseEntity.ok(
                new LoginResponse(valorJWT, TEMPO_EXPIRACAO)
        );
    }
}
