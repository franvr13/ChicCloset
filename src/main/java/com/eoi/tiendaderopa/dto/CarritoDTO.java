package com.eoi.tiendaderopa.dto;

import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.entidades.Producto;
import org.antlr.v4.runtime.misc.NotNull;

public class CarritoDTO {
    private Integer id;
    private @NotNull Integer userId;
    private @NotNull Integer quantity;
    private @NotNull Producto product;

    public CarritoDTO() {
    }

    public CartDTO(Carrito carrito) {
        this.setId(carrito.getId());
        this.setUserId(carrito.getUserId());
        this.setQuantity(carrito.getQuantity());
        this.setProducto(carrito.getProducto());
    }

    @Override
    public String toString() {
        return "CarritoDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", productoName=" + getProducto().getNombre() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Producto getProducto() {
        return product;
    }

    public void setProducto(Producto producto) {
        this.product = producto;
    }

}
