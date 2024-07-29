package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.DatosFacturacion;
import com.eoi.tiendaderopa.repositorios.RepoFacturacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrvcDatosFacturacion extends AbstractBusinessSrvc<DatosFacturacion, Integer, RepoFacturacion> {

    @Autowired
    public SrvcDatosFacturacion(RepoFacturacion repo) {
        super(repo);
    }
}
