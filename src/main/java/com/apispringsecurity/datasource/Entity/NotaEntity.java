package com.apispringsecurity.datasource.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "nota")
public class NotaEntity implements Serializable {

    //    Nota: id, title, content, id_caderno, id_usuario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nota_id;

    @Column(name = "titulo", columnDefinition = "varchar(255)", nullable = false)
    private String titulo;

    @Column(name = "conteudo", columnDefinition = "varchar(600)", nullable = false)
    private String conteudo;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_caderno", nullable = false)
    private CadernoEntity caderno;
}