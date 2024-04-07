package com.apispringsecurity.Service;

import com.apispringsecurity.datasource.Entity.NotaEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface NotaService {

    public NotaEntity create (String token,NotaEntity entity) throws BadRequestException;
    public void delete(String token,Long id);
    public NotaEntity update (String token,Long id,NotaEntity entity);
    public NotaEntity getEntity (String token,Long id);
    public List<NotaEntity> getEntities (String token);
    public List<NotaEntity> getEntities(String token,Long idCaderno);

}
