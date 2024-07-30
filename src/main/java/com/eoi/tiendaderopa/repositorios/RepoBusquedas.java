package com.eoi.tiendaderopa.repositorios;


import com.eoi.tiendaderopa.entidades.HistorialBusquedas;
import com.eoi.tiendaderopa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoBusquedas extends JpaRepository<HistorialBusquedas, Integer> {
    List<HistorialBusquedas> findByUsuario(Usuario usuario);
}
