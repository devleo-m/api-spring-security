package com.apispringsecurity.Service;

import com.apispringsecurity.datasource.Entity.UsuarioEntity;

public interface UsuarioService {

    void delete(String token,Long id);
    UsuarioEntity update (String token,Long id,UsuarioEntity entity);
    UsuarioEntity getEntity (String token,Long id);

    UsuarioEntity registrar(UsuarioEntity dto);

    String login(UsuarioEntity dto);

}
