package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RepoProducto extends JpaRepository<Producto, Long> {
    Optional<Producto> findById(Integer id);
    Page<Producto> findAll(Pageable pageable);

}
