package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.dto.AddToCarritoDTO;
import com.eoi.tiendaderopa.dto.CarritoDTO;
import com.eoi.tiendaderopa.dto.CosteCarrito;
import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoCarrito;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SrvcCarrito {

    private final Carrito carrito;
    private final RepoCarrito repoCarrito;

    public SrvcCarrito(Carrito carrito, RepoCarrito repoCarrito) {
        this.carrito = carrito;
        this.repoCarrito = repoCarrito;
    }

    public void addToCart(AddToCarritoDTO addToCartDto, int userId){
        Carrito cart = getAddToCartFromDto(AddToCarritoDTO,userId);
        repoCarrito.save(cart);
    }

    private Carrito getAddToCartFromDto(AddToCarritoDTO addToCartDto, int userId) {
        Carrito cart = new Carrito(AddToCarritoDTO, userId);
        return cart;
    }

    public CosteCarrito listCartItems(int user_id) {
        List<Carrito> cartList = repoCarrito.findAllByUserIdOrderByCreatedDateDesc(user_id);
        List<CarritoDTO> cartItems = new ArrayList<>();
        for (Carrito cart:cartList){
            CarritoDTO cartDto = getDtoFromCart(carrito);
            cartItems.add(cartDto);
        }
        double totalCost = 0;
        for (CarritoDTO cartDto:cartItems){
            totalCost += (cartDto.getProducto().getPrecio()* cartDto.getQuantity());
        }
        CosteCarrito costeCarrito = new CosteCarrito(cartItems,totalCost);
        return costeCarrito;
    }


    public static CarritoDTO getDtoFromCart(Carrito cart) {
        CarritoDTO carritoDTO = new CarritoDTO(carritoDTO);
        return carritoDTO;
    }


    public void updateCartItem(AddToCarritoDTO carritoDTO, int userId, Producto product){
        Carrito carrito = getAddToCartFromDto(carritoDTO,userId);
        carrito.setQuantity(carritoDTO.getQuantity());
        carrito.setUserId(userId);
        carrito.setId(carritoDTO.getId());
        carrito.setProductoId(producto.getId());
        carrito.setCreatedDate(new Date());
        repoCarrito.save(carrito);
    }

    public void deleteCartItem(int id,int userId) throws CartItemNotExistException {
        if (!repoCarrito.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        repoCarrito.deleteById(id);

    }
}
