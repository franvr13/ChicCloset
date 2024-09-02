package com.eoi.tiendaderopa.repositorios;


import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepoDetallesUsuario extends JpaRepository<DetallesUsuario, Integer> {

    Optional<DetallesUsuario> findByUsuario(Usuario usuario);
    Optional<DetallesUsuario> findByUsuarioId(Integer usuario);
    DetallesUsuario findByUsuarioDetalle_Id(int usuarioId);
}
