package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Transient
    private Double precio;

    @Transient
    private int cantidad;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
    private Collection<ProductoCarrito> carrito;

    private String tokenSession;

    public Carrito() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return fecha;
    }

    public void setDate(Date fecha) {
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
        return this.getCantidad();
    }

    public Collection<ProductoCarrito> getProducto() {
        return getProducto();
    }

    public void setCarrito(Collection<ProductoCarrito> productoCarritos) {
        this.carrito = productoCarritos;
    }

    public String getTokenSession() {
        return tokenSession;
    }

    public void setTokenSession(String tokenSession) {
        this.tokenSession = tokenSession;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((getProducto() == null) ? 0 : getProducto().hashCode());
        result = prime * result + ((tokenSession == null) ? 0 : tokenSession.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Carrito other = (Carrito) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (getProducto() == null) {
            if (other.getProducto() != null)
                return false;
        } else if (!getProducto().equals(other.getProducto()))
            return false;
        if (tokenSession == null) {
            if (other.tokenSession != null)
                return false;
        } else if (!tokenSession.equals(other.tokenSession))
            return false;
        return true;
    }

}
