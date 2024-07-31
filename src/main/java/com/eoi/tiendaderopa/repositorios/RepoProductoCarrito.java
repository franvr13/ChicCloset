package com.eoi.tiendaderopa.repositorios;


import com.eoi.tiendaderopa.entidades.ProductoCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProductoCarrito extends JpaRepository<ProductoCarrito, Long> {

}
