package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoFactura extends JpaRepository<Factura, Integer> {
}
