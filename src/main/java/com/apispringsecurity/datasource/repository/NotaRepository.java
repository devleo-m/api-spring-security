package com.apispringsecurity.datasource.repository;

import com.apispringsecurity.datasource.Entity.NotaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {


    @Transactional
    @Modifying
    @Query(value = "select * from nota where  usuario_id  = :usuarioId", nativeQuery = true)
    List<NotaEntity> findAllByUsuario(Long usuarioId);

    @Transactional
    @Modifying
    @Query(value = "select * from nota where  usuario_id  = :usuarioId and caderno_id = :cadernoId", nativeQuery = true)
    List<NotaEntity> findAllByUsuarioAndCaderno(Long usuarioId, Long cadernoId);



}
