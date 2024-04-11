package com.apispringsecurity.Service;

import com.apispringsecurity.datasource.Entity.CadernoEntity;

import java.util.List;

public interface CadernoService {

    CadernoEntity create (String token,CadernoEntity entity);
    void delete(String token,Long id);
    CadernoEntity update (String token,Long id,CadernoEntity entity);
    CadernoEntity getEntity (String token,Long id);
    List<CadernoEntity> getEntities (String token);

}