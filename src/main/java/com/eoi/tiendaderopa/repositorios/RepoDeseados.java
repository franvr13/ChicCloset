package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.ListaProductosDeseados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDeseados extends JpaRepository<ListaProductosDeseados, Long> {
    ListaProductosDeseados findBySessionToken(String sessionToken);
}
