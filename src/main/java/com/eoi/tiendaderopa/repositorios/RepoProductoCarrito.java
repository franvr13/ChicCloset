package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.entidades.ProductoCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoProductoCarrito extends JpaRepository<ProductoCarrito, Long> {

    public List<ProductoCarrito> findAllByCarrito(Carrito carrito);

}
