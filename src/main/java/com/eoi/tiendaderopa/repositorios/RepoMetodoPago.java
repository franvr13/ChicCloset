package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoMetodoPago extends JpaRepository<MetodoPago, Long> {
}
