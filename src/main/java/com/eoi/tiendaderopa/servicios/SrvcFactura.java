package com.eoi.tiendaderopa.servicios;
import com.eoi.tiendaderopa.entidades.Factura;
import com.eoi.tiendaderopa.repositorios.RepoFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrvcFactura extends AbstractBusinessSrvc {
    protected SrvcFactura(RepoFactura repoFactura) {
        super(repoFactura);
    }
}

