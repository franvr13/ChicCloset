package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private @NotBlank Integer userId;

    @Column(name = "producto_id")
    private @NotBlank Long productoId;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Producto producto;


    private int quantity;

    public Carrito() {
    }


    public Carrito(CartDto cartDto, Producto producto,int userId){
        this.userId = userId;
        this.productoId = cartDto.getProductoId();
        this.quantity = cartDto.getQuantity();
        this.producto = producto;
        this.createdDate = new Date();
    }

    public Carrito(@NotBlank Integer userId, @NotBlank Long productoId, int quantity) {
        this.userId = userId;
        this.productoId = productoId;
        this.createdDate = new Date();
        this.quantity = quantity;
    }

    public Carrito(CartDto cartDto, Producto producto) {
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
        this.productoId = productId;
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
