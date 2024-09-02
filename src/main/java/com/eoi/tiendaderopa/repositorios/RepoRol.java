package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoRol extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByRolNombre(String rol);
}
