package com.eoi.tiendaderopa.dto;

public class CosteCarrito {
    private List<CarritoDto> cartItems;
    private double totalCost;

    public CartCost(List<CarritoDto> cartDtoList, double totalCost) {
        this.cartItems = cartDtoList;
        this.totalCost = totalCost;
    }

    public List<CarritoDto> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CarritoDto> cartDtoList) {
        this.cartItems = cartDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
