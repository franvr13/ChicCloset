package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Deseados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoDeseados extends JpaRepository<Deseados, Long> {
    Deseados findBySessionToken(String sessionToken);
}
