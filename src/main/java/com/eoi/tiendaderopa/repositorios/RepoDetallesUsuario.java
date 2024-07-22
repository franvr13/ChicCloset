package com.eoi.tiendaderopa.repositorios;


import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepoDetallesUsuario extends JpaRepository<DetallesUsuario, Integer> {

    List<DetallesUsuario> findByUsuario(Usuario usuario);
    List<DetallesUsuario> findByUsuarioId(Integer usuario);
}
