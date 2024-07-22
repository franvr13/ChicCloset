package com.eoi.tiendaderopa.dto;

import com.eoi.tiendaderopa.entidades.Carrito;
import org.antlr.v4.runtime.misc.NotNull;

public class AddToCarritoDTO {
    private Integer id;
    private @NotNull Integer userId;
    private @NotNull Long productoId;
    private @NotNull Integer quantity;

    public AddToCarritoDTO() {
    }

    public AddToCarritoDTO(Integer id, @NotNull Integer userId, @NotNull Long productId, @NotNull Integer quantity) {
        this.id = id;
        this.userId = userId;
        this.productoId = productId;
        this.quantity = quantity;
    }

    public AddToCarritoDTO(Carrito carrito) {
        this.setId(carrito.getId());
        this.setProductId(carrito.getProductoId());
        this.setUserId(carrito.getUserId());
        this.setQuantity(carrito.getQuantity());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productoId +
                ", quantity=" + quantity +
                ",";
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

    public Long getProductId() {
        return productoId;
    }

    public void setProductId(Long productId) {
        this.productoId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
