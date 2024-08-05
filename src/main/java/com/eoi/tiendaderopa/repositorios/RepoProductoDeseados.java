package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProductoDeseados extends JpaRepository<ProductoDeseados, Long> {

    static String findByTokenSession(String tokenSession) {
        return tokenSession;
    }

    static Deseados saveAndFlush(Deseados deseados) {
        return deseados;
    }

    static Deseados add(Deseados deseados) {
        return deseados;
    }

    void delete(String car);
}
