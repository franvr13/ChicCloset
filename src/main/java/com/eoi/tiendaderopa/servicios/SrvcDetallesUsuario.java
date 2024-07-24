package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoDetallesUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SrvcDetallesUsuario extends AbstractBusinessSrvc<DetallesUsuario, Integer, RepoDetallesUsuario> {

    @Autowired
    public SrvcDetallesUsuario(RepoDetallesUsuario usuarioDetallesRepo) {
        super(usuarioDetallesRepo);
    }

    @Autowired
    RepoDetallesUsuario repoDetallesUsuario;

    public List<DetallesUsuario> obtenerDetallesUsuario(Usuario usuario) {
        return repoDetallesUsuario.findByUsuario(usuario);
    }

    public List<DetallesUsuario> obtenerDetallesUsuarioporId(Integer idusuario) {
        return repoDetallesUsuario.findByUsuarioId(idusuario);
    }

}
