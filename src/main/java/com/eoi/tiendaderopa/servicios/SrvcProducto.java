package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.repositorios.RepoPedido;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import org.springframework.stereotype.Service;

@Service
public class SrvcProducto extends AbstractBusinessSrvc {
    protected SrvcProducto(RepoProducto repo) {
        super(repo);
    }
}
