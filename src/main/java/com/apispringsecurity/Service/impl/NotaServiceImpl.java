package com.apispringsecurity.Service.impl;

import com.apispringsecurity.Service.NotaService;
import com.apispringsecurity.datasource.Entity.NotaEntity;
import com.apispringsecurity.datasource.repository.CadernoRepository;
import com.apispringsecurity.datasource.repository.NotaRepository;
import com.apispringsecurity.datasource.repository.UsuarioRepository;
import com.apispringsecurity.exceptions.Error.BadRequestException;
import com.apispringsecurity.exceptions.Error.ForbiddenException;
import com.apispringsecurity.utils.TokenUtils;
//import org.apache.tomcat.util.json.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
    private final CadernoRepository cadernoRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtDecoder jwtDecoder;

    @Override
    public NotaEntity create(String token,NotaEntity entity) {
        usuarioRepository.findById(entity.getUsuario()
                .getId_usuario())
                .orElseThrow(() -> new BadRequestException("Usuario não existe"));
        cadernoRepository.findById(entity.getCaderno()
                .getId_caderno())
                .orElseThrow(() -> new BadRequestException("Caderno não existe"));
        return repository.save(entity);
    }

    @Override
    public void delete(String token,Long id) {
        try{
            var notaEntity = repository.findById(id).orElseThrow(() -> new BadRequestException("Elemento não encontrado"));

            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(notaEntity.getUsuario().getId_usuario() !=  Long.parseLong(attributes.getSubject())){
                throw new ForbiddenException("Erro na autenticação");
            }

            repository.deleteById(id);

        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }

    }

    @Override
    public NotaEntity update(String token,Long id, NotaEntity entity) {

        try{
            var notaEntity = repository.findById(id)
                    .orElseThrow(() -> new BadRequestException("Nota não encontrada"));

            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(notaEntity.getUsuario().getId_usuario() !=  Long.parseLong(attributes.getSubject())){
                throw new ForbiddenException("Erro na autenticação");
            }

            if (entity.getTitulo() != null &&  !entity.getTitulo().isEmpty()){
                notaEntity.setTitulo(entity.getTitulo());
            }

            if (entity.getConteudo() != null &&  !entity.getConteudo().isEmpty()){
                notaEntity.setConteudo(entity.getConteudo());
            }
            return repository.save(notaEntity);

        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }

    }

    @Override
    public NotaEntity getEntity(String token,Long id) {
        try{
            var nota = repository.findById(id)
                    .orElseThrow(() -> new BadRequestException("Nota não encontrada"));

            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(nota.getUsuario().getId_usuario() !=  Long.parseLong(attributes.getSubject())){
                throw new ForbiddenException("Erro na autenticação");
            }

            return nota;
        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }

    }

    @Override
    public List<NotaEntity> getEntities(String token) {
        try{
            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(attributes.getSubject() == null || attributes.getSubject().isEmpty()){
                throw new ForbiddenException("Erro na autenticação");
            }

            return repository.findAllByUsuario(Long.parseLong(attributes.getSubject()));

        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }

    }

    @Override
    public List<NotaEntity> getEntities(String token,Long idCaderno) {
        try{
            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(attributes.getSubject() == null || attributes.getSubject().isEmpty()){
                throw new ForbiddenException("Erro na autenticação");
            }

            return repository.findAllByUsuarioAndCaderno(Long.parseLong(attributes.getSubject()),idCaderno);

        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }
    }
}
