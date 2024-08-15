package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.entidades.ProductoCarrito;
import com.eoi.tiendaderopa.repositorios.RepoCarrito;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import com.eoi.tiendaderopa.repositorios.RepoProductoCarrito;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static groovyjarjarantlr4.v4.gui.Trees.save;

@Service
public class SrvcCarrito {

    @Autowired
    private RepoCarrito repoCarrito;
    @Autowired
    private SrvcProducto srvcProducto;
    @Autowired
    private RepoProductoCarrito repoProductoCarrito;

    @Autowired
    private RepoProducto repoProducto;

    public Carrito addCarritoPrimeraVez(Long id, String sessionToken, int cantidad) {
        Carrito carrito = new Carrito();
        carrito.setTokenSession(sessionToken);
        return repoCarrito.save(carrito);
    }


    @Transactional
    public void addToExistingCarrito(Long productoId, String sessionToken, int cantidad, HttpSession session) {
        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);

        if (carrito == null) {
            carrito = new Carrito();
            carrito.setTokenSession(sessionToken);
            carrito = repoCarrito.save(carrito);
        }

        Producto producto = repoProducto.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productoId));

        ProductoCarrito productoCarrito = carrito.getListaProductosCarrito()
                .stream()
                .filter(pc -> pc.getProducto().equals(producto))
                .findFirst()
                .orElse(null);

        if (productoCarrito != null) {
            // Si el producto ya está en el carrito, actualizar la cantidad
            productoCarrito.setQuantity(productoCarrito.getQuantity() + cantidad);
            repoProductoCarrito.save(productoCarrito);
        } else {
            // Si el producto no está en el carrito, añadirlo
            productoCarrito = new ProductoCarrito();
            productoCarrito.setProducto(producto);
            productoCarrito.setQuantity(cantidad);
            productoCarrito.setCarrito(carrito);
            carrito.getListaProductosCarrito().add(productoCarrito);
            repoProductoCarrito.save(productoCarrito);
        }

        repoCarrito.save(carrito);
    }



    public Carrito getCarritoBySessionToken(String sessionToken) {
        return  repoCarrito.findByTokenSession(sessionToken);
    }

    public ProductoCarrito updateProductoCarrito(Long id, int cantidad) {
        ProductoCarrito productoCarrito = repoProductoCarrito.findById(id).get();
        return repoProductoCarrito.saveAndFlush(productoCarrito);
    }

    @Transactional
    public void removeProductoCarritoFromCarrito(Long id, String sessionToken) {
        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);
        ProductoCarrito productoCarrito = repoProductoCarrito.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductoCarrito no encontrado: " + id));

        carrito.getListaProductosCarrito().remove(productoCarrito);
        repoProductoCarrito.delete(productoCarrito);
        repoCarrito.save(carrito);
    }

    @Transactional
    public void clearCarrito(String sessionToken) {
        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);
        if (carrito == null) {
            throw new RuntimeException("Carrito no encontrado para el token de sesión: " + sessionToken);
        }
        repoProductoCarrito.deleteAll(carrito.getListaProductosCarrito());
        carrito.getListaProductosCarrito().clear();
        repoCarrito.save(carrito);

    }

}
