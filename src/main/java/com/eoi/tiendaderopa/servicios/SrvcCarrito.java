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
        productoCarrito.getCantidad(cantidad);
        productoCarrito.setDate(new Date());
        productoCarrito.setProducto(SrvcProducto.getProductoByID(id));
        carrito.getProducto().add(productoCarrito);
        carrito.setTokenSession(sessionToken);
        Carrito save = RepoCarrito.save(carrito);
        return save;

    }

    public Carrito addToExistingCarrito(Long id, String sessionToken, int cantidad) {

        Carrito carrito = RepoCarrito.findByTokenSession(sessionToken);
        Producto p = SrvcProducto.getProductoByID(id);
        Boolean productDoesExistInTheCart = false;
        if (carrito != null) {
            Set<ProductoCarrito> productoCarrito = (Set<ProductoCarrito>) carrito.getProducto();
            for (ProductoCarrito producto : productoCarrito) {
                if (producto.getProducto().equals(p)) {
                    productDoesExistInTheCart = true;
                    producto.getCantidad(cantidad);
                    carrito.getProducto();
                    return repoCarrito.saveAndFlush(carrito);
                }

            }
        }
        if(!productDoesExistInTheCart && (carrito != null))
        {
            ProductoCarrito productoCarrito = new ProductoCarrito();
            productoCarrito.setDate(new Date());
            productoCarrito.getCantidad(cantidad);
            productoCarrito.setProducto(p);
            carrito.getProducto().add(productoCarrito);
            return repoCarrito.saveAndFlush(carrito);
        }

        return this.addCarritoPrimeraVez(id, sessionToken, cantidad);

    }

    public String getCarritoBySessionToken(String sessionToken) {

        return  RepoCarrito.findByTokenSession(sessionToken);
    }

    public ProductoCarrito updateProductoCarrito(Long id, int cantidad) {
        ProductoCarrito productoCarrito = repoProductoCarrito.findById(id).get();
        productoCarrito.getCantidad(cantidad);
        return repoProductoCarrito.saveAndFlush(productoCarrito);

    }

    public Carrito removeProductoCarritoFromCarrito(Long id, String sessionToken) {
        String carrito = RepoCarrito.findByTokenSession(sessionToken);
        Set<ProductoCarrito> productoCarrito = (Set<ProductoCarrito>) carrito.productoCarrito();
        ProductoCarrito productoCarrito1 = null;
        for(ProductoCarrito producto : productoCarrito) {
            if(producto.getId()==id) {
                producto = producto;
            }
        }
        productoCarrito.remove(productoCarrito);
        repoProductoCarrito.delete((ProductoCarrito) productoCarrito);
        return repoCarrito.guardar(carrito);
    }

    public void clearShoppingCart(String sessionToken) {
        String car = RepoCarrito.findByTokenSession(sessionToken);
        repoCarrito.delete(car);

    }

}
