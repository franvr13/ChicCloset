package com.eoi.tiendaderopa.dto;

import java.util.List;

public class CosteCarrito {
    private List<CarritoDTO> cartItems;
    private double totalCost;

    public CosteCarrito(List<CarritoDTO> cartDtoList, double totalCost) {
        this.cartItems = cartDtoList;
        this.totalCost = totalCost;
    }

    public List<CarritoDTO> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CarritoDTO> cartDtoList) {
        this.cartItems = cartDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
