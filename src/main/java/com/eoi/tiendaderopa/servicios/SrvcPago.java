package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.repositorios.RepoPago;
import org.springframework.stereotype.Service;

@Service
public class SrvcPago extends AbstractBusinessSrvc {
    protected SrvcPago(RepoPago repoPago) {
        super(repoPago);
    }
}
