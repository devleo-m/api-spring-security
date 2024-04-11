package com.apispringsecurity.datasource.Entity;

import com.apispringsecurity.Controller.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;

    @Column(unique = true, nullable = false)
    private String nome_usuario;
    private String login_usuario;
    private String senha_usuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<CadernoEntity> cadernos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<NotaEntity> notas;

    /*senhaValida
    *
    *Valida login com a senha criptografada salva no banco
    */

    public boolean senhaValida(LoginRequest loginRequest, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return bCryptPasswordEncoder.matches(loginRequest.senha(), this.senha_usuario);
    }

    public long getUsuario_id() {
        return id_usuario;
    }

    public void setUsuario_id(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome_usuario;
    }

    public void setNome(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getLogin() {
        return login_usuario;
    }

    public void setLogin(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getSenha() {
        return senha_usuario;
    }

    public void setSenha(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }
}
