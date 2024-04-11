package com.apispringsecurity.Service.impl;

import com.apispringsecurity.Service.CadernoService;
import com.apispringsecurity.datasource.Entity.CadernoEntity;
import com.apispringsecurity.datasource.repository.CadernoRepository;
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
public class CadernoServiceImpl implements CadernoService {

    private final CadernoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final JwtDecoder jwtDecoder;

    @Override
    public CadernoEntity create(String token, CadernoEntity entity) {
        usuarioRepository.findById(entity.getUsuario()
                .getUsuario_id()).orElseThrow(() -> new BadRequestException("Elemento associado não existe"));
        return repository.save(entity);
    }

    @Override
    public void delete(String token,Long id) {

        try{
            var cadernoEntity = repository.findById(id).orElseThrow(() -> new BadRequestException("Elemento não encontrado"));

            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(cadernoEntity.getUsuario().getUsuario_id() !=  Long.parseLong(attributes.getSubject())){
                throw new ForbiddenException("Erro na autenticação");
            }

            repository.deleteById(id);

        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }

    }

    @Override
    public CadernoEntity update(String token,Long id, CadernoEntity entity) {

        try{
            var cadernoEntity = repository.findById(id).orElseThrow(() -> new BadRequestException("Elemento não encontrado"));

            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(cadernoEntity.getUsuario().getUsuario_id() !=  Long.parseLong(attributes.getSubject())){
                throw new ForbiddenException("Erro na autenticação");
            }

            if (entity.getNome_caderno() != null &&  !entity.getNome_caderno().isEmpty()){
                cadernoEntity.setNome_caderno(entity.getNome_caderno());
            }

            return repository.save(cadernoEntity);

        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }
    }

    @Override
    public CadernoEntity getEntity(String token,Long id) {
        try{
            var entity = repository.findById(id).orElseThrow(() -> new BadRequestException("Elemento não encontrado"));

            token = TokenUtils.recoverToken(token);
            TokenUtils.TokenAttributes attributes = TokenUtils.parseToken(token);

            if(entity.getUsuario().getUsuario_id() !=  Long.parseLong(attributes.getSubject())){
                throw new ForbiddenException("Erro na autenticação");
            }

            return  entity;

        }catch (ParseException e){
            throw new ForbiddenException("Erro na autenticação");
        }
    }

    @Override
    public List<CadernoEntity> getEntities(String token) {

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
}