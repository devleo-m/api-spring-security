package com.apispringsecurity.Controller;

import com.apispringsecurity.Controller.dto.request.InserirUsuarioRequest;
import com.apispringsecurity.datasource.Entity.UsuarioEntity;
import com.apispringsecurity.datasource.repository.PerfilRepository;
import com.apispringsecurity.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final BCryptPasswordEncoder bCryptEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    @PostMapping("cadastro")
    public ResponseEntity<String> newUser(
            @RequestBody InserirUsuarioRequest inserirUsuarioRequest
    ) throws Exception {
//        PerfilEntity perfilEntity=  perfilRepository.findByNomePerfil("Usuario").get();

        boolean usuarioExistente = usuarioRepository.findByNomeUsuario(inserirUsuarioRequest.nomeUsuario())
                .isPresent();

        if (usuarioExistente){
            throw new Exception("Usuario já existe");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNomeUsuario(inserirUsuarioRequest.nomeUsuario());
        usuario.setSenha(bCryptEncoder.encode(inserirUsuarioRequest.senha()).toString());
//        usuario.setPerfis(Set.of(perfilEntity));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Criado", HttpStatus.CREATED);
    }

}
