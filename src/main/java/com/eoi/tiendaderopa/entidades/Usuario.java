package com.eoi.tiendaderopa.entidades;

import com.eoi.tiendaderopa.Validaciones.ValidEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements Serializable, UserDetails {

    //Constructor creado para poder crear usuarios simples sin todo lo que necesita un usuario
    public Usuario(int id, String contraseña, String email) {
        this.id = id;
        this.contraseña = contraseña;
        this.email = email;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "contraseña", length = 450)
    private String contraseña;

    @ValidEmail
    @Column(name = "email", length = 70)
    private String email;


    @OneToMany(mappedBy = "usuario")
    private Set<DatosFacturacion> datosFacturacion;

    @OneToMany(mappedBy = "usuario")
    private Set<MetodoPago> metodosPago;

    @OneToMany(mappedBy = "usuarioPedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Pedido> pedidosUsuario;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarioRol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private DetallesUsuario detalle;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Rol role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRolNombre().toUpperCase());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return contraseña;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
