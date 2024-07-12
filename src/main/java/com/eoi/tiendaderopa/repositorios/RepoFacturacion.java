package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.DatosFacturacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoFacturacion extends JpaRepository<DatosFacturacion,Integer> {
}
