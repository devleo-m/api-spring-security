package com.apispringsecurity.datasource.Entity;

import com.apispringsecurity.Controller.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
// implementando o UserDetails que vem do Spring Security
// com a finalidade de sobreecreve o User Interno do Security
// sou obrigado a implementar os métodos do UserDetails
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(unique = true)
    private String nomeUsuario;
    private String senha;

    // Esse relacionamento não gera colunas na tabela usuário
    // Ele gera uma tabela nova entre usuário e perfil
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_papeis",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<PerfilEntity> perfis;

//    @OneToMany(cascade = CascadeType.REMOVE)
//    private List<TarefaEntity> tarefas;


    public boolean senhaValida(LoginRequest loginRequest, BCryptPasswordEncoder bCryptEncoder) {
        return bCryptEncoder.matches(
                loginRequest.senha(),
                this.senha
        );
    }


    /*
     * Métodos do User Details
     *  Sobreecrever os métodos com os nossos dados
     *  Esses métodos são lidos pelo Security
     */
    @Override // Perfis de acesso
    // A collection resultante desse método deve extender a interface GrantedAuthority
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getPerfis();
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getNomeUsuario();
    }

    @Override // Se a conta expira -> false
    // a conta não expira -> true
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override// Se a conta está travada -> true
    // a conta não está travada -> false
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override// Se a credencial expira -> false -> senha expira ?
    // a credencial não expira -> true
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // conta ativa ou não
    public boolean isEnabled() {
        return true;
    }
}
