package com.apispringsecurity.Service;

import com.apispringsecurity.datasource.Entity.NotaEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface NotaService {

    NotaEntity create (String token,NotaEntity entity) throws BadRequestException;
    void delete(String token,Long id);
    NotaEntity update (String token,Long id,NotaEntity entity);
    NotaEntity getEntity (String token,Long id);
    List<NotaEntity> getEntities (String token);
    List<NotaEntity> getEntities(String token,Long idCaderno);

}
