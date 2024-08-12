package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProductoDeseados extends JpaRepository<ProductoDeseado, Long> {

    ProductoDeseado findByTokenSession(String tokenSession);

}
