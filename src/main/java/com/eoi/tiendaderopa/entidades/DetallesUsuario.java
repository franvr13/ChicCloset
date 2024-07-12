package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "detallesUsuario")
public class DetallesUsuario implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nombre", length = 25)
    private String Nombre;

    @Column(name = "apellido", length = 50)
    private String Apellido;

    @Column(name = "direccion", length = 250)
    private String Direccion;

    @Column(name = "fechaNacimiento")
    private LocalDateTime FechaNacimiento;

    @Column(name = "dni", length = 10)
    private String Dni;

    @OneToOne
    @JoinColumn(name="usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
