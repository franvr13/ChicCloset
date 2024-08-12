package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.entidades.ProductoCarrito;
import com.eoi.tiendaderopa.repositorios.RepoCarrito;
import com.eoi.tiendaderopa.repositorios.RepoProductoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

import static groovyjarjarantlr4.v4.gui.Trees.save;

@Service
public class SrvcCarrito {

    @Autowired
    private static RepoCarrito repoCarrito;
    @Autowired
    private SrvcProducto srvcProducto;
    @Autowired
    private RepoProductoCarrito repoProductoCarrito;

    public static Carrito addCarritoPrimeraVez(Long id, String sessionToken, int cantidad) {
        Carrito carrito = new Carrito();
        ProductoCarrito productoCarrito = new ProductoCarrito();
        productoCarrito.setDate(new Date());
        productoCarrito.setProducto(SrvcProducto.getProductoByID(id));
        carrito.getProducto().add(productoCarrito);
        carrito.setTokenSession(sessionToken);
        carrito = repoCarrito.save(carrito);
        return carrito;

    }

    public Carrito addToExistingCarrito(Long id, String sessionToken, int cantidad) {

        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);
        Producto p = SrvcProducto.getProductoByID(id);
        Boolean productDoesExistInTheCart = false;
        if (carrito != null) {
            Set<ProductoCarrito> productoCarrito = (Set<ProductoCarrito>) carrito.getProducto();
            for (ProductoCarrito producto : productoCarrito) {
                if (producto.getProducto().equals(p)) {
                    productDoesExistInTheCart = true;
                    carrito.getProducto();
                    return repoCarrito.saveAndFlush(carrito);
                }

            }
        }
        if(!productDoesExistInTheCart && (carrito != null))
        {
            ProductoCarrito productoCarrito = new ProductoCarrito();
            productoCarrito.setDate(new Date());
            productoCarrito.setProducto(p);
            carrito.getProducto().add(productoCarrito);
            return repoCarrito.saveAndFlush(carrito);
        }

        return this.addCarritoPrimeraVez(id, sessionToken, cantidad);

    }

    public Carrito getCarritoBySessionToken(String sessionToken) {
        return  repoCarrito.findByTokenSession(sessionToken);
    }

    public ProductoCarrito updateProductoCarrito(Long id, int cantidad) {
        ProductoCarrito productoCarrito = repoProductoCarrito.findById(id).get();
        return repoProductoCarrito.saveAndFlush(productoCarrito);
    }

    public Carrito removeProductoCarritoFromCarrito(Long id, String sessionToken) {
        Carrito carrito = repoCarrito.findByTokenSession(sessionToken);
        Set<ProductoCarrito> productoCarrito = (Set<ProductoCarrito>) repoProductoCarrito.getReferenceById(id);
        ProductoCarrito productoCarrito1 = null;
        for(ProductoCarrito producto : productoCarrito) {
            if(producto.getId()==id) {
                producto = producto;
            }
        }
        productoCarrito.remove(productoCarrito);
        repoProductoCarrito.delete((ProductoCarrito) productoCarrito);
        return repoCarrito.save(carrito);
    }

    public void clearCarrito(String sessionToken) {
        Carrito car = repoCarrito.findByTokenSession(sessionToken);
        repoCarrito.delete(car);

    }

}
