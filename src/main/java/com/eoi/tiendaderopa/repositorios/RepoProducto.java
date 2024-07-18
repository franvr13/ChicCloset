package com.eoi.tiendaderopa.repositorios;
import com.eoi.tiendaderopa.entidades.Pago;
import com.eoi.tiendaderopa.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RepoProducto extends JpaRepository<Producto, Long> {

}
