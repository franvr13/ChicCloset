package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoDetallesUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SrvcDetallesUsuario extends AbstractBusinessSrvc<DetallesUsuario, Integer, RepoDetallesUsuario> {

    @Autowired
    public SrvcDetallesUsuario(RepoDetallesUsuario usuarioDetallesRepo) {
        super(usuarioDetallesRepo);
    }

    @Autowired
    RepoDetallesUsuario repoDetallesUsuario;

    public Optional<DetallesUsuario> obtenerDetallesUsuario(Usuario usuario) {
        return repoDetallesUsuario.findByUsuario(usuario);
    }

    public Optional<DetallesUsuario> obtenerDetallesUsuarioporId(Usuario usuario
    )
    {
        return repoDetallesUsuario.findByUsuario(usuario);
    }

}
