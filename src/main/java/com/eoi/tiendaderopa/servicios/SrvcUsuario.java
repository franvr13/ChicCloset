package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class SrvcUsuario extends AbstractBusinessSrvc<Usuario, Integer, RepoUsuario> {

    private final RepoUsuario repoUsuario;

    protected SrvcUsuario(RepoUsuario repoUsuario) {
        super(repoUsuario);
        this.repoUsuario = repoUsuario;
    }

    public void registrarUsuario(Usuario usuario) {

        repoUsuario.save(usuario);

    }
}
