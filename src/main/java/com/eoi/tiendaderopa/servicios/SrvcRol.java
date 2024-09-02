package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Rol;
import com.eoi.tiendaderopa.repositorios.RepoRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SrvcRol extends AbstractBusinessSrvc<Rol, Long, RepoRol> {
    private final RepoRol repoRol;

    protected SrvcRol(RepoRol repoRol) {
        super(repoRol);
        this.repoRol = repoRol;
    }

    public Rol getRol(String rol) {
        if(repoRol.findByRolNombre(rol).isPresent()){
            return repoRol.findByRolNombre(rol).get();
        }
        else return null;
    }
}

