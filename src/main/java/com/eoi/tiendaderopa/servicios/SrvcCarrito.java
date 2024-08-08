package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.entidades.ProductoCarrito;
import com.eoi.tiendaderopa.repositorios.RepoCarrito;
import com.eoi.tiendaderopa.repositorios.RepoProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class SrvcCarrito {

    @Autowired
    private RepoCarrito repoCarrito;

    @Autowired
    private SrvcProducto srvcProducto;

    @Autowired
    private RepoProductoCarrito repoProductoCarrito;

    public Carrito addCarritoPrimeraVez(Long id, String sessionToken, int cantidad) {
        Carrito carrito = new Carrito();
        ProductoCarrito productoCarrito = new ProductoCarrito();
        productoCarrito.setQuantity(cantidad);
        productoCarrito.setDate(new Date());
        productoCarrito.setProducto(srvcProducto.getProductoByID(id));
        carrito.setCarrito(new HashSet<>());
        carrito.getCarrito().add(productoCarrito);
        carrito.setTokenSession(sessionToken);
        return repoCarrito.save(carrito);
    }

    public Carrito addToExistingCarrito(Long id, String sessionToken, int cantidad) {
        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);
        if (carrito != null) {
            Set<ProductoCarrito> productosEnCarrito = carrito.getCarrito();
            Producto producto = srvcProducto.getProductoByID(id);
            boolean productoExistente = false;

            for (ProductoCarrito productoCarrito : productosEnCarrito) {
                if (productoCarrito.getProducto().equals(producto)) {
                    productoExistente = true;
                    productoCarrito.setQuantity(cantidad);
                    return repoCarrito.save(carrito);
                }
            }

            if (!productoExistente) {
                ProductoCarrito nuevoProductoCarrito = new ProductoCarrito();
                nuevoProductoCarrito.setDate(new Date());
                nuevoProductoCarrito.setQuantity(cantidad);
                nuevoProductoCarrito.setProducto(producto);
                productosEnCarrito.add(nuevoProductoCarrito);
                return repoCarrito.save(carrito);
            }
        }
        return addCarritoPrimeraVez(id, sessionToken, cantidad);
    }

    public Carrito getCarritoBySessionToken(String sessionToken) {
        return repoCarrito.findByTokenSession(sessionToken);
    }

    public ProductoCarrito updateProductoCarrito(Long id, int cantidad) {
        ProductoCarrito productoCarrito = repoProductoCarrito.findById(id).orElse(null);
        if (productoCarrito != null) {
            productoCarrito.setQuantity(cantidad);
            return repoProductoCarrito.save(productoCarrito);
        }
        return null;
    }

    public Carrito removeProductoCarritoFromCarrito(Long id, String sessionToken) {
        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);
        if (carrito != null) {
            Set<ProductoCarrito> productosEnCarrito = carrito.getCarrito();
            ProductoCarrito productoAEliminar = null;

            for (ProductoCarrito productoCarrito : productosEnCarrito) {
                if (productoCarrito.getId().equals(id)) {
                    productoAEliminar = productoCarrito;
                    break;
                }
            }

            if (productoAEliminar != null) {
                productosEnCarrito.remove(productoAEliminar);
                repoProductoCarrito.delete(productoAEliminar);
                return repoCarrito.save(carrito);
            }
        }
        return carrito;
    }

    public void clearCarrito(String sessionToken) {
        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);
        if (carrito != null) {
            repoCarrito.delete(carrito);
        }
    }
}
