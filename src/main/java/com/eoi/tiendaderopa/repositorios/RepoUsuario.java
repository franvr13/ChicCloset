package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Integer> {
}
