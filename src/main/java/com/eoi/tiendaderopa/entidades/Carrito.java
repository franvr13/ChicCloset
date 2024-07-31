package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Carrito")
public class Carrito {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Transient
    private Double precio;

    @Transient
    private int cantidad;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
    private Collection<ProductoCarrito> carrito;

    private String tokenSession;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        Double sum = 0.0;
        for(ProductoCarrito productoCarrito : this.carrito) {
            sum = sum + productoCarrito.getProducto().getPrecio();
        }
        return precio;
    }

    public int getCantidad() {
        return this.cantidad;
    }



    public Collection<ProductoCarrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(Collection<ProductoCarrito> carrito) {
        this.carrito = carrito;
    }

    public String getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }
}
