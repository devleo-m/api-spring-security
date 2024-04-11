package com.apispringsecurity.datasource.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "caderno")
public class CadernoEntity  implements Serializable {
//    id, nome, id_usuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_caderno;

    @Column(name = "nome", columnDefinition = "varchar(255)", nullable = false)
    private String nome_caderno;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;


    @OneToMany(mappedBy = "nota", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER) //caderno
    private List<NotaEntity> notas;
}
