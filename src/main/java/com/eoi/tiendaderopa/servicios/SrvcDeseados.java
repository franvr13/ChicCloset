package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Deseados;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.entidades.ProductoDeseados;
import com.eoi.tiendaderopa.repositorios.RepoDeseados;
import com.eoi.tiendaderopa.repositorios.RepoProductoDeseados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class SrvcDeseados {

    @Autowired
    private RepoDeseados repoDeseados;
    @Autowired
    private RepoProductoDeseados repoProductoDeseados;
    @Autowired
    private SrvcProducto srvcProducto;

    public Deseados addToDeseadosPrimeraVez(Long id, String sessionToken) {
        Deseados deseados = new Deseados();
        ProductoDeseados productoDeseados = new ProductoDeseados();

        productoDeseados.setDate(new Date());
        productoDeseados.setProducto(srvcProducto.getProductoByID(id));
        deseados.getProducto().add(productoDeseados);
        deseados.setSessionToken(sessionToken);
        deseados.setDate(new Date());
        return repoDeseados.save(deseados);

    }

    public Deseados addToExistingDeseados(Long id, String sessionToken) {

        Deseados deseados = repoDeseados.findBySessionToken(sessionToken);
        Producto p = srvcProducto.getProductoByID(id);
        Boolean productoExisteEnCarrito = false;
        if (deseados != null) {
            Set<ProductoDeseados> producto = deseados.getProducto();
            for (ProductoDeseados productoDeseados : producto) {
                if (productoDeseados.getProducto().equals(p)) {
                    productoExisteEnCarrito = true;
                    break;
                }

            }
        }
        if(!productoExisteEnCarrito && (deseados != null))
        {
            ProductoDeseados productoDeseados = new ProductoDeseados();
            productoDeseados.setDate(new Date());
            productoDeseados.setProducto(p);
            productoDeseados.getProducto().add(productoDeseados);
            return RepoProductoDeseados.saveAndFlush(deseados);
        }

        return null;

    }

    public Deseados getDeseadosBySessionToken(String sessionToken) {

        return  repoDeseados.findBySessionToken(sessionToken);
    }


    public Deseados removeProductoDeseados(Long id, String sessionToken) {
        Deseados Deseados = repoDeseados.findBySessionToken(sessionToken);
        Set<ProductoDeseados> productoDeseados = Deseados.getProducto();
        ProductoDeseados productoDeseados1 = null;
        for(ProductoDeseados productoDeseados2 : productoDeseados) {
            if(productoDeseados1.getId()==id) {
                productoDeseados = (Set<ProductoDeseados>) productoDeseados1;
            }
        }
        productoDeseados.remove(productoDeseados);
        repoProductoDeseados.delete((ProductoDeseados) productoDeseados);
        Deseados.setProducto(productoDeseados);
        return RepoProductoDeseados.saveAndFlush(Deseados);
    }

    public void clearDeseados(String sessionToken) {
        String car = RepoProductoDeseados.findByTokenSession(sessionToken);
        repoProductoDeseados.delete(car);

    }
}
