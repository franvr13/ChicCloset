package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.HistorialBusquedas;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoUsuarioDetalles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvcUsuarioDetalles extends AbstractBusinessSrvc<DetallesUsuario, Integer, RepoUsuarioDetalles> {

    @Autowired
    public SrvcUsuarioDetalles(RepoUsuarioDetalles usuarioDetallesRepo) {
        super(usuarioDetallesRepo);
    }

    @Autowired
    RepoUsuarioDetalles repoUsuarioDetalles;

    public List<DetallesUsuario> obtenerDetallesUsuario(Usuario usuario) {
        return repoUsuarioDetalles.findByUsuario(usuario);
    }
}
