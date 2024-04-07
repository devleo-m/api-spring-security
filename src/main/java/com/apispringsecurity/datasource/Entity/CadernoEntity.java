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
    private long caderno_id;

    @Column(name = "nome", columnDefinition = "varchar(255)", nullable = false)
    private String nome;


    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;


    @OneToMany(mappedBy = "caderno", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<NotaEntity> notas;
}
