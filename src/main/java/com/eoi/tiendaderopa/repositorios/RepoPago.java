package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.MetodoPago;
import com.eoi.tiendaderopa.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPago extends JpaRepository<Pago, Long> {
}
