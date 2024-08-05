package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RepoProducto extends JpaRepository<Producto, Long> {
    Page<Producto> findAll(Pageable pageable);
    Page<Producto> findByColor(String color, Pageable pageable);
    Page<Producto> findByTalla(String talla, Pageable pageable);
    Page<Producto> findByColorAndTalla(String color, String talla, Pageable pageable);


}
