package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Rol;
import com.eoi.tiendaderopa.repositorios.RepoRol;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SrvcRol extends AbstractBusinessSrvc {
    protected SrvcRol(RepoRol repoRol) {
        super(repoRol);
    }
}

