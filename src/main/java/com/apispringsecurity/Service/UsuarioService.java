package com.apispringsecurity.Service;

import com.apispringsecurity.datasource.Entity.UsuarioEntity;

public interface UsuarioService {

    public void delete(String token,Long id);
    public UsuarioEntity update (String token,Long id,UsuarioEntity entity);
    public UsuarioEntity getEntity (String token,Long id);


    UsuarioEntity registrar(UsuarioEntity dto);

    String login(UsuarioEntity dto);

}
