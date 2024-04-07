package com.apispringsecurity.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
    List<TarefaEntity> findAllByUsuarioId(Long idUsuario);
}
