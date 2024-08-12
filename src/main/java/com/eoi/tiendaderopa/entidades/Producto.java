package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "proveedor", length = 45)
    private String proveedor;

    @Column(name = "material", length = 45)
    private String material;

    @Column(name = "color", length = 45)
    private String color;

    @Column(name = "descripcion", length = 250)
    private String descripcion;

    @Column(name = "foto_url", length = 255)
    private String fotoUrl;

    @Column(name = "cantidad_stock")
    private int cantidadStock;

    @Column(name = "precio")
    private double precio;

    public void add(ProductoDeseado productoDeseados) {

    }
}
