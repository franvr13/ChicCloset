package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "usuarios")
public class Usuario  implements Serializable {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name ="nombre",length = 80)
    private String nombre;

    @Column (name ="contraseña",length = 45)
    private String contraseña;

    @Column (name ="email",length = 70)
    private String email;

    @Column (name ="dni",length = 70)
    private String dni;

    @Column (name ="fechaNaciemiento",length = 70)
    private String fechaNaciemiento;

    @OneToMany(mappedBy = "usuarioPedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Pedido> pedidoUsuario;

    @OneToMany(mappedBy = "usuarioRol", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rol> roles;

}
