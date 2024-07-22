package com.eoi.tiendaderopa.entidades;

import com.eoi.tiendaderopa.dto.CarritoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.datetime.standard.DateTimeContext;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private @NotNull Integer userId;

    @Column(name = "producto_id")
    private @NotNull Long productoId;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Producto producto;


    private int quantity;

    public Carrito() {
    }


    public Carrito(CarritoDTO cartDto, Producto producto, int userId){
        this.userId = userId;
        this.productoId = cartDto.getProductoId();
        this.quantity = cartDto.getQuantity();
        this.producto = producto;
        this.createdDate = new Date();
    }

    public Carrito(@NotNull Integer userId, @NotNull Long productoId, int quantity) {
        this.userId = userId;
        this.productoId = productoId;
        this.createdDate = new Date();
        this.quantity = quantity;
    }

    public Carrito(CarritoDTO cartDto, Producto producto) {
        this.productoId = cartDto.getProductoId();
        this.quantity = cartDto.getQuantity();
        this.producto = producto;
        this.createdDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getProductoId() {
        return productoId;
    }

    public void setProductId(long productId) {
        this.productoId = productoId;
    }

    public DateTimeContext getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTimeContext createdDate) {
        this.createdDate = createdDate;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProduct(Producto producto) {
        this.producto = producto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
