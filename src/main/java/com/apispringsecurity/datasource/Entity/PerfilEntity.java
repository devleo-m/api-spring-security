package com.apispringsecurity.datasource.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "perfil")
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_perfil;

    private String nome_perfil;

}
