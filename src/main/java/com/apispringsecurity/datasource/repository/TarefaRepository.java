package com.apispringsecurity.datasource.repository;

import com.apispringsecurity.datasource.Entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
    List<TarefaEntity> findAllByUsuarioId(Long idUsuario);
}
