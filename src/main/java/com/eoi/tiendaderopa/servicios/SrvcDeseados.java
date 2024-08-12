package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.ListaProductosDeseados;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.entidades.ProductoDeseado;
import com.eoi.tiendaderopa.repositorios.RepoDeseados;
import com.eoi.tiendaderopa.repositorios.RepoProductoDeseados;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class SrvcDeseados {

    private final RepoDeseados repoDeseados;
    private final RepoProductoDeseados repoProductoDeseados;
    private final SrvcProducto srvcProducto;

    public SrvcDeseados(RepoDeseados repoDeseados, RepoProductoDeseados repoProductoDeseados, SrvcProducto srvcProducto) {
        this.repoDeseados = repoDeseados;
        this.repoProductoDeseados = repoProductoDeseados;
        this.srvcProducto = srvcProducto;
    }


    //TODO Repasar con mimo esta parte de añadir o elimninar productos a listas de deseados etc.
    // Dad mejores nombres a los objetos para tener claro lo que son.


    public ListaProductosDeseados addToDeseadosPrimeraVez(Long id, String sessionToken) {
        ListaProductosDeseados listaProductosDeseados = new ListaProductosDeseados();
        ProductoDeseado productoDeseados = new ProductoDeseado();

        productoDeseados.setDate(new Date());
        productoDeseados.setProducto(srvcProducto.getProductoByID(id));
        listaProductosDeseados.getProducto().add(productoDeseados);
        listaProductosDeseados.setSessionToken(sessionToken);
        listaProductosDeseados.setDate(new Date());
        return repoDeseados.save(listaProductosDeseados);

    }

    public ListaProductosDeseados addToExistingDeseados(Long id, String sessionToken) {

        ListaProductosDeseados listaProductosDeseados = repoDeseados.findBySessionToken(sessionToken);


        Producto p = srvcProducto.getProductoByID(id);
        Boolean productoExisteEnCarrito = false;
        if (listaProductosDeseados != null) {
            Set<ProductoDeseado> producto = listaProductosDeseados.getProducto();
            for (ProductoDeseado productoDeseados : producto) {
                if (productoDeseados.getProducto().equals(p)) {
                    productoExisteEnCarrito = true;
                    break;
                }

            }
        }
        if(!productoExisteEnCarrito && (listaProductosDeseados != null))
        {
            ProductoDeseado productoDeseados = new ProductoDeseado();
            productoDeseados.setDate(new Date());
            productoDeseados.setProducto(p);
            productoDeseados.getProducto().add(productoDeseados);
            //TODO echadle un ojo a esto.
            //deseados.setProducto(productoDeseados);

        }
        return repoDeseados.save(listaProductosDeseados);

    }

    public ListaProductosDeseados getDeseadosBySessionToken(String sessionToken) {

        return  repoDeseados.findBySessionToken(sessionToken);
    }


    public ListaProductosDeseados removeProductoDeseados(Long id, String sessionToken) {

        //TODO  Revisad este método. Parece que queréis eliminar un producto de un Set.


        ListaProductosDeseados listaProductosDeseados = repoDeseados.findBySessionToken(sessionToken);
        // asi se pueden eliminar objetos de un set
        // deseados.getProducto().remove(); <---

       Set<ProductoDeseado> productoDeseados = listaProductosDeseados.getProducto();

        //así tambien se puede eliminar un objeto
        productoDeseados.remove(listaProductosDeseados);

        //ProductoDeseados productoDeseados1 = null;
//
//        for(ProductoDeseados productoDeseados2 : productoDeseados) {
//           if(productoDeseados1.getId()==id) {
//                productoDeseados = (Set<ProductoDeseados>) productoDeseados1;
//            }
//        }
//        productoDeseados.remove(productoDeseados);
//        repoProductoDeseados.delete((ProductoDeseados) productoDeseados);
//        deseados.setProducto(productoDeseados);

        productoDeseados.forEach(productoDeseados1 -> {
            repoProductoDeseados.delete(productoDeseados1);
        });

        return repoDeseados.save(listaProductosDeseados);
    }

    public void clearDeseados(String sessionToken) {

        ProductoDeseado producto = repoProductoDeseados.findByTokenSession(sessionToken);
        repoProductoDeseados.delete(producto);

    }
}
