package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Carrito {


    //TODO Repasar esta clase, y poner las relaciones correctamente con las demás.
    // Poner los dos lados de la relación a ser posible y como recomendación, nunca poner el column_name, y dejarlo que hibernate escoja.


    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private Double precio;

    @OneToMany( mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoCarrito> listaProductosCarrito = new ArrayList<>();


    private String tokenSession;



    public int getCantidad() {
        return this.listaProductosCarrito.size();
    }

    public List<ProductoCarrito> getProducto() {
        return listaProductosCarrito;
    }


    public Double getPrecio() {
        Double sum = 0.0;
        for (ProductoCarrito productoCarrito : this.listaProductosCarrito) {
            sum += productoCarrito.getProducto().getPrecio() * productoCarrito.getQuantity();
        }
        return sum;
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
