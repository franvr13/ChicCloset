package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.MetodoPago;
import com.eoi.tiendaderopa.repositorios.RepoMetodoPago;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvcMetodoPago extends AbstractBusinessSrvc {
    protected SrvcMetodoPago(RepoMetodoPago repoMetodoPago) {
        super(repoMetodoPago);
    }
}
