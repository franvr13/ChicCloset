package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "metodoPago")
public class MetodoPago implements Serializable {

    @Id
    @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "numero_tarjeta", length = 16)
    private String numero_tarjeta;

    private String caducidad;

    private String cvv;

    public MetodoPago(String nombre, String numero_tarjeta, String caducidad, String cvv) {
        this.nombre = nombre;
        this.numero_tarjeta = numero_tarjeta;
        this.caducidad = caducidad;
        this.cvv = cvv;
    }

    @ManyToOne
    private Usuario usuario;
}
