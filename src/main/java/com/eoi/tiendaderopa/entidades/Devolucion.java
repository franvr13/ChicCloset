package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "devolucion")
public class Devolucion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Set<Producto> productos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venta_id", referencedColumnName = "id")
    private Set<Venta> ventas;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unidad")
    private double precio_unidad;


    @Column(name = "comentario")
    private String comentario;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    private Factura factura;

}