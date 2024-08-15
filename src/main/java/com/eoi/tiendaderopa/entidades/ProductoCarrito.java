package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "ProductoCarrito")
@Getter
@Setter
public class ProductoCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //TODO Revisar cómo contamos las cantidades. En principio, tenemos el quantity, que es el numero de items de un mismo tipo en un carrito.
    private int quantity;

    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((producto == null) ? 0 : producto.hashCode());
        result = prime * result + quantity;
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
        ProductoCarrito other = (ProductoCarrito) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (producto == null) {
            if (other.producto != null)
                return false;
        } else if (!producto.equals(other.producto))
            return false;

        return quantity == other.quantity;
    }

    @Override
    public String toString() {
        return "ProductoCarrito [id=" + id + ", producto=" + producto + "]";
    }


    public void remove(ProductoCarrito productoCarrito) {

    }

    public ProductoCarrito() {}

    public ProductoCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.quantity= cantidad;
    }
}
