package com.eoi.tiendaderopa.repositorios;


import com.eoi.tiendaderopa.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCarrito extends JpaRepository<Carrito, Long> {
        Carrito findByTokenSession(String tokenSession);

}
