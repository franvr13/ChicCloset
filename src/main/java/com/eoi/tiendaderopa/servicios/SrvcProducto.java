package com.eoi.tiendaderopa.servicios;
import com.eoi.tiendaderopa.dto.ProductNotExistException;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoPedido;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SrvcProducto extends AbstractBusinessSrvc {
    protected SrvcProducto(RepoProducto repo) {
        super(repo);
    }

    public Producto getProductById(Long productId) throws ProductNotExistException {
        Optional<Producto> optionalProduct = RepoProducto.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }
}
